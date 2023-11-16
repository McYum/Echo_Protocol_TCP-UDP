package org.acme.Temp.Resources;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.Temp.Entities.StudioEntity;
import org.acme.Temp.Entities.SeasonEntity;
import org.acme.Temp.POJO.StudioAddRequest;
import org.acme.Temp.Repositories.StudioRepository;
import org.acme.Temp.Repositories.SeasonsRepository;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;

@Tag(name = "studio")
@Path("/studio")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StudioResource {
    @Inject
    StudioRepository studioRepository;

    @Inject
    SeasonsRepository seasonsRepository;

    @GET
    @Path("/{studioId}")
    public Response getStudio(@PathParam("studioId") Long studioId) {
        StudioEntity studio = studioRepository.findStudioById(studioId);
        if (studio != null) {
            return Response.ok(studio).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    public List<StudioEntity> getAllStudios() {
        return studioRepository.findAllStudios();
    }

    @GET
    @Path("/{studioId}/seasons")
    public Response getSeasonsForStudio(@PathParam("studioId") Long studioId) {
        List<SeasonEntity> seasons = studioRepository.findSeasonsByStudioId(studioId);
        if (seasons != null) {
            return Response.ok(seasons).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Transactional
    public Response addStudio(StudioAddRequest request) {
        StudioEntity studio = studioRepository.addStudio(request);

        return Response.status(Response.Status.CREATED).entity(studio).build();
    }

    @DELETE
    @Path("/{studioId}")
    @Transactional
    public Response removeStudio(@PathParam("studioId") Long studioId) {
        if (studioRepository.studioExists(studioId)) {
            studioRepository.removeStudioById(studioId);
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/nuke")
    public void nukeDB() {
        studioRepository.nuke();
    }
}
