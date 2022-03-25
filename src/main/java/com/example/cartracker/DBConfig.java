package com.example.cartracker;

import com.example.cartracker.Driver.Driver;
import com.example.cartracker.Driver.DriverRepository;
import com.example.cartracker.Owner.Owner;
import com.example.cartracker.Owner.OwnerRepository;
import com.example.cartracker.Timestamp.Timestamp;
import com.example.cartracker.vehicle.Vehicle;
import com.example.cartracker.vehicle.VehicleRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class DBConfig {

    @Bean
    CommandLineRunner setUpDatabase(
            VehicleRepository vehicleRepo,
            DriverRepository driverRepo,
            OwnerRepository ownerRepo
    ) {
        return args -> {
            Faker faker = new Faker();

            Driver albert = new Driver("Albert", "Szulc");

            Owner hubert = new Owner("hubert.szulc@gmail.pl", "AlbertMistrz", "Hubert", "Szulc");
            ownerRepo.save(hubert);

            Vehicle vehicle1 = new Vehicle("BMW Alberta", 52.4064, 16.9252, LocalDateTime.now());
            Vehicle vehicle2 = new Vehicle("Audi Alberta", 52.2297, 21.0122, LocalDateTime.now());

            Timestamp timestamp_v2_1 = new Timestamp(52.232629, 20.998186, LocalDateTime.now().minusMinutes(1));
            Timestamp timestamp_v2_2 = new Timestamp(52.230870, 20.987692, LocalDateTime.now().minusMinutes(2));
            Timestamp timestamp_v2_3 = new Timestamp(52.217045, 20.902012, LocalDateTime.now().minusMinutes(3));


            Timestamp timestamp_v1_1 = new Timestamp(52.411177, 16.932129, LocalDateTime.now().minusMinutes(1));
            Timestamp timestamp_v1_2 = new Timestamp(52.409868, 16.956302, LocalDateTime.now().minusMinutes(2));
            Timestamp timestamp_v1_3 = new Timestamp(52.410143, 16.998173, LocalDateTime.now().minusMinutes(3));


            vehicle1.addTimestamp(timestamp_v1_1);
            vehicle1.addTimestamp(timestamp_v1_2);
            vehicle1.addTimestamp(timestamp_v1_3);

            vehicle2.addTimestamp((timestamp_v2_1));
            vehicle2.addTimestamp((timestamp_v2_2));
            vehicle2.addTimestamp((timestamp_v2_3));

            albert.addVehicle(vehicle1);

            hubert.addVehicle(vehicle1);
            hubert.addVehicle(vehicle2);

            driverRepo.save(albert);
//            vehicleRepo.saveAll(List.of(
//                    vehicle1, vehicle2
//            ));
        };
    }
}
