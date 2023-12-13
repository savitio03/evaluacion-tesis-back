package com.unisinu.evaluaciontesis.usuarios.services;

import com.unisinu.evaluaciontesis.compartidos.ResultadoDTO;
import com.unisinu.evaluaciontesis.usuarios.models.dto.FiltroConsultaUsuariosDTO;
import com.unisinu.evaluaciontesis.usuarios.models.dto.UsuarioDTO;
import com.unisinu.evaluaciontesis.usuarios.models.dto.UsuarioOutDTO;

public interface IUsuarioService {

    ResultadoDTO guardarUsuario(UsuarioDTO usuarioDTO);

    UsuarioOutDTO consultarUsuario(UsuarioDTO usuarioDTO);

    UsuarioOutDTO consultarUsuarios(FiltroConsultaUsuariosDTO filtroConsultaUsuariosDTO);

    ResultadoDTO actualizarUsuario(UsuarioDTO usuarioDTO);

    UsuarioOutDTO consultarUsuariosPorAprobar();

    ResultadoDTO aprobarUsuario(Long idUsuario);

    ResultadoDTO rechazarUsuario(Long idUsuario);
}
