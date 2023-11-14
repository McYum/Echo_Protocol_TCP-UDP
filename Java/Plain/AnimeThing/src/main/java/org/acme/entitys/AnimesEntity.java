package org.acme.entitys;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "animes", schema = "sql11655762", catalog = "")
public class AnimesEntity {
    @Basic
    @Column(name = "Completed")
    private Integer completed;
    @Basic
    @Column(name = "Director_Id")
    private Integer directorId;
    @Basic
    @Column(name = "Episodes")
    private Integer episodes;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private int id;
    @Basic
    @Column(name = "Logo_Url")
    private String logoUrl;
    @Basic
    @Column(name = "Studio_Id")
    private Integer studioId;
    @Basic
    @Column(name = "Watched_episodes")
    private Integer watchedEpisodes;
    @Basic
    @Column(name = "Anime_Name")
    private String animeName;
    @Basic
    @Column(name = "Studio_Name")
    private String studioName;

    public Integer getCompleted() {
        return completed;
    }

    public void setCompleted(Integer completed) {
        this.completed = completed;
    }

    public Integer getDirectorId() {
        return directorId;
    }

    public void setDirectorId(Integer directorId) {
        this.directorId = directorId;
    }

    public Integer getEpisodes() {
        return episodes;
    }

    public void setEpisodes(Integer episodes) {
        this.episodes = episodes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public Integer getStudioId() {
        return studioId;
    }

    public void setStudioId(Integer studioId) {
        this.studioId = studioId;
    }

    public Integer getWatchedEpisodes() {
        return watchedEpisodes;
    }

    public void setWatchedEpisodes(Integer watchedEpisodes) {
        this.watchedEpisodes = watchedEpisodes;
    }

    public String getAnimeName() {
        return animeName;
    }

    public void setAnimeName(String animeName) {
        this.animeName = animeName;
    }

    public String getStudioName() {
        return studioName;
    }

    public void setStudioName(String studioName) {
        this.studioName = studioName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnimesEntity that = (AnimesEntity) o;
        return id == that.id && Objects.equals(completed, that.completed) && Objects.equals(directorId, that.directorId) && Objects.equals(episodes, that.episodes) && Objects.equals(logoUrl, that.logoUrl) && Objects.equals(studioId, that.studioId) && Objects.equals(watchedEpisodes, that.watchedEpisodes) && Objects.equals(animeName, that.animeName) && Objects.equals(studioName, that.studioName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(completed, directorId, episodes, id, logoUrl, studioId, watchedEpisodes, animeName, studioName);
    }

}
