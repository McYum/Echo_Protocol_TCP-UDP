package org.acme.Temp.DTO;

import org.acme.Temp.Entities.SeasonEntity;

public class SeasonWithStudioDTO {
    private Long seasonId;
    private Long studioId;
    private String seasonName;
    private String studioName;

    public SeasonWithStudioDTO() {
    }

    public SeasonWithStudioDTO(SeasonEntity season) {
        this.seasonId = season.getId();
        //this.studioId = season.getSeasonId();
        this.seasonName = season.getName();
        this.studioName = (season.getStudio() != null) ? season.getStudio().getName() : null;
    }

    public Long getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(Long seasonId) {
        this.seasonId = seasonId;
    }

    public Long getStudioId() {
        return studioId;
    }

    public void setStudioId(Long studioId) {
        this.studioId = studioId;
    }

    public String getSeasonName() {
        return seasonName;
    }

    public void setSeasonName(String seasonName) {
        this.seasonName = seasonName;
    }

    public String getStudioName() {
        return studioName;
    }

    public void setStudioName(String studioName) {
        this.studioName = studioName;
    }
}
