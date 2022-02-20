package com.example.cartracker.vehicle;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VehicleConfig {

  @Bean
  CommandLineRunner commandLineRunner(VehicleRepository repository) {
    return args -> {
      Vehicle pojazd_1 = new Vehicle(
        "Pojazd 1"
      );
      Vehicle pojazd_2 = new Vehicle(
        "Pojazd 2"
      );
      Vehicle pojazd1 = new Vehicle(
        "Pojazd1"
      );
      Vehicle mac = new Vehicle(
        "b8:27:eb:22:ec:d4"
      );
      Vehicle test_vehicle = new Vehicle(
        "Test Vehicle"
      );
      
      repository.saveAll(
        List.of(pojazd_1, pojazd_2, pojazd1, mac, test_vehicle)
      );
    };
  }
}
