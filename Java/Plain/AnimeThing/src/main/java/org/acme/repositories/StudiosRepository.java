package org.acme.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.acme.POJO.SeasonAddRequest;
import org.acme.POJO.StudioAddRequest;
import org.acme.entitys.SeasonsEntity;
import org.acme.entitys.StudioEntity;

import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class StudiosRepository implements PanacheRepositoryBase<StudioEntity, Integer> {
    @Inject
    SeasonsRepository seasonsRepository;

    @Transactional
    public StudioEntity addStudio(StudioAddRequest request) {
        if (studioExists(request.studioName)) {
            System.out.println("Studio with the name " + request.studioName + " already exists.");
            return null;
        }

        StudioEntity studiosEntity = new StudioEntity();
        studiosEntity.setStudioName(request.studioName);
        studiosEntity.setLogoUrl(request.logoUrl);

        persist(studiosEntity);
        System.out.println("Added studio with the data " + request);
        return studiosEntity;
    }

    @Transactional
    public StudioEntity addSeasonToStudio(int studioId, SeasonAddRequest request) {
        StudioEntity studio = findById(studioId);
        if (studio != null) {
            SeasonsEntity seasonEntity = new SeasonsEntity();
            seasonEntity.setAnimeId(request.animeId);
            seasonEntity.setSeason(request.season);
            seasonEntity.setEpisodes(request.episode);
            seasonEntity.setStudioName(studio.getStudioName());
            seasonEntity.setStudio(studio);

            studio.addSeason(seasonEntity);

            persist((Iterable<StudioEntity>) seasonEntity);
            return studio;
        } else {
            System.out.println("Studio with ID " + studioId + " not found.");
            return null;
        }
    }

    public StudioEntity searchForId(int id) {
        System.out.println(find("id", id).firstResult());
        return findById(id);
    }

    @Transactional
    public List<SeasonsEntity> getAllSeasonsForStudio(int studioId) {
        StudioEntity studio = findById(studioId);
        System.out.println(studio.getSeasons());
        if (studio != null) {
            List<SeasonsEntity> seasons = studio.getSeasons();
            System.out.println("Seasons connected to Studio " + studioId + ": " + seasons);
            return seasons;
        } else {
            System.out.println("Studio with ID " + studioId + " not found.");
            return Collections.emptyList();
        }
    }

    @Transactional
    public void nuke() {
        deleteAll();
    }

    @Transactional
    public void removeSeasonById(int id) {
        StudioEntity studiosEntity = findById(id);
        if (studiosEntity != null) {
            delete(studiosEntity);
            System.out.println("Studio with ID " + id + " has been removed.");
        } else {
            System.out.println("Studio with ID " + id + " does not exist.");
        }
    }

    public boolean studioExists(String studioName) {
        return count("studioName", studioName) > 0;
    }

    public StudioEntity findByStudioName(String studioName) {
        System.out.println(find("studioName", studioName).firstResult().getSeasons());
        return find("studioName", studioName).firstResult();
    }
}
