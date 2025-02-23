package com.flysoft.systemMonitoring.controller;

import com.flysoft.systemMonitoring.service.MetricsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MetricsController {

    private final MetricsService metricsService;

    public MetricsController(MetricsService metricsService) {
        this.metricsService = metricsService;
    }

    @GetMapping("/system-metrics")
    public String getSystemMetrics() {
        return metricsService.getSystemMetrics();
    }
}
