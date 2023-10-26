package com.unisinu.evaluaciontesis.usuarios.services.impl;

import com.unisinu.evaluaciontesis.compartidos.ResultadoDTO;
import com.unisinu.evaluaciontesis.usuarios.models.dto.UsuarioDTO;
import com.unisinu.evaluaciontesis.usuarios.models.entidades.Usuario;
import com.unisinu.evaluaciontesis.usuarios.models.mappers.UsuarioMapper;
import com.unisinu.evaluaciontesis.usuarios.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.unisinu.evaluaciontesis.usuarios.services.IUsuarioService;

import java.math.BigDecimal;

@Service
public class UsuarioService implements IUsuarioService {


    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Override
    public ResultadoDTO guardarUsuario(UsuarioDTO usuarioDTO) {
        ResultadoDTO resultadoDTO = new ResultadoDTO();
        resultadoDTO.setExitoso(Boolean.FALSE);

        if (usuarioDTO == null) {
            resultadoDTO.setMensaje("El usuario no puede ser nulo");
            return resultadoDTO;
        }

        if (usuarioDTO.getCorreo() == null) {
            resultadoDTO.setMensaje("El correo es nulo");
            return resultadoDTO;
        }

        Usuario usuario = new Usuario();

        usuario = usuarioMapper.toEntity(usuarioDTO);

        usuarioRepository.save(usuario);

        resultadoDTO.setExitoso(Boolean.TRUE);
        return resultadoDTO;
    }

}
