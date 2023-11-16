package org.acme.Temp.Repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import org.acme.Temp.Entities.StudioEntity;
import org.acme.Temp.Entities.SeasonEntity;
import org.acme.Temp.POJO.SeasonAddRequest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class SeasonsRepository implements PanacheRepositoryBase<SeasonEntity, Long> {

    public SeasonEntity findSeasonById(Long seasonId) {
        SeasonEntity season = findById(seasonId);

        return season;
    }

    public List<SeasonEntity> findAllSeasons() {
        return listAll();
    }

    @Transactional
    public SeasonEntity addSeason(SeasonAddRequest request) {
        long studioId = request.studioId;

        StudioEntity studio = StudioEntity.findById(studioId);
        if (studio == null) {
            System.out.println("Studio with Id[" + request.studioId + "] not found");
            return null;
        }

        SeasonEntity season = new SeasonEntity();
        //season.setSeasonId(studioId);
        season.setName(request.name);
        season.setStudio(studio);

        persist(season);

        return season;
    }

    @Transactional
    public void removeSeasonById(Long seasonId) {
        deleteById(seasonId);
    }

    @Transactional
    public void nuke() {
        deleteAll();
    }

    public boolean seasonExists(Long seasonId) {
        return count("id = ?1", seasonId) > 0;
    }
}
