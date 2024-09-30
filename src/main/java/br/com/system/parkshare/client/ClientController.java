package br.com.system.parkshare.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.system.parkshare.security.Token;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("api/v1/client")
public class ClientController {

    @Autowired
    private ClientService service;

    @PostMapping
    @Transactional
    public ResponseEntity<Client> createClient(JwtAuthenticationToken token) {

        Client client = service.createClient(
                Token.getidAccount(token));
        return ResponseEntity.status(HttpStatus.CREATED).body(client);

    }

}
