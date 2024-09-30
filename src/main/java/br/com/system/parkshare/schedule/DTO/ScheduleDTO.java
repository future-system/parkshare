package br.com.system.parkshare.schedule.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleDTO {
    private UUID idParking;
    private LocalDate day;
    private LocalDateTime init;
    private LocalDateTime finish;
}