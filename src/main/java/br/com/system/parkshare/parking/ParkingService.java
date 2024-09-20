package br.com.system.parkshare.parking;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.system.parkshare.garage.GarageRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ParkingService {

    @Autowired
    private ParkingRepository parkingRepository;

    @Autowired
    private GarageRepository garageRepository;

    public Parking create(UUID idGarage, String description, String vehicleType) {
        Parking parking = new Parking();

        parking.setDescription(description);
        parking.setVehicleType(vehicleType);
        parking.setStatus(true);//??? ver isso

        parking.setGarage(garageRepository.findById(idGarage)
                .orElseThrow(() -> new EntityNotFoundException("Garagem não encontrada")));

        return parkingRepository.save(parking);
    }

    public void delete(UUID id) {
        parkingRepository.delete(findById(id));
    }

    public Parking findById(UUID id) {
        return parkingRepository.findById(id).orElseThrow(() -> new RuntimeException("Associado não encontrado"));
    }

    public Iterable<Parking> findAll() {
        return parkingRepository.findAll();
    }

    public Page<Parking> findAll(int page, int pageSize) {
        return parkingRepository.findAll(PageRequest.of(page, pageSize, Direction.DESC, "garage.name"));
    }

    public Parking update(UUID id, String description, String vehicleType) {
        Parking parking = findById(id);

        parking.setDescription(description);
        parking.setVehicleType(vehicleType);
        parking.setStatus(true);//??? ver isso

        return parkingRepository.save(parking);
    }

}
