package br.com.system.parkshare.associated;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
@RequestMapping("api/v1/associated")
public class AssociatedController {

    @Autowired
    private AssociatedService service;

    @GetMapping
    public ResponseEntity<Page<Associated>> get(@RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
            JwtAuthenticationToken token) {
        return ResponseEntity.ok(service.findAll(page, pageSize));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Associated> create(JwtAuthenticationToken token, @RequestBody @Valid Associated associated) {
        return ResponseEntity.ok(service.create(Token.getidAccount(token), associated.getDescription()));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Associated> update(@PathVariable String id, @RequestBody @Valid Associated associated) {
        return ResponseEntity.ok(service.update(UUID.fromString(id), associated.getDescription()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> delete(@PathVariable String id) {
        service.delete(UUID.fromString(id));
        return ResponseEntity.ok().build();
    }

    //  public record Associated()) {
    // }
}
