package org.acme.entitys;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "seasons", schema = "sql11655762", catalog = "")
public class SeasonsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private int id;
    @Basic
    @Column(name = "Anime_Id")
    private Integer animeId;
    @Basic
    @Column(name = "Season")
    private Integer season;
    @Basic
    @Column(name = "Episodes")
    private Integer episodes;
    @Basic
    @Column(name = "Studio_Name")
    private String studioName;
    @Column(name = "Studio_Id")
    private Integer studioId;

    @JoinColumn(name = "Studio_Id", referencedColumnName = "Studio_Id", insertable = false, updatable = false)
    @ManyToOne(cascade = CascadeType.PERSIST)
    private StudioEntity studio;

    public StudioEntity getStudio() {
        return studio;
    }

    public void setStudio(StudioEntity studios) {
        this.studio = studios;
        studios.addSeason(this);
        /*
        if (studios != null) {
            studios.getSeasons().add(this);
        }*/
        System.out.println(studios.getSeasons());
        System.out.println("Connected studio: " + getStudio());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getAnimeId() {
        return animeId;
    }

    public void setAnimeId(Integer animeId) {
        this.animeId = animeId;
    }

    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    public Integer getEpisodes() {
        return episodes;
    }

    public void setEpisodes(Integer episodes) {
        this.episodes = episodes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SeasonsEntity that = (SeasonsEntity) o;
        return id == that.id && Objects.equals(animeId, that.animeId) && Objects.equals(season, that.season) && Objects.equals(episodes, that.episodes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, animeId, season, episodes);
    }

    public String getStudioName() {
        return studioName;
    }

    public void setStudioName(String studioName) {
        this.studioName = studioName;
    }

    public Integer getStudioId() {
        return studioId;
    }

    public void setStudioId(Integer studioId) {
        System.out.println("Set studio Id to: " + studioId);
        this.studioId = studioId;
    }
}
