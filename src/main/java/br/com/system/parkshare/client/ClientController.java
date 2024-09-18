package br.com.system.parkshare.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("api/v1/client/")
public class ClientController {

    @Autowired
    private ClientService service;

    @PostMapping("{id}")
    @Transactional
    public ResponseEntity<Client> createClient(@PathVariable UUID accountId) {
        
        return null;
    }

}
