package com.flysoft.systemMonitoring.repository;

import com.flysoft.systemMonitoring.model.Settings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SettingsRepository extends JpaRepository<Settings, Long> {
}
