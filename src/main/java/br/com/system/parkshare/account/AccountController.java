package br.com.system.parkshare.account;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.UUID;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.system.parkshare.security.Token;
import br.com.system.parkshare.utils.response.Return;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/v1/account/")
public class AccountController {

    @Autowired
    private AccountService service;

    // @PreAuthorize("hasAuthority('ROLE_USER')")
    // @PreAuthorize("hasAnyAuthority('USER', 'NONE', 'ROLE_USER')")
    @GetMapping
    public ResponseEntity<Return> get(@RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
            JwtAuthenticationToken token) {


        final var users = service.getRepository().findAllByAppIdApp(
                UUID.fromString(Token.getClaimFromToken(token, "idApp").toString()),
                PageRequest.of(page, pageSize, Direction.DESC, "dateTimeAccess"));

        return ResponseEntity
                .ok(new Return(users.toList(), page, pageSize, users.getTotalPages(), users.getTotalElements()));
    }

    @PutMapping("/{id}")
    @Transactional
    public String update(@PathVariable String id) {

        return "1";
    }

    @DeleteMapping("/{id}")
    @Transactional
    public String dalete(@PathVariable String id) {

        return "1";
    }

}
