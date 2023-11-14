package org.acme.POJO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AnimeAddRequest {
    @JsonProperty("completed")
    public Integer completed;

    @JsonProperty("directorId")
    public Integer directorId;

    @JsonProperty("episodes")
    public Integer episodes;

    @JsonProperty("logoUrl")
    public String logoUrl;

    @JsonProperty("studioId")
    public Integer studioId;

    @JsonProperty("watchedEpisodes")
    public Integer watchedEpisodes;

    @JsonProperty("animeName")
    public String animeName;

    @JsonProperty("studioName")
    public String studioName;


    @Override
    public String toString() {
        return "AnimeAddRequest{" +
                "completed=" + completed +
                ", directorId=" + directorId +
                ", episodes=" + episodes +
                ", logoUrl='" + logoUrl + '\'' +
                ", studioId=" + studioId +
                ", watchedEpisodes=" + watchedEpisodes +
                ", animeName='" + animeName + '\'' +
                ", studioName='" + studioName + '\'' +
                '}';
    }
}
