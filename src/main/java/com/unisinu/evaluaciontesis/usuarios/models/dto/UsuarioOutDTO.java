package com.unisinu.evaluaciontesis.usuarios.models.dto;

import com.unisinu.evaluaciontesis.compartidos.ResultadoDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioOutDTO extends ResultadoDTO {

    private UsuarioDTO usuarioDTO;
    private List<UsuarioDTO> listaUsuarioDTO;
    private Long totalUsuarios;
}
