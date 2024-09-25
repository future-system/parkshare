package br.com.system.parkshare.garage;

import br.com.system.parkshare.associated.Associated;
import br.com.system.parkshare.imageGarage.ImageGarage;
import br.com.system.parkshare.parking.Parking;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "garage")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Garage {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_garage")
    private UUID idGarage;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_associated", nullable = false)
    private Associated associated;

    @OneToMany(mappedBy = "garage", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Set<Parking> parkings;

    @OneToMany(mappedBy = "garage", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Set<ImageGarage> imageGarage;

    @Column(name = "name",  nullable = false)
    private String name;

    @Column(name = "price",  nullable = false)
    private float price;

    @Column(name = "street",  nullable = false)
    private String street;

    @Column(name = "block",  nullable = false)
    private String block;

    @Column(name = "number",  nullable = false)
    private int number;

    @Column(name = "state",  nullable = false)
    private String state;

    @Column(name = "description",  nullable = false)
    private String description;

    @Column(name = "latitude",  nullable = false)
    private double latitude;

    @Column(name = "longitude",  nullable = false)
    private double longitude;
}
