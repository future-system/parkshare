package br.com.system.parkshare.associated;

import br.com.system.parkshare.garage.Garage;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "associated")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Associated {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_associated")
    private UUID idAssociated;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "associated", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Garage> garages;
}
