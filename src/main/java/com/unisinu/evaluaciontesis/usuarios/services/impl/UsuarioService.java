package com.unisinu.evaluaciontesis.usuarios.services.impl;

import com.unisinu.evaluaciontesis.compartidos.ResultadoDTO;
import com.unisinu.evaluaciontesis.usuarios.models.dto.UsuarioDTO;
import com.unisinu.evaluaciontesis.usuarios.models.entidades.Usuario;
import com.unisinu.evaluaciontesis.usuarios.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.unisinu.evaluaciontesis.usuarios.services.IUsuarioService;

@Service
public class UsuarioService implements IUsuarioService {


    @Autowired
    IUsuarioRepository usuarioRepository;


    @Override
    public ResultadoDTO guardarUsuario(UsuarioDTO usuarioDTO) {
        ResultadoDTO resultadoDTO = new ResultadoDTO();
        if(usuarioDTO == null){
            resultadoDTO.setMensaje("No se puede guardar el usuario");
            return resultadoDTO;
        }

        Usuario usuario = new Usuario();

        usuarioRepository.save(usuario);

        resultadoDTO.setExitoso(Boolean.TRUE) ;
        resultadoDTO.setMensaje("El usuario se ha guardado con exito");

        return resultadoDTO;
    }

}
