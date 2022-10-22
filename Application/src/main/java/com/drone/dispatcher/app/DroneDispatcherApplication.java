package com.drone.dispatcher.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.drone.dispatcher.*")
public class DroneDispatcherApplication {

	public static void main(String[] args) {
		SpringApplication.run(DroneDispatcherApplication.class, args);
	}
}
