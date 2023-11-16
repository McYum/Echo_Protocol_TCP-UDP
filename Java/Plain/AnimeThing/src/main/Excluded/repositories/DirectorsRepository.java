package org.acme.Excluded.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import org.acme.Excluded.POJO.DirectorsAddRequest;
import org.acme.Excluded.entitys.DirectorsEntity;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class DirectorsRepository implements PanacheRepositoryBase<DirectorsEntity, Integer> {

    @Transactional
    public DirectorsEntity addDirector(DirectorsAddRequest request) {
        if (directorExists(request.name)) {
            System.out.println("Director with the name " + request.name + " already exists.");
            return null;
        }

        DirectorsEntity directorsEntity = new DirectorsEntity();
        directorsEntity.setName(request.name);

        persist(directorsEntity);
        System.out.println("Added director with the data " + request);
        return directorsEntity;
    }

    @Transactional
    public void removeDirectorById(int id) {
        delete("id", id);
    }

    @Transactional
    public void nuke() {
        deleteAll();
    }

    private boolean directorExists(String name) {
        return count("name = ?1", name) > 0;
    }
}
