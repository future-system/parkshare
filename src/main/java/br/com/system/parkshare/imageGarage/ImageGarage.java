package br.com.system.parkshare.imageGarage;

import java.util.UUID;

import br.com.system.parkshare.associated.Associated;
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
    @JoinColumn(name = "id_associated", nullable = false)
    private Associated associated;

    @Lob
    @Column(name = "image")
    private byte[] image;
}
