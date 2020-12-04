package com.vcc.adtech.demosimplecrub.layer.presentation.controller;

import com.vcc.adtech.demosimplecrub.layer.application.model.LoginPayload;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/user")
@RestController
public class UserController {
    @PostMapping(path ="login", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> logIn(@RequestBody LoginPayload loginPayload, HttpServletRequest request){

    }

}
