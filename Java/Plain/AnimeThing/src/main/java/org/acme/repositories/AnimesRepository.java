package org.acme.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import org.acme.POJO.AnimeAddRequest;
import org.acme.entitys.AnimesEntity;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class AnimesRepository implements PanacheRepositoryBase<AnimesEntity, Integer> {

    public List<AnimesEntity> searchForId(int id) {
        return list("id", id);
    }

    @Transactional
    public AnimesEntity addAnime(AnimeAddRequest request) {
        AnimesEntity anime = new AnimesEntity();
        anime.setCompleted(request.completed);
        anime.setDirectorId(request.directorId);
        anime.setEpisodes(request.episodes);
        anime.setLogoUrl(request.logoUrl);
        anime.setStudioId(request.studioId);
        anime.setWatchedEpisodes(request.watchedEpisodes);
        anime.setAnimeName(request.animeName);
        anime.setStudioName(request.studioName);

        if (!animeExists(request.animeName)) {
            System.out.println("Anime with the name " + request.animeName + " already exists.");
        }

        persist(anime);
        return anime;
    }

    @Transactional
    public void removeAnimeById(int id) {
        delete("id", id);
    }

    @Transactional
    public void nuke() {
        deleteAll();
    }

    public boolean animeExists(String animeName) {
        return count("animeName = ?1", animeName) > 0;
    }
}
