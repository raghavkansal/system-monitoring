package com.flysoft.systemMonitoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SystemMonitoringApplication {

	public static void main(String[] args) {
		SpringApplication.run(SystemMonitoringApplication.class, args);
		System.out.println("Hello world system monitoring!");
	}

}
