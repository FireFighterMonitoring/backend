package com.jambit.feuermoni.config.jersey;


import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ParameterNameProvider;
import javax.ws.rs.ext.ContextResolver;

import org.glassfish.jersey.server.validation.ValidationConfig;


public class ValidationConfigContextResolver implements ContextResolver<ValidationConfig> {

    @Override
    public ValidationConfig getContext(Class<?> type) {
        ValidationConfig config = new ValidationConfig();
        config.parameterNameProvider(new RealParameterNameProvider());

        return config;
    }

    private static class RealParameterNameProvider implements ParameterNameProvider {

        @Override
        public List<String> getParameterNames(Constructor<?> constructor) {
            return getParameterNames(constructor.getParameters());
        }

        @Override
        public List<String> getParameterNames(Method method) {
            return getParameterNames(method.getParameters());
        }

        private List<String> getParameterNames(Parameter[] parameters) {
            return Arrays.asList(parameters).stream().map(Parameter::getName).collect(Collectors.toList());
        }
    }
}
