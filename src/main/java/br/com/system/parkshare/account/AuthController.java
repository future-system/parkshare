package br.com.system.parkshare.account;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.system.parkshare.security.AuthDTO;
import br.com.system.parkshare.security.AuthTokenJWT;
import br.com.system.parkshare.security.Token;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/auth/v1/")
public class AuthController {

    @Autowired
    private AccountService service;

    @Autowired
    private JwtEncoder jwtEncoder;

    @PostMapping("/login")
    @Transactional
    public ResponseEntity<AuthTokenJWT> login(@RequestBody @Valid AuthDTO account) {
        return ResponseEntity.ok(
                new AuthTokenJWT(Token.generateTokenJWT(jwtEncoder, service.makeLogin(account)),
                        Token.generateTokenExpirationTime()));
    }

    @PostMapping("/signin")
    @Transactional
    public ResponseEntity<Account> create(@RequestBody @Valid Account account) {
        return service.create(account);
    }

}
