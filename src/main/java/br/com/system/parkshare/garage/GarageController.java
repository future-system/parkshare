package br.com.system.parkshare.garage;

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

import br.com.system.parkshare.security.Token;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/garage/")
public class GarageController {

    @Autowired
    private GarageService service;

    @GetMapping
    public ResponseEntity<Page<Garage>> get(@RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
            JwtAuthenticationToken token) {
        return ResponseEntity.ok(service.findAll(page, pageSize));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Garage> create(JwtAuthenticationToken token, @RequestBody @Valid Garage garage) {
        return ResponseEntity.ok(service.create(Token.getidAccount(token), garage.getName(), garage.getPrice(), garage.getStreet(), garage.getBlock(), garage.getNumber(), garage.getState(), garage.getDescription(), garage.getLatitude(), garage.getLongitude()));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Garage> update(@PathVariable String id, @RequestBody @Valid Garage garage) {
        return ResponseEntity.ok(service.update(UUID.fromString(id), garage.getName(), garage.getPrice(), garage.getStreet(), garage.getBlock(), garage.getNumber(), garage.getState(), garage.getDescription(), garage.getLatitude(), garage.getLongitude()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> delete(@PathVariable String id) {
        service.delete(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }

    //  public record GarageDTO(UUID id, String name, float price, String street, String block, int number, String state,
    //  String description, double latitude, double longitude) {
    //  }
}
