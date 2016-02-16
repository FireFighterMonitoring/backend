package com.jambit.feuermoni.config.jersey;


import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public abstract class AbstractExceptionMapper<T extends Throwable> implements ExceptionMapper<T> {

    @Override
    public Response toResponse(T exception) {
        log.error("Error encountered", exception);

        return Response
                .status(getStatus())
                .entity(new ErrorInfo(exception.getMessage()))
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }

    protected abstract Response.Status getStatus();
}
