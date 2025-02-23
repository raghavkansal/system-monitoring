package com.flysoft.systemMonitoring.controller;

import com.flysoft.systemMonitoring.model.Settings;
import com.flysoft.systemMonitoring.repository.SettingsRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/settings")
public class SettingsController {

    private final SettingsRepository settingsRepository;

    public SettingsController(SettingsRepository settingsRepository) {
        this.settingsRepository = settingsRepository;
    }

    @GetMapping
    public String settingsPage(Model model) {
        model.addAttribute("settings", settingsRepository.findById(1L).orElse(new Settings()));
        return "settings";
    }

    @PostMapping
    public String saveSettings(@ModelAttribute Settings settings) {
        settingsRepository.save(settings);
        return "redirect:/";
    }
}
