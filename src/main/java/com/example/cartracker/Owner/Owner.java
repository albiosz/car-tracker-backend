package com.example.cartracker.Owner;

import com.example.cartracker.vehicle.Vehicle;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Owner")
@Table(
        name = "owner",
        uniqueConstraints = {
                @UniqueConstraint(name = "owner_email_unique", columnNames = "email")
        }
)
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Owner {

    @Id
    @SequenceGenerator(
            name = "owner_id_seq",
            sequenceName = "owner_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "owner_id_seq"
    )
    @Column(
            name = "id",
            nullable = false
    )
    public Long Id;

    @Column(
            name = "email",
            nullable = false
    )
    private String email;

    @Column(
            name = "password",
            nullable = false
    )
    private String password;

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
            mappedBy = "owner",
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Vehicle> vehicles = new ArrayList<>();

    public Owner(String email, String password, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void addVehicle(Vehicle vehicle) {
        if(!vehicles.contains(vehicle)) {
            vehicles.add(vehicle);
            vehicle.setOwner(this);
        }
    }

    public void removeVehicle(Vehicle vehicle) {
        if(vehicles.contains(vehicle)) {
            vehicles.remove(vehicle);
            vehicle.setOwner(this);
        }
    }
}
