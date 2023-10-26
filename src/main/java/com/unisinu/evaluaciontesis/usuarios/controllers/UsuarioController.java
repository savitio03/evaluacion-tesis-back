package com.unisinu.evaluaciontesis.usuarios.controllers;

import com.unisinu.evaluaciontesis.compartidos.ResultadoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.unisinu.evaluaciontesis.usuarios.models.dto.UsuarioDTO;
import com.unisinu.evaluaciontesis.usuarios.services.IUsuarioService;

@CrossOrigin(origins = "http:localhost:4200")
@RestController
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @PostMapping("guardarUsuario")
    public ResultadoDTO guardarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        return usuarioService.guardarUsuario(usuarioDTO);
    }
}
