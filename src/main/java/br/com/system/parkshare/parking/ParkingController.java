package br.com.system.parkshare.parking;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/parking/")
public class ParkingController {

    @Autowired
    private ParkingService service;

    @GetMapping
    public ResponseEntity<Page<Parking>> get(@RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
            JwtAuthenticationToken token) {
        return ResponseEntity.ok(service.findAll(page, pageSize));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Parking> create(JwtAuthenticationToken token, @RequestBody @Valid ParkingDTO parking) {
        return ResponseEntity.ok(service.create(parking.idGarage(), parking.description(), parking.vehicleType()));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Parking> update(@PathVariable String id, @RequestBody @Valid ParkingDTO parking) {
        return ResponseEntity.ok(service.update(UUID.fromString(id), parking.description(), parking.vehicleType()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> delete(@PathVariable String id) {
        service.delete(UUID.fromString(id));
        return ResponseEntity.ok().build();
    }

    public record ParkingDTO(UUID idGarage, String description, String vehicleType) {
    }
}
