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
@RequestMapping("/auth/v1/{idApp}/")
public class AuthController {

    @Autowired
    private AccountService service;

    @Autowired
    private JwtEncoder jwtEncoder;

    @PostMapping("/login")
    @Transactional
    public ResponseEntity<AuthTokenJWT> login(@PathVariable UUID idApp, @RequestBody @Valid AuthDTO account) {
        return ResponseEntity.ok(
                new AuthTokenJWT(Token.generateTokenJWT(jwtEncoder, service.makeLogin(account, idApp, account.login())),
                        Token.generateTokenExpirationTime()));
    }

    @PostMapping("/signin")
    @Transactional
    public ResponseEntity<Account> create(@PathVariable UUID idApp, @RequestBody @Valid Account account) {
        return service.create(idApp, account);
    }

}
