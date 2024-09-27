package br.com.system.parkshare.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth/v1/address")
public class AddressController {

    @Autowired
    private AddressService service;

    @GetMapping("/{address}")
    public ResponseEntity<ReturnOnlyData> get(@PathVariable String address) {
        return ResponseEntity.ok(new ReturnOnlyData(service.getAddress(address)));
    }

    @GetMapping("/cep/{cep}")
    public ResponseEntity<ReturnOnlyData> getCep(@PathVariable String cep) {
        return ResponseEntity.ok(new ReturnOnlyData(service.getCep(cep)));
    }

    public record ReturnOnlyData(Object data) {
    }
}
