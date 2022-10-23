package com.drone.dispatcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class DroneDispatcherApplication {

	public static void main(String[] args) {
		SpringApplication.run(DroneDispatcherApplication.class, args);
	}
}
