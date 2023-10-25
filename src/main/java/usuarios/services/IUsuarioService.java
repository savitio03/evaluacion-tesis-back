package usuarios.services;

import compartidos.ResultadoDTO;
import usuarios.models.dto.UsuarioDTO;

public interface IUsuarioService {

    public ResultadoDTO guardarUsuario(UsuarioDTO usuarioDTO);
}
