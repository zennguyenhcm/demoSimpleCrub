package com.vcc.adtech.demosimplecrub;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class MyApplication implements CommandLineRunner {

    public static void main(String[] args) {
        WebApplicationType webType = WebApplicationType.SERVLET;

        try {
            new SpringApplicationBuilder(MyApplication.class).web(webType)
                    .registerShutdownHook(true).run(args);
            System.out.println("Starting server ..............");
        } catch (Exception e) {
            System.out.println("Error: "+e);
        }
    }

    @Override
    public void run(String... args) throws Exception {

    }

}