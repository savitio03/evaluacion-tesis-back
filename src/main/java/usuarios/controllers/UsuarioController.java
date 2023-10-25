package usuarios.controllers;

import compartidos.ResultadoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import usuarios.models.dto.UsuarioDTO;
import usuarios.services.IUsuarioService;

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
