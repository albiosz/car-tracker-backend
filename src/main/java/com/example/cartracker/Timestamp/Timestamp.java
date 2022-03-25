package com.example.cartracker.Timestamp;


import com.example.cartracker.vehicle.Vehicle;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "Timestamp")
@Table(name = "timestamp")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Timestamp {

    @Id
    @SequenceGenerator(
            name = "timestamp_id_seq",
            sequenceName = "timestamp_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "timestamp_id_seq"
    )
    @Column(
            name = "id",
            nullable = false
    )
    private Long id;

    @Column(
            name = "longitude",
            nullable = false
    )
    private Double longitude;

    @Column(
            name = "latitude",
            nullable = false
    )
    private Double latitude;

    @Column(
            name = "time",
            nullable = false
    )
    private LocalDateTime time;

    @ManyToOne
    @JoinColumn(
            name = "vehicle_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "vehicle_id_fk"
            )
    )
    private Vehicle vehicle;

    public Timestamp(Double longitude, Double latitude, LocalDateTime time) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.time = time;
    }
}
