package com.audora.comprasonline.api.dto;

import lombok.Data;

@Data
public class UsuarioDto {
    private String email;
    private String senha;
    private Boolean admin;
}
