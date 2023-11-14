package org.acme.entitys;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "studios", schema = "sql11655762", catalog = "")
public class StudioEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private int id;
    @Basic
    @Column(name = "Logo_Url")
    private String logoUrl;
    @Basic
    @Column(name = "Studio_Name")
    private String studioName;
    @Basic
    @Column(name = "Studio_Id")
    private Integer studioId;

    @OneToMany(mappedBy = "studio", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<SeasonsEntity> seasons;

    public StudioEntity() {
        this.seasons = new ArrayList<>();
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
        StudioEntity that = (StudioEntity) o;
        return id == that.id && Objects.equals(logoUrl, that.logoUrl) && Objects.equals(studioName, that.studioName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, logoUrl, studioName);
    }

    public Integer getStudioId() {
        return studioId;
    }

    public void setStudioId(Integer studioId) {
        this.studioId = studioId;
    }

    public void addSeason(SeasonsEntity season) throws InterruptedException {
        System.out.println("trying to connect season: " + season);
        System.out.println("season check " + seasons);

        if (seasons == null) {
            this.seasons = new ArrayList<>();
        }

        System.out.println("End of add season");
        /*
        if (seasons == null) {
            this.seasons = new ArrayList<>();
        }


        System.out.println("season check 2" + this.seasons);
        Thread.sleep(2500);
        seasons.add(season);
        //season.setStudio(this);
        */
    }

    public List<SeasonsEntity> getSeasons() {
        return seasons;
    }
}
