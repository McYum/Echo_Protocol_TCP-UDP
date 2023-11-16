package org.acme.Excluded.entitys;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "animes", schema = "sql11655762", catalog = "")
public class AnimesEntity {
    @Basic
    @Column(name = "Completed")
    private Integer completed;
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

}
