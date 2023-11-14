package org.acme.resources;

import org.acme.POJO.SeasonAddRequest;
import org.acme.entitys.SeasonsEntity;
import org.acme.entitys.StudioEntity;
import org.acme.repositories.SeasonsRepository;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.repositories.StudiosRepository;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Seasons")
@RequestScoped
@Path("/seasons")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SeasonsResource {

    @Inject
    SeasonsRepository seasonsRepository;

    @Inject
    StudiosRepository studiosRepository;

    @GET
    @Path("/get-by-id")
    public Response searchForId(@QueryParam("ID") int id) {
        return Response.ok(seasonsRepository.searchForId(id)).build();
    }


    @GET
    @Path("/list-all")
    public Response listAllSeasons() {
        List<SeasonsEntity> allSeasons = seasonsRepository.listAll();

        return Response.ok(allSeasons).build();
    }

    @GET
    @Path("/get-connected-studio")
    public Response getConnectedStudio(@QueryParam("seasonId") int seasonId) {
        StudioEntity connectedStudio = seasonsRepository.getConnectedStudioForSeason(seasonId);
        if (connectedStudio != null) {
            return Response.ok(connectedStudio).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Season or connected studio not found.").build();
        }
    }

    @POST
    @Path("/add")
    public Response addSeason(SeasonAddRequest request) {
        SeasonsEntity addedSeason = seasonsRepository.addSeason(request);

        if (addedSeason != null) {
            return Response.ok(addedSeason).build();
        } else {
            return Response.status(Response.Status.CONFLICT).entity("Season already exists.").build();
        }
    }

    @POST
    @Path("/remove-by-id")
    public Response removeSeason(@QueryParam("ID") int id) {
        seasonsRepository.removeSeasonById(id);
        return Response.ok().build();
    }

    @DELETE
    @Path("/nuke")
    public Response nukeDB() {
        seasonsRepository.nuke();
        return Response.ok().build();
    }
}
