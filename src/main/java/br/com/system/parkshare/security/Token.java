package br.com.system.parkshare.security;

import java.time.Instant;
import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import br.com.system.parkshare.account.Account;
import br.com.system.parkshare.cellphone.Cellphone;

@Component
public abstract class Token {

    public static Instant generateTokenExpirationTime() {
        return Instant.now().plusSeconds(3600);// 1 hora
    }

    public static JwtClaimsSet generateTokenClaims(Account usuario) {
        return JwtClaimsSet.builder()
                .issuer("system-api")
                .issuedAt(Instant.now())
                .expiresAt(generateTokenExpirationTime())
                .subject(usuario.getEmail())
                .claim("idAccount", usuario.getIdAccount().toString())
                .claim("nickname", usuario.getNickname())
                .claim("email", usuario.getEmail())
                .claim("username", usuario.getUsername())
                .claim("phones", usuario.getCellphones() == null ? new ArrayList<>()
                        : usuario.getCellphones().stream()
                                .map(Cellphone::getCellphone).collect(Collectors.toList()))
                // .claim("dateTimeCreated", usuario.getDateTimeCreated())
                .claim("authorities",
                        usuario.getAuthorities().stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .build();
    }

    public static String generateTokenJWT(JwtEncoder jwtEncoder, Account usuario) {
        return jwtEncoder.encode(JwtEncoderParameters.from(generateTokenClaims(usuario))).getTokenValue();
    }

    public static Object getClaimFromToken(JwtAuthenticationToken token, String nameClaim) {
        return token.getTokenAttributes().get(nameClaim);
    }

    public static UUID getidApp(JwtAuthenticationToken token) {

        return UUID.fromString(getClaimFromToken(token, "idApp").toString());
    }

    public static UUID getidAccount(JwtAuthenticationToken token) {

        return UUID.fromString(getClaimFromToken(token, "idAccount").toString());

    }
}
