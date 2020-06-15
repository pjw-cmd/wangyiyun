package com.zjgsu.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zjgsu.dao.MusicItemDao;
import com.zjgsu.dao.PlayListDao;
import com.zjgsu.dao.PlayListItemDao;
import com.zjgsu.entity.MusicItemEntity;
import com.zjgsu.entity.PlayListEntity;
import com.zjgsu.entity.PlayListItemEntity;
import com.zjgsu.utils.IDGenerator;
import com.zjgsu.utils.MyStringUtils;
import jdk.nashorn.internal.scripts.JO;
import org.apache.commons.lang3.ArrayUtils;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PlayListService {
    @Autowired
    MusicItemService musicItemService;
    @Autowired
    PlayListItemDao playListItemDao;
    @Autowired
    PlayListDao playListDao;
    @Autowired
    MusicItemDao musicItemDao;

    Integer not_delete = 0;
    Integer delete = 1;

    int playListItemIdLength = 10;


    public void updateUserPlayList(String userId, JSONArray playList) {
        PlayListEntity oldPlayListEntity = playListDao.getByCriterion(Restrictions.eq("userId", userId));
        if (oldPlayListEntity != null) {
            String playlistId = oldPlayListEntity.getPlayListId();
            List<PlayListItemEntity> playListItemEntityList = playListItemDao.listByCriterion(
                    Restrictions.eq("playListId", playlistId), Restrictions.eq("isDelete", not_delete));

            for (int i = 0; i < playList.size(); i++) {
                JSONObject jsonObject = playList.getJSONObject(i);
                String musicId = String.valueOf(jsonObject.getInteger("id"));
                PlayListItemEntity supPlayListItemEntity = null;
                for (PlayListItemEntity playListItemEntity : playListItemEntityList) {
                    if (musicId.equals(playListItemEntity.getMusicId())) {
                        // 说明
                        supPlayListItemEntity = playListItemEntity;
                        playListItemEntityList.remove(playListItemEntity);
                        break;
                    }
                }
                if (supPlayListItemEntity == null) {
                    // 说明添加了新的歌曲
                    this.addMusicToPlayList(playlistId, musicId);

                }// 说明是老的歌曲不用处理
            }
            // 处理那些被删除的歌曲
            for (PlayListItemEntity playListItemEntity : playListItemEntityList) {
                playListItemEntity.setIsDelete(delete);
                playListItemDao.update(playListItemEntity);
            }

        }
    }

    /*
        创建播放列表
     */
    public String createPlayList(String user_id) {
        PlayListEntity playListEntity = new PlayListEntity();
        playListEntity.setUserId(user_id);
        playListEntity.setPlayListId(IDGenerator.generateID(8));
        return playListDao.save(playListEntity);
    }

    /*
        添加播放歌曲
     */
    public String addMusicToPlayList(String playlist_id, String music_id) {
        PlayListItemEntity playListItemEntity = new PlayListItemEntity();
        playListItemEntity.setPlayListItemId(IDGenerator.generateID(playListItemIdLength));
        playListItemEntity.setPlayListId(playlist_id);
        playListItemEntity.setMusicId(music_id);
        playListItemEntity.setIsDelete(0); //  使用软删除模式
        return playListItemDao.save(playListItemEntity);
    }

    /*
        删除歌曲
     */
    public void deleteMusicItem(String playlist_id, String music_id) {
        PlayListItemEntity playListItemEntity = playListItemDao.getByCriterion(Restrictions.eq("playlistId", playlist_id), Restrictions.eq("musicId", music_id));
        playListItemEntity.setIsDelete(1);

        playListItemDao.update(playListItemEntity);
    }

    /*
        获取用户的播放列表
     */
    public List<JSONObject> getUserPlayList(String userId) {
        // 直接传保存的extra_String ,
        PlayListEntity playListEntity = playListDao.getByCriterion(Restrictions.eq("userId", userId));
        List<JSONObject> musicItemList = new ArrayList<>();
        if (playListEntity != null) {
            List<PlayListItemEntity> playListItemEntityList = playListItemDao.listByCriterion(
                    Restrictions.eq("playListId", playListEntity.getPlayListId()), Restrictions.eq("isDelete", not_delete));
            List<String> lossMusicItemList = new ArrayList<>();
            for (int i = 0; i < playListItemEntityList.size(); i++) {
                PlayListItemEntity playListItemEntity = playListItemEntityList.get(i);
                String musicId = playListItemEntity.getMusicId();
                MusicItemEntity musicItemEntity = musicItemDao.getByCriterion(Restrictions.eq("musicId", musicId));
                if (musicItemEntity != null) {
                    String json_string = musicItemEntity.getExtraString();
                    JSONObject raw_jsonObject = JSONObject.parseObject(json_string);
                    JSONObject music_jsonObject = new JSONObject();

                    music_jsonObject.put("album", raw_jsonObject.getJSONObject(""));
                    music_jsonObject.put("alia", raw_jsonObject.getJSONArray("alia"));
                    music_jsonObject.put("artist", raw_jsonObject.getJSONArray("ar"));
                    music_jsonObject.put("avatar", raw_jsonObject.getJSONObject("al").getString("picUrl"));  // 歌曲图片地址
                    int dt = raw_jsonObject.getIntValue("dt");
                    String string_dt = dt / 1000 + "." + dt % 1000;
                    double duration = Double.parseDouble(string_dt);
                    music_jsonObject.put("duration", duration);
                    music_jsonObject.put("hot", raw_jsonObject.getIntValue("pop"));
                    music_jsonObject.put("id", raw_jsonObject.getIntValue("id"));
                    music_jsonObject.put("isfm", false);
                    music_jsonObject.put("lyric", "");
                    music_jsonObject.put("mvid", "");
                    music_jsonObject.put("name", raw_jsonObject.getString("name"));
                    music_jsonObject.put("url", "");

                    musicItemList.add(music_jsonObject);
                } else {

                    lossMusicItemList.add(musicId);
                }
            }
            if (lossMusicItemList.size() > 0) {
                // 对于缺失的音乐使用网络 请求来弥补
                String ids = MyStringUtils.listToString(lossMusicItemList, ',');
                List<JSONObject> saveMusicItemToDataSetList = musicItemService.saveMusicItemToDataSet(ids);
                musicItemList.addAll(saveMusicItemToDataSetList);
            }


            return musicItemList;
        }
        return null;
    }


    /*
      获取用户播放列表相似的歌曲.
   */
    public List<JSONObject> getUserSimilarityPlayList(String userId) {
        // 根据用户id拿到用户的播放列表
        PlayListEntity playListEntity = playListDao.getByCriterion(Restrictions.eq("userId", userId));
        List<JSONObject> musicItemList = new ArrayList<>();
        List<String> id_list = new ArrayList<>();
        int max_select_music = 10;
        if (playListEntity != null) {
            // 根据播放列表获取到播放列表中的音乐
            List<PlayListItemEntity> playListItemEntityList = playListItemDao.listByCriterion(
                    Restrictions.eq("playListId", playListEntity.getPlayListId()), Restrictions.eq("isDelete", not_delete));
            for (PlayListItemEntity playListItemEntity : playListItemEntityList) {
                // 根据某个音乐来查询相似的音乐
                String data = musicItemService.getSimMusicById(playListItemEntity.getMusicId());
                JSONObject jsonObject = JSONObject.parseObject(data);
                JSONArray sim_songs = jsonObject.getJSONArray("songs");
                for (int i = 0; i < sim_songs.size(); i++) {
                    JSONObject song = sim_songs.getJSONObject(i);
                    id_list.add(song.getString("id"));
                }
                if (id_list.size() >= max_select_music) {
                    break;
                }
            }
            String ids = MyStringUtils.listToString(id_list, ',');
            String data = musicItemService.listMusicByIds(ids);
            JSONObject jsonObject = JSONObject.parseObject(data);
            JSONArray songs = jsonObject.getJSONArray("songs");

            musicItemList = songs.toJavaList(JSONObject.class);
            return musicItemList;
        }
        return null;
    }
}
