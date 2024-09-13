package br.com.system.parkshare.parking;

import br.com.system.parkshare.garage.Garage;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "parking")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Parking {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_parking")
    private UUID idParking;

    @ManyToOne
    @JoinColumn(name = "id_garage", nullable = false)
    private Garage garage;

    @Column(name = "vehicle_type")
    private String vehicleType;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private boolean status;
}
