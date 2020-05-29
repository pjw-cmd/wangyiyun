package com.zjgsu.entity;

import javax.persistence.*;

@Entity
@Table(name = "play_list", schema = "music_cloud", catalog = "")
public class PlayListEntity {
    private int userId;
    private Integer playListId;
    private String playListName;

    @Id
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "play_list_id")
    public Integer getPlayListId() {
        return playListId;
    }

    public void setPlayListId(Integer playListId) {
        this.playListId = playListId;
    }

    @Basic
    @Column(name = "play_list_name")
    public String getPlayListName() {
        return playListName;
    }

    public void setPlayListName(String playListName) {
        this.playListName = playListName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayListEntity that = (PlayListEntity) o;

        if (userId != that.userId) return false;
        if (playListId != null ? !playListId.equals(that.playListId) : that.playListId != null) return false;
        if (playListName != null ? !playListName.equals(that.playListName) : that.playListName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (playListId != null ? playListId.hashCode() : 0);
        result = 31 * result + (playListName != null ? playListName.hashCode() : 0);
        return result;
    }
}
