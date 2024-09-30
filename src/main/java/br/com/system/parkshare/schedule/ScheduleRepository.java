package br.com.system.parkshare.schedule;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, UUID>{

    @Query("SELECT COUNT(s) > 0 FROM Schedule s WHERE s.parking.idParking = :idParking AND :dateVerificada BETWEEN s.initDateTime AND s.finishDateTime")
    boolean existsByidParkingAndDateVerificada(@Param("idParking") UUID idParking, @Param("dateVerificada") LocalDateTime dateVerificada);

}
