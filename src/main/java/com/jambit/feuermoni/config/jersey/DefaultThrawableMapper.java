package com.jambit.feuermoni.config.jersey;


import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;


@Provider
public class DefaultThrawableMapper extends AbstractExceptionMapper<Throwable> {

    @Override
    protected Response.Status getStatus() {
        return Response.Status.INTERNAL_SERVER_ERROR;
    }
}
