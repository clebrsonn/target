package br.com.hyteck.platform.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TargetController {


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public void status() {
    }
}
