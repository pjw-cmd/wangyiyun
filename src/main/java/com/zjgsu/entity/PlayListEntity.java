package com.zjgsu.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "play_list", schema = "music_cloud", catalog = "")
public class PlayListEntity implements Serializable {
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
        return userId == that.userId &&
                Objects.equals(playListId, that.playListId) &&
                Objects.equals(playListName, that.playListName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, playListId, playListName);
    }
}
