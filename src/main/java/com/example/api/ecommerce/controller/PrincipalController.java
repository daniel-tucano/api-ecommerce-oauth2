package com.example.api.ecommerce.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrincipalController {

    @GetMapping("/principal")
    public String apresentaPrincipal(@AuthenticationPrincipal OAuth2AuthenticatedPrincipal principal) {
        return  principal.toString();
    }

}
