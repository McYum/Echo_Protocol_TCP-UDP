package org.acme.Temp.Repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import org.acme.Temp.Entities.StudioEntity;
import org.acme.Temp.Entities.SeasonEntity;
import org.acme.Temp.POJO.StudioAddRequest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class StudioRepository implements PanacheRepositoryBase<StudioEntity, Long> {

    public StudioEntity findStudioById(Long studioId) {
        return findById(studioId);
    }

    public List<StudioEntity> findAllStudios() {
        return listAll();
    }

    @Transactional
    public StudioEntity addStudio(StudioAddRequest request) {
        StudioEntity studio = new StudioEntity();
        studio.setName(request.name);

        persist(studio);
        return studio;
    }

    @Transactional
    public void removeStudioById(Long studioId) {
        deleteById(studioId);
    }

    @Transactional
    public void nuke() {
        deleteAll();
    }

    public boolean studioExists(Long studioId) {
        return count("id = ?1", studioId) > 0;
    }

    public List<SeasonEntity> findSeasonsByStudioId(Long studioId) {
        StudioEntity studio = findById(studioId);
        if (studio != null) {
            return studio.getSeasonsById().stream().collect(Collectors.toList());
        } else {
            return null;
        }
    }
}