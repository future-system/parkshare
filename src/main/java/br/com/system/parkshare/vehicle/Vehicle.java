package br.com.system.parkshare.vehicle;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.system.parkshare.client.Client;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "vehicle")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idVehicle;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_client")
    private Client client;

    @Column(name = "plate", unique = true)
    private String plate;

    @Column(name = "model")
    private String model;

    @Column(name = "brand")
    private String brand;    

    @Column(name = "color")
    private String color;

    @Column(name = "type")
    private String type;

    @Column(name = "description")
    private String description;
}
