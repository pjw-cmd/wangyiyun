package com.zjgsu.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "play_list_item", schema = "music_cloud", catalog = "")
public class PlayListItemEntity implements Serializable {
    private String playListId;
    private String musicId;
    private Integer isDelete;
    private Timestamp createTime;

    @Id
    @Basic
    @Column(name = "play_list_id")
    public String getPlayListId() {
        return playListId;
    }

    public void setPlayListId(String playListId) {
        this.playListId = playListId;
    }

    @Basic
    @Column(name = "music_id")
    public String getMusicId() {
        return musicId;
    }

    public void setMusicId(String musicId) {
        this.musicId = musicId;
    }

    @Basic
    @Column(name = "is_delete")
    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayListItemEntity that = (PlayListItemEntity) o;
        return playListId == that.playListId &&
                Objects.equals(musicId, that.musicId) &&
                Objects.equals(isDelete, that.isDelete) &&
                Objects.equals(createTime, that.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playListId, musicId, isDelete, createTime);
    }
}
