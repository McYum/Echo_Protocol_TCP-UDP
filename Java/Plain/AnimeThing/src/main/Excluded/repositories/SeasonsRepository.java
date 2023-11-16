package org.acme.Excluded.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.inject.Inject;
import org.acme.Excluded.POJO.SeasonAddRequest;
import org.acme.Excluded.entitys.SeasonsEntity;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.acme.Excluded.entitys.StudioEntity;

@ApplicationScoped
public class SeasonsRepository implements PanacheRepositoryBase<SeasonsEntity, Integer> {
    @Inject
    StudiosRepository studiosRepository;

    @Transactional
    public SeasonsEntity addSeason(SeasonAddRequest request) throws InterruptedException {
        System.out.println("Received JSON Request: " + request.toString());
        System.out.println(request.animeId);

        if (!studioExists(request.studioname)) {
            System.out.println("Studio with name " + request.studioname + " does not exist.");
            return null;
        }

        if (seasonExists(request.animeId, request.season)) {
            System.out.println("Season with Anime_Id " + request.animeId + " and Season " + request.season + " already exists.");
            return null;
        }

        StudioEntity studio = studiosRepository.findByStudioName(request.studioname);
        if (studio == null) {
            System.out.println("Error fetching Studio with name " + request.studioname);
            return null;
        }

        SeasonsEntity seasonEntity = new SeasonsEntity();
        seasonEntity.setAnimeId(request.animeId);
        seasonEntity.setSeason(request.season);
        seasonEntity.setEpisodes(request.episode);
        seasonEntity.setStudioName(request.studioname);
        seasonEntity.setStudio(studio);
        seasonEntity.setStudioId(studio.getId());

        persist(seasonEntity);

        SeasonsEntity persistedSeason = findById(seasonEntity.getId());
        System.out.println("Persisted Season: " + persistedSeason);
        System.out.println("Logo_Url of connected studio: " + persistedSeason.getStudio().getLogoUrl());

        System.out.println("End of addSeason");
        return seasonEntity;
    }

    public SeasonsEntity searchForId(int id) {
        System.out.println(find("id", id).firstResult());
        return find("id", id).firstResult();
    }

    @Transactional
    public void removeSeasonById(int id) {
        delete("id", id);
    }

    @Transactional
    public void nuke() {
        deleteAll();
    }

    @Transactional
    public StudioEntity getConnectedStudioForSeason(int seasonId) {
        SeasonsEntity season = findById(seasonId);
        if (season != null) {
            Integer connectedStudioId = season.getStudioId();
            if (connectedStudioId != null) {
                StudioEntity connectedStudio = studiosRepository.findById(connectedStudioId);
                System.out.println("Connected Studio for Season " + seasonId + ": " + connectedStudio);
                return connectedStudio;
            } else {
                System.out.println("Studio ID is null for Season " + seasonId);
            }
        } else {
            System.out.println("Season with ID " + seasonId + " not found.");
        }
        return null;
    }

    private boolean seasonExists(int animeId, int season) {
        return count("animeId = ?1 and season = ?2", animeId, season) > 0;
    }

    private boolean studioExists(String studioName) {
        return studiosRepository.studioExists(studioName);
    }
}
