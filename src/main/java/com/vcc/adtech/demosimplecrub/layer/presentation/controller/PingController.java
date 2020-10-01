package com.vcc.adtech.demosimplecrub.layer.presentation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/ping")
@RestController
public class PingController {

    private final Logger logger = LoggerFactory.getLogger(PingController.class);

    @GetMapping(produces = {MediaType.TEXT_PLAIN_VALUE})
    public String ping() {
        logger.debug("PING!");
        return "ping";
    }

}
