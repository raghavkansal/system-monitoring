package com.flysoft.systemMonitoring.model;

import jakarta.persistence.*;

@Entity
@Table(name = "settings")
public class Settings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String apiUrl;

    public Settings() {}

    public Settings(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public Long getId() { return id; }
    public String getApiUrl() { return apiUrl; }
    public void setApiUrl(String apiUrl) { this.apiUrl = apiUrl; }
}
