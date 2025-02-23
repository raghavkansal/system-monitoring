package com.flysoft.systemMonitoring.service;

import com.flysoft.systemMonitoring.model.Settings;
import com.flysoft.systemMonitoring.repository.SettingsRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Optional;

@Service
public class MetricsService {

    private final SettingsRepository settingsRepository;
    private final RestTemplate restTemplate;

    public MetricsService(SettingsRepository settingsRepository, RestTemplate restTemplate) {
        this.settingsRepository = settingsRepository;
        this.restTemplate = restTemplate;
    }

    public String getSystemMetrics() {
        Optional<Settings> settings = settingsRepository.findById(1L);
        if (settings.isPresent()) {
            String apiUrl = settings.get().getApiUrl();
            return restTemplate.getForObject(apiUrl, String.class);
        }
        return "{}"; // Return empty JSON if no API URL is set
    }
}
