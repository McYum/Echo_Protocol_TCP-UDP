package org.acme.Excluded.POJO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StudioAddRequest {
    @JsonProperty("logoUrl")
    public String logoUrl;

    @JsonProperty("studioName")
    public String studioName;

    @Override
    public String toString() {
        return "StudioAddRequest{" +
                "Logo_Url=" + logoUrl +
                ", Studio_Name=" + studioName +
                '}';
    }
}