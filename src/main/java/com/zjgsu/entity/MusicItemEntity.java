package com.zjgsu.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "music_item", schema = "music_cloud", catalog = "")
public class MusicItemEntity {
    private String musicId;
    private String name;
    private String artistName;
    private String albumName;
    private String artistId;
    private String albumId;
    private Integer duration;
    private String extraString;

    @Id
    @Column(name = "music_id")
    public String getMusicId() {
        return musicId;
    }

    public void setMusicId(String musicId) {
        this.musicId = musicId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "artist_name")
    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    @Basic
    @Column(name = "album_name")
    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    @Basic
    @Column(name = "artist_id")
    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    @Basic
    @Column(name = "album_id")
    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    @Basic
    @Column(name = "duration")
    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @Basic
    @Column(name = "extra_string")
    public String getExtraString() {
        return extraString;
    }

    public void setExtraString(String extraString) {
        this.extraString = extraString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MusicItemEntity that = (MusicItemEntity) o;
        return Objects.equals(musicId, that.musicId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(artistName, that.artistName) &&
                Objects.equals(albumName, that.albumName) &&
                Objects.equals(artistId, that.artistId) &&
                Objects.equals(albumId, that.albumId) &&
                Objects.equals(duration, that.duration) &&
                Objects.equals(extraString, that.extraString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(musicId, name, artistName, albumName, artistId, albumId, duration, extraString);
    }
}
