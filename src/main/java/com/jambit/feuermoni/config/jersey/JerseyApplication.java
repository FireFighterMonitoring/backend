package com.jambit.feuermoni.config.jersey;


import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.message.DeflateEncoder;
import org.glassfish.jersey.message.GZipEncoder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.filter.EncodingFilter;
import org.springframework.stereotype.Component;


@Component
@ApplicationPath("api/v1")
public class JerseyApplication extends ResourceConfig {

    public JerseyApplication() {
        packages("com.jambit.feuermoni.resource");

        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);

        register(ValidationConfigContextResolver.class);
        EncodingFilter.enableFor(this, GZipEncoder.class, DeflateEncoder.class);
    }
}
