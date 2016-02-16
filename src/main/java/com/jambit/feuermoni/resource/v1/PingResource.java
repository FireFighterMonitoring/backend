package com.jambit.feuermoni.resource.v1;


import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("ping")
@Produces(MediaType.TEXT_PLAIN)
public class PingResource {

    @GET
    @NotNull
    public String ping() {
        return "pong";
    }
}
