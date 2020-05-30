package com.zjgsu.service;

import com.zjgsu.dao.PlayListDao;
import com.zjgsu.dao.PlayListItemDao;
import com.zjgsu.entity.PlayListEntity;
import com.zjgsu.entity.PlayListItemEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayListService {
    @Autowired
    PlayListItemDao playListItemDao;
    @Autowired
    PlayListDao playListDao;

    /*
        创建播放列表
     */
    public String createPlayList(int user_id) {
        PlayListEntity playListEntity = new PlayListEntity();
        playListEntity.setUserId(user_id);

        return playListDao.save(playListEntity);
    }

    /*
        添加播放歌曲
     */
    public String addMusicToPlayList(int playlist_id, String music_id) {
        PlayListItemEntity playListItemEntity = new PlayListItemEntity();
        playListItemEntity.setPlayListId(playlist_id);
        playListItemEntity.setMusicId(music_id);
        return playListItemDao.save(playListItemEntity);
    }

}
