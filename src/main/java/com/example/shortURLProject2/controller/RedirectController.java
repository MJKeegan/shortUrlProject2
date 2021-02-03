package com.example.shortURLProject2.controller;

import com.example.shortURLProject2.entity.Redirect;
import com.example.shortURLProject2.request.RedirectCreationRequest;
import com.example.shortURLProject2.service.RedirectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class RedirectController {

    private RedirectService redirectService;

    @Autowired
    public RedirectController(RedirectService redirectService){
        this.redirectService = redirectService;
    }

    @GetMapping("/{alias}")
    public ResponseEntity<?> handleRequest(@PathVariable String alias) throws URISyntaxException {
        Redirect redirect = redirectService.getRedirect(alias);
        System.out.println("We're redirecting here!" + redirect);
        URI uri = new URI(redirect.getUrl());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uri);
        return new ResponseEntity<>(httpHeaders, HttpStatus.MOVED_PERMANENTLY);
    }

    @PostMapping("/")
    public ResponseEntity<?> createRequest(@Valid @RequestBody RedirectCreationRequest redirectCreationRequest){
        System.out.println("getting here");
        return ResponseEntity.ok(redirectService.createRedirect(redirectCreationRequest));
    }
}
