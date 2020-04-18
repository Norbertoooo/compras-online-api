package com.audora.comprasonline.api.security.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class JwtResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String jwttoken;
}
