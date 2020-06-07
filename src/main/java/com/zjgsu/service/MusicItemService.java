package com.zjgsu.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zjgsu.dao.MusicItemDao;
import com.zjgsu.entity.MusicItemEntity;
import com.zjgsu.utils.Response;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.alibaba.fastjson.util.IOUtils.UTF8;

@Service
public class MusicItemService {
    @Autowired
    MusicItemDao musicItemDao;
    @Autowired
    CloseableHttpClient httpClient;
    @Autowired
    RequestConfig requestConfig;

    /**
     * 先写一个能根据音乐 ids获取音乐的方法
     */
    public String listMusicByIds(String ids) {
        System.out.println("ids:" + ids);
        String url = "http://10.21.243.206:3000/song/detail?ids=" + ids;
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);
        CloseableHttpResponse response = null;
        String responseContext = null;
        try {
            //使用HttpClient发起请求得到响应
            response = httpClient.execute(httpGet);
            //获得响应实体
            HttpEntity entity = response.getEntity();
            Header[] headers = response.getAllHeaders();
            //将响应实体转换为字符串
            responseContext = EntityUtils.toString(entity, UTF8);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseContext;
    }

    /**
     * 将新的音乐保存到数据，并且返回一个可以传输给前端的JSON数据
     *
     * @param ids
     * @return
     */
    public List<JSONObject> saveMusicItemToDataSet(String ids) {
        String data = listMusicByIds(ids);
        JSONObject jsonObject = JSONObject.parseObject(data);
        JSONArray songs = jsonObject.getJSONArray("songs");
        List<JSONObject> musicItemList = new ArrayList<>();
        for (int i = 0; i < songs.size(); i++) {
            JSONObject song = songs.getJSONObject(i);
            JSONObject album = song.getJSONObject("al");
            JSONObject artist = song.getJSONArray("ar").getJSONObject(0);
            MusicItemEntity musicItemEntity = new MusicItemEntity();
            musicItemEntity.setName(song.getString("name"));
            musicItemEntity.setMusicId(song.getString("id"));
            musicItemEntity.setAlbumId(album.getString("id"));
            musicItemEntity.setAlbumName(album.getString("name"));
            musicItemEntity.setArtistName(artist.getString("name"));
            musicItemEntity.setArtistId(artist.getString("id"));
            musicItemEntity.setDuration(song.getInteger("dt"));
            musicItemEntity.setExtraString(song.toJSONString());
            musicItemDao.save(musicItemEntity);

            JSONObject music_jsonObject = new JSONObject();
            music_jsonObject.put("album", song.getJSONObject(""));
            music_jsonObject.put("alia", song.getJSONArray("alia"));
            music_jsonObject.put("artist", song.getJSONArray("ar"));
            music_jsonObject.put("avatar", song.getJSONObject("al").getString("picUrl"));  // 歌曲图片地址
            int dt = song.getIntValue("dt");
            String string_dt = dt / 1000 + "." + dt % 1000;
            double duration = Double.parseDouble(string_dt);
            music_jsonObject.put("duration", duration);
            music_jsonObject.put("hot", song.getIntValue("pop"));
            music_jsonObject.put("id", song.getIntValue("id"));
            music_jsonObject.put("isfm", false);
            music_jsonObject.put("lyric", "");
            music_jsonObject.put("mvid", "");
            music_jsonObject.put("name", song.getString("name"));
            music_jsonObject.put("url", "");
            musicItemList.add(music_jsonObject);
        }
        return musicItemList;
    }
}
