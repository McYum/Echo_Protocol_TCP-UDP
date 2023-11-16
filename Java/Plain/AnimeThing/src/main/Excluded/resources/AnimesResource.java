package org.acme.Excluded.resources;

import org.acme.Excluded.POJO.AnimeAddRequest;
import org.acme.Excluded.entitys.AnimesEntity;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.Excluded.repositories.AnimesRepository;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Animes")
@Path("/animes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class AnimesResource {

    @Inject
    AnimesRepository animesRepository;

    @GET
    @Path("/get-by-id")
    public Response searchForId(@QueryParam("ID") int ID) {
        return Response.ok(animesRepository.searchForId(ID)).build();
    }

    @GET
    @Path("/list-all")
    public List<AnimesEntity> listAll() {
        return animesRepository.listAll();
    }

    @POST
    @Path("/add")
    public Response addAnime(AnimeAddRequest request) {
        return Response.ok(animesRepository.addAnime(request)).build();
    }

    @POST
    @Path("/remove-by-id")
    public Response removeAnime(@QueryParam("ID") int ID) {
        animesRepository.removeAnimeById(ID);
        return Response.ok().build();
    }

    @DELETE
    @Path("/nuke")
    public void nukeDB() {
        animesRepository.nuke();
    }
}
