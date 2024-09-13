package br.com.system.parkshare.scheduled;

import br.com.system.parkshare.schedule.Schedule;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "scheduled")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Scheduled {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_scheduled")
    private UUID idScheduled;

    @ManyToOne
    @JoinColumn(name = "id_schedule", nullable = false)
    private Schedule schedule;

    @Column(name = "day")
    private LocalDate day;

    @Column(name = "init_time")
    private LocalTime initTime;

    @Column(name = "finish_time")
    private LocalTime finishTime;

    @Column(name = "extra_time")
    private LocalTime extraTime;

    @NotBlank
    @Column(name = "status")
    private String status;
}
