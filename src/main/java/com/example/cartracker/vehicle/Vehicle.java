package com.example.cartracker.vehicle;

import com.example.cartracker.Driver.Driver;
import com.example.cartracker.Owner.Owner;
import com.example.cartracker.Timestamp.Timestamp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Vehicle")
@Table(name = "vehicle")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Vehicle {

    @Id
    @SequenceGenerator(
            name = "vehicle_sequence",
            sequenceName = "vehicle_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "vehicle_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String name;

    @Column(
            name = "last_update"
    )
    private LocalDateTime lastUpdate;

    @Column(
            name = "latitud"
    )
    private Double latitude;

    @Column(
            name = "longitude"
    )
    private Double longitude;

    @ManyToOne
    @JoinColumn(
            name = "owner_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "owner_id_fk"
            )
    )
    private Owner owner;

    @ManyToOne
    @JoinColumn(
            name = "driver_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "driver_id_fk"
            )
    )
    private Driver driver;

    @OneToMany(
            mappedBy = "vehicle",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            fetch = FetchType.LAZY
    )
    private List<Timestamp> timestamps = new ArrayList<>();

    public Vehicle(String name, Double latitude, Double longitude, LocalDateTime lastUpdate) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.lastUpdate = lastUpdate;
    }

    public void addTimestamp(Timestamp timestamp) {
        if (!this.timestamps.contains(timestamp)) {
            this.timestamps.add(timestamp);
            timestamp.setVehicle(this);
        }
    }

    public void removeTimestamp(Timestamp timestamp) {
        if (this.timestamps.contains(timestamp)) {
            this.timestamps.remove(timestamp);
            timestamp.setVehicle(null);
        }
    }
}
