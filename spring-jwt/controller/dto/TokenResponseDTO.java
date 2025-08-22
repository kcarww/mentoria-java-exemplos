package com.sollaris.controller.dto;

public class TokenResponseDTO {
    public String token;
    public String tipo;
    public String login;

    public TokenResponseDTO(String token, String login) {
        this.token = token;
        this.tipo = "Bearer";
        this.login = login;
    }
}