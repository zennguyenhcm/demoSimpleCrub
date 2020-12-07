package com.vcc.adtech.demosimplecrub.layer.presentation.controller;

import com.vcc.adtech.demosimplecrub.layer.application.model.LoginPayload;
import com.vcc.adtech.demosimplecrub.layer.application.sevice.IAccountService;
import com.vcc.adtech.demosimplecrub.utility.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private IAccountService accountService;
    @PostMapping(path ="login", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> logIn(
            @RequestParam(value="email") String email,
            @RequestParam(value="password") String password,
            HttpServletRequest request){
        try {
            LoginPayload loginPayload = new LoginPayload();
            loginPayload.getAccountInfo().setEmail(email);
            loginPayload.getAccountInfo().setPassword(password);
            accountService.logIn(loginPayload);
            return Util.formatResponse(HttpStatus.OK, null, 1, "Login is successful!");
        }catch (Throwable e){
            String message = "Internal Server Error";
            return Util.formatResponse(HttpStatus.INTERNAL_SERVER_ERROR, null, 0, message);
        }
    }

}
