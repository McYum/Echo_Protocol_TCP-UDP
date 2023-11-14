package org.acme.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.Map;
import java.util.List;
import java.util.HashMap;

@ApplicationScoped
public class AnimeService {
    @Inject
    EntityManager em;

    /*
    @Transactional
    public String addAnime(Map<String, Object> attributes) {
        String animeName = (String) attributes.get("Anime_Name");

        if (animeExists(animeName)) {
            System.out.println("Anime with the name " + animeName + " already exists.");
            return null;
        }

        AnimesEntity anime = new AnimesEntity();
        System.out.println("Adding anime");

        for (Map.Entry<String, Object> entry : attributes.entrySet()) {
            String attribute = entry.getKey();
            Object value = entry.getValue();

            switch (attribute) {
                case "Anime_Name":
                    anime.setAnimeName((String) value);
                    break;
                case "Studio_Name":
                    anime.setStudioName((String) value);
                    break;
                case "Completed":
                    anime.setCompleted((Integer) value);
                    break;
                case "Director_Id":
                    anime.setDirectorId((Integer) value);
                    break;
                case "Studio_Id":
                    anime.setStudioId((Integer) value);
                    break;
                case "Watched_Episodes":
                    anime.setWatchedEpisodes((Integer) value);
                    break;
                case "Episodes":
                    anime.setEpisodes((Integer) value);
                    break;
                case "Logo_Url":
                    anime.setLogoUrl((String) value);
                    break;
                default:
                    System.out.println("Unknown attribute: " + attribute);
            }
        }

        em.persist(anime);

        System.out.println("Added anime");
        return "Anime with the name " + animeName + " has been added.";
    }

    @Transactional
    public String deleteAnime(String animeName) {
        if (!animeExists(animeName)) {
            System.out.println("Anime with the name " + animeName + " does not exist.");
            return null;
        }

        List<AnimesEntity> animes = em.createQuery(
                        "SELECT a FROM AnimesEntity a WHERE a.animeName = :animeName", AnimesEntity.class)
                .setParameter("animeName", animeName)
                .getResultList();

        for (AnimesEntity anime : animes) {
            em.remove(anime);
        }

        System.out.println("Anime with the name " + animeName + " has been deleted.");
        return null;
    }

    @Transactional
    public Map<String, Object> getAttributesByName(String animeName) {
        if (!animeExists(animeName)) {
            System.out.println(animeName + " does not exist.");
            return null;
        }
        AnimesEntity anime = em.createQuery(
                        "SELECT a FROM AnimesEntity a WHERE a.animeName = :animeName", AnimesEntity.class)
                .setParameter("animeName", animeName)
                .getSingleResult();

        Map<String, Object> attributes = new HashMap<>();
        attributes.put("Anime_Name", anime.getAnimeName());
        attributes.put("Studio_Name", anime.getStudioName());
        attributes.put("Completed", anime.getCompleted());
        attributes.put("Director_Id", anime.getDirectorId());
        attributes.put("Studio_Id", anime.getStudioId());
        attributes.put("Watched_Episodes", anime.getWatchedEpisodes());
        attributes.put("Episodes", anime.getEpisodes());
        attributes.put("Logo_Url", anime.getLogoUrl());

        return attributes;
    }

    private boolean animeExists(String animeName) {
        List<AnimesEntity> existingAnimes = em.createQuery(
                        "SELECT a FROM AnimesEntity a WHERE a.animeName = :animeName", AnimesEntity.class)
                .setParameter("animeName", animeName)
                .getResultList();

        return !existingAnimes.isEmpty();
    }
     */
}
