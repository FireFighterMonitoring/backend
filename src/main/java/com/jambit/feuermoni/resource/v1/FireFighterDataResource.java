package com.jambit.feuermoni.resource.v1;


import java.net.URI;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.jambit.feuermoni.model.FireFighterData;
import com.jambit.feuermoni.service.FireFighterDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@Path("data")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FireFighterDataResource {

    private final FireFighterDataService service;

    @Autowired
    public FireFighterDataResource(FireFighterDataService service) {
        this.service = service;
    }

    @POST
    @Valid
    public Response save(
            @Context UriInfo uriInfo,
            @NotNull @Valid FireFighterData data) {
        data = service.save(data);

        URI locationUri = uriInfo
                .getAbsolutePathBuilder()
                .segment(String.valueOf(data.getId()))
                .build();

        return Response.created(locationUri).entity(data).build();
    }

    @GET
    @NotNull
    @Valid
    public Iterable<FireFighterData> loadLatest() {
        return service.loadLatest();
    }
}
