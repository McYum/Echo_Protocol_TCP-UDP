package org.acme.POJO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SeasonAddRequest {
    @JsonProperty("animeId")
    public int animeId;

    @JsonProperty("season")
    public int season;

    @JsonProperty("episode")
    public int episode;

    @JsonProperty("studioname")
    public String studioname;

    @Override
    public String toString() {
        return "SeasonAddRequest{" +
                "animeId=" + animeId +
                ", season=" + season +
                ", episode=" + episode +
                ", studioname=" + studioname +
                '}';
    }
}