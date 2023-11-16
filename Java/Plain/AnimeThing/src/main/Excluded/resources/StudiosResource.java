package org.acme.Excluded.resources;

import org.acme.Excluded.entitys.StudioEntity;
import org.acme.Excluded.POJO.StudioAddRequest;
import org.acme.Excluded.entitys.SeasonsEntity;
import org.acme.Excluded.repositories.StudiosRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Studios")
@Path("/studios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class StudiosResource {

    @Inject
    StudiosRepository studiosRepository;

    @GET
    @Path("/get-by-id")
    public Response searchForId(@QueryParam("ID") int id) {
        return Response.ok(studiosRepository.searchForId(id)).build();
    }

    @GET
    @Path("/find-by-name")
    public Response findByStudioName(@QueryParam("studioName") String studioName) {
        StudioEntity studiosEntity = studiosRepository.findByStudioName(studioName);
        if (studiosEntity != null) {
            return Response.ok(studiosEntity).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/list-all")
    public Response listAllStudios() {
        List<StudioEntity> allStudios = studiosRepository.listAll();

        for (StudioEntity studio : allStudios) {
            studio.getSeasons().size();
        }

        return Response.ok(allStudios).build();
    }

    @GET
    @Path("/get-all-seasons")
    public Response getAllSeasonsForStudio(@QueryParam("studioId") int studioId) {
        List<SeasonsEntity> seasons = studiosRepository.getAllSeasonsForStudio(studioId);
        if (!seasons.isEmpty()) {
            return Response.ok(seasons).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("No seasons found for Studio with ID " + studioId).build();
        }
    }

    @POST
    @Path("/add")
    public Response addStudio(StudioAddRequest request) {
        return Response.ok(studiosRepository.addStudio(request)).build();
    }

    @POST
    @Path("/remove-by-id")
    public Response removeStudio(@QueryParam("ID") int id) {
        studiosRepository.removeSeasonById(id);
        return Response.ok().build();
    }

    @DELETE
    @Path("/nuke")
    public void nukeDB() {
        studiosRepository.nuke();
    }
}
