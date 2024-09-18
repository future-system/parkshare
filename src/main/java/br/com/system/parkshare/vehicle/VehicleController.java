package br.com.system.parkshare.vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.system.parkshare.security.Token;
import jakarta.transaction.Transactional;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService service;

    @PostMapping
    @Transactional
    public ResponseEntity<List<Vehicle>> createVehicle(JwtAuthenticationToken token, @RequestBody Vehicles vehicles) {
        List<Vehicle> res = service.createVehicle(Token.getidAccount(token), vehicles.vehicles());
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    public record Vehicles(List<Vehicle> vehicles) {
    }
}
