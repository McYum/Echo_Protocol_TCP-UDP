package org.acme.POJO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DirectorsAddRequest {
    @JsonProperty("name")
    public String name;

    @Override
    public String toString() {
        return "DirectorsAddRequest{" +
                "name='" + name + '\'' +
                '}';
    }
}
