package com.example.hellowebapp;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api")
public class HelloApplication extends Application {
    @GET
    @Produces("text/plain")

    public String hello() {
        return "Sup";
    }
}