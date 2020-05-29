package com.zjgsu.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "play_list_item", schema = "music_cloud", catalog = "")
public class PlayListItemEntity {
    private int playListId;
    private String musicId;
    private Integer isDelete;
    private Timestamp createTime;

    @Id
    @Column(name = "play_list_id")
    public int getPlayListId() {
        return playListId;
    }

    public void setPlayListId(int playListId) {
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

        if (playListId != that.playListId) return false;
        if (musicId != null ? !musicId.equals(that.musicId) : that.musicId != null) return false;
        if (isDelete != null ? !isDelete.equals(that.isDelete) : that.isDelete != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = playListId;
        result = 31 * result + (musicId != null ? musicId.hashCode() : 0);
        result = 31 * result + (isDelete != null ? isDelete.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        return result;
    }
}
