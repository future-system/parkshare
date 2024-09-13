package br.com.system.parkshare.schedule;

import br.com.system.parkshare.client.Client;
import br.com.system.parkshare.parking.Parking;
import br.com.system.parkshare.scheduled.Scheduled;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "schedule")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_schedule")
    private UUID idSchedule;

    @ManyToOne
    @JoinColumn(name = "id_client", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "id_parking", nullable = false)
    private Parking parking;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Scheduled> scheduledList;

    @Column(name = "day")
    private LocalDate day;

    @Column(name = "init_datetime")
    private LocalDateTime initDateTime;

    @Column(name = "finish_datetime")
    private LocalDateTime finishDateTime;

    @Column(name = "status")
    private String status;
}
