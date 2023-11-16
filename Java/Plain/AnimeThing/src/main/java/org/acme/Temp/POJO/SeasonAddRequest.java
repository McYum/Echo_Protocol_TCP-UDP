package org.acme.Temp.POJO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SeasonAddRequest {
    @JsonProperty("studioId")
    public long studioId;

    @JsonProperty("name")
    public String name;

    @Override
    public String toString() {
        return "SeasonsAddRequest{" +
                "studioId=" + studioId +
                "name=" + name +
                '}';
    }
}
