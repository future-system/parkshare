package br.com.system.parkshare.vehicle;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, UUID> {
    
}
