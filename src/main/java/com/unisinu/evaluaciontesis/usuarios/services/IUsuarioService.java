package com.unisinu.evaluaciontesis.usuarios.services;

import com.unisinu.evaluaciontesis.compartidos.ResultadoDTO;
import com.unisinu.evaluaciontesis.usuarios.models.dto.UsuarioDTO;

public interface IUsuarioService {

    public ResultadoDTO guardarUsuario(UsuarioDTO usuarioDTO);
}
