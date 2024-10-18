package br.com.system.parkshare.scheduled;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduledRepository extends JpaRepository<Scheduled, UUID>{
  
}
