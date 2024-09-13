package br.com.system.parkshare.garage;

import br.com.system.parkshare.associated.Associated;
import br.com.system.parkshare.parking.Parking;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

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

    @ManyToOne
    @JoinColumn(name = "id_associated", nullable = false)
    private Associated associated;

    @OneToMany(mappedBy = "garage", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Parking> parkings;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private float price;

    @Column(name = "street")
    private String street;

    @Column(name = "block")
    private String block;

    @Column(name = "number")
    private int number;

    @Column(name = "state")
    private String state;

    @Column(name = "description")
    private String description;
}
