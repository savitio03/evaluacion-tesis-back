package com.unisinu.evaluaciontesis.usuarios.controllers;

import com.unisinu.evaluaciontesis.compartidos.ResultadoDTO;
import com.unisinu.evaluaciontesis.usuarios.models.dto.FiltroConsultaUsuariosDTO;
import com.unisinu.evaluaciontesis.usuarios.models.dto.UsuarioOutDTO;
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

    @PostMapping("consultarUsuario")
    public UsuarioOutDTO consultarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        return usuarioService.consultarUsuario(usuarioDTO);
    }

    @PostMapping("consultarUsuarios")
    public UsuarioOutDTO consultarUsuarios(@RequestBody FiltroConsultaUsuariosDTO filtroConsultaUsuariosDTO) {
        return usuarioService.consultarUsuarios(filtroConsultaUsuariosDTO);
    }

    @GetMapping("consultarUsuariosPorAprobar")
    public UsuarioOutDTO consultarUsuariosPorAprobar() {
        return usuarioService.consultarUsuariosPorAprobar();
    }

    @PostMapping("actualizarUsuario")
    public ResultadoDTO actualizarUsuario(@RequestParam UsuarioDTO usuarioDTO) {
        return usuarioService.actualizarUsuario(usuarioDTO);
    }

    @GetMapping("aprobarUsuario")
    public ResultadoDTO aprobarUsuario(@RequestParam Long idUsuario) {
        return usuarioService.aprobarUsuario(idUsuario);
    }

    @GetMapping("rechazarUsuario")
    public ResultadoDTO rechazarUsuario(@RequestParam Long idUsuario) {
        return usuarioService.rechazarUsuario(idUsuario);
    }

}
