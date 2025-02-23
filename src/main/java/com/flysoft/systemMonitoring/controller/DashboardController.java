package com.flysoft.systemMonitoring.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin(origins = "http://localhost:8080")
@Controller
public class DashboardController {

    @Value("${system.metrics.api.url}") // Read API URL from properties
    private String apiUrl;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("apiUrl", apiUrl); // Pass API URL to Thymeleaf
        return "index";
    }
}
