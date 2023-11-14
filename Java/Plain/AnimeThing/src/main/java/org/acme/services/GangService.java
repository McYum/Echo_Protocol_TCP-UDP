package org.acme.services;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GangService {

    public String gangup(String name) {
        return "Ganging up on: " + name;
    }
}
