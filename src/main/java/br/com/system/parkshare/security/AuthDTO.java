package br.com.system.parkshare.security;

import jakarta.validation.constraints.NotBlank;

public record AuthDTO(@NotBlank String login, @NotBlank String password) {//Um jeito rapido para Fazer DTO
}