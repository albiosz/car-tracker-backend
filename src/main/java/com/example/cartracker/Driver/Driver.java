package com.example.cartracker.Driver;

import com.example.cartracker.vehicle.Vehicle;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Driver")
@Table(name = "driver")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Driver {

    @Id
    @SequenceGenerator(
            name = "driver_id_seq",
            sequenceName = "driver_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "driver_id_seq"
    )
    @Column(
            name = "id",
            nullable = false
    )
    public Long Id;

    @Column(
            name = "first_name",
            nullable = false
    )
    private String firstName;

    @Column(
            name = "last_name",
            nullable = false
    )
    private String lastName;

    @OneToMany(
            mappedBy = "driver",
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Vehicle> vehicles = new ArrayList<>();

    public Driver(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void addVehicle(Vehicle vehicle) {
        if(!vehicles.contains(vehicle)) {
            vehicles.add(vehicle);
            vehicle.setDriver(this);
        }
    }

    public void removeVehicle(Vehicle vehicle) {
        if(vehicles.contains(vehicle)) {
            vehicles.remove(vehicle);
            vehicle.setDriver(this);
        }
    }
}
