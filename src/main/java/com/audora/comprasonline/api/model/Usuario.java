package com.audora.comprasonline.api.model;

import com.audora.comprasonline.api.model.enums.PerfilEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @NotEmpty(message = "email não pode ser nulo")
    @Column(unique=true)
    private String email;

    @NotNull(message = "Senha não pode ser nula")
    @NotEmpty
    private String senha;

    private PerfilEnum perfil;

    @OneToOne(cascade = CascadeType.ALL)
    private CarrinhoDeCompras carrinhoDeCompras;

}
