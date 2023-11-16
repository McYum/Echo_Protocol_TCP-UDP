package org.acme.Temp.Resources;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.Temp.DTO.SeasonWithStudioDTO;
import org.acme.Temp.Entities.SeasonEntity;
import org.acme.Temp.POJO.SeasonAddRequest;
import org.acme.Temp.Repositories.SeasonsRepository;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;

@Tag(name = "season")
@Path("/seasons")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SeasonsResource {

    @Inject
    SeasonsRepository seasonsRepository;

    @GET
    @Path("/{seasonId}")
    public Response getSeason(@PathParam("seasonId") Long seasonId) {
        SeasonEntity season = seasonsRepository.findSeasonById(seasonId);

        if (season != null) {
            SeasonWithStudioDTO responseDTO = new SeasonWithStudioDTO(season);
            return Response.ok(responseDTO).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Transactional
    public Response addSeason(SeasonAddRequest request) {
        SeasonEntity season = seasonsRepository.addSeason(request);
        return Response.status(Response.Status.CREATED).entity(season).build();
    }

    @DELETE
    @Path("/nuke")
    public void nukeDB() {
        seasonsRepository.nuke();
    }

    @DELETE
    @Path("/{seasonId}")
    @Transactional
    public Response removeSeason(@PathParam("seasonId") Long seasonId) {
        if (seasonsRepository.seasonExists(seasonId)) {
            seasonsRepository.removeSeasonById(seasonId);
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    public List<SeasonEntity> getAllSeasons() {
        return seasonsRepository.findAllSeasons();
    }
}