package com.revature.quizzard.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/health")
public class TestController {

    @GetMapping
    public Map<String, Object> healthCheck() {
        Map<String, Object> healthCheckResponse = new HashMap<>();
        healthCheckResponse.put("status", "UP");
        return healthCheckResponse;
    }

}
