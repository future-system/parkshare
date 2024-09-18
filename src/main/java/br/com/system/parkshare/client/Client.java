package br.com.system.parkshare.client;

import br.com.system.parkshare.vehicle.Vehicle;
import br.com.system.parkshare.schedule.Schedule;
import br.com.system.parkshare.account.Account;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "client")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_client")
    private UUID idClient;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_account", nullable = false)
    private Account account;  

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Vehicle> vehicles;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Schedule> schedules;
}
