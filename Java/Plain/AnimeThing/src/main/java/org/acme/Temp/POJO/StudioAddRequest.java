package org.acme.Temp.POJO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StudioAddRequest {
    @JsonProperty("name")
    public String name;

    @Override
    public String toString() {
        return "StudioAddRequest{" +
                "name=" + name +
                '}';
    }
}
