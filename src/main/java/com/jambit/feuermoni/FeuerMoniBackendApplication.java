package com.jambit.feuermoni;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


@EnableAutoConfiguration
@ComponentScan
public class FeuerMoniBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeuerMoniBackendApplication.class, args);
    }
}
