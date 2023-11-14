package org.acme.resources;

import org.acme.POJO.DirectorsAddRequest;
import org.acme.entitys.DirectorsEntity;
import org.acme.repositories.DirectorsRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Directors")
@Path("/directors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class DirectorsResource {

    @Inject
    DirectorsRepository directorsRepository;

    @GET
    @Path("/get-by-id")
    public Response searchForId(@QueryParam("ID") int id) {
        return Response.ok(directorsRepository.findById(id)).build();
    }

    @GET
    @Path("/list-all")
    public Response listAllDirectors() {
        List<DirectorsEntity> allDirectors = directorsRepository.listAll();
        return Response.ok(allDirectors).build();
    }

    @POST
    @Path("/add")
    public Response addDirector(DirectorsAddRequest request) {
        return Response.ok(directorsRepository.addDirector(request)).build();
    }

    @POST
    @Path("/remove-by-id")
    public Response removeDirector(@QueryParam("ID") int id) {
        directorsRepository.removeDirectorById(id);
        return Response.ok().build();
    }

    @DELETE
    @Path("/nuke")
    public void nukeDB() {
        directorsRepository.nuke();
    }
}
