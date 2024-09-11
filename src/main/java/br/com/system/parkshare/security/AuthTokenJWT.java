package br.com.system.parkshare.security;

import java.time.Instant;

public record AuthTokenJWT(String token, Instant expiresAt) {
}

