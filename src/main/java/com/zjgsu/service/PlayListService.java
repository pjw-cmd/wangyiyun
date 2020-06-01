package com.zjgsu.service;

import com.zjgsu.dao.PlayListDao;
import com.zjgsu.dao.PlayListItemDao;
import com.zjgsu.entity.PlayListEntity;
import com.zjgsu.entity.PlayListItemEntity;
import com.zjgsu.utils.IDGenerator;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PlayListService {
    @Autowired
    PlayListItemDao playListItemDao;

    @Autowired
    PlayListDao playListDao;

    /*
        创建播放列表
     */
    public String createPlayList(String user_id) {
        PlayListEntity playListEntity = new PlayListEntity();
        playListEntity.setUserId(user_id);
        playListEntity.setPlayListId(IDGenerator.generateID());
        return playListDao.save(playListEntity);
    }

    /*
        添加播放歌曲
     */
    public String addMusicToPlayList(String playlist_id, String music_id) {
        PlayListItemEntity playListItemEntity = new PlayListItemEntity();
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
}
