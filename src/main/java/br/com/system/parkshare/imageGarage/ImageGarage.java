package br.com.system.parkshare.imageGarage;

import java.util.UUID;

import br.com.system.parkshare.garage.Garage;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "image_garage")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImageGarage {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "id_garage", nullable = false)
    private Garage garage;

    @Lob
    @Column(name = "image")
    private byte[] image;
}
