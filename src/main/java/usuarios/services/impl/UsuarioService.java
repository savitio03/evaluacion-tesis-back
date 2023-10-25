package usuarios.services.impl;

import compartidos.ResultadoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import usuarios.models.dto.UsuarioDTO;
import usuarios.models.entidades.Usuario;
import usuarios.models.mappers.UsuarioMapper;
import usuarios.repository.IUsuarioRepository;
import usuarios.services.IUsuarioService;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    UsuarioMapper usuarioMapper;

    @Autowired
    IUsuarioRepository usuarioRepository;


    @Override
    public ResultadoDTO guardarUsuario(UsuarioDTO usuarioDTO) {
        ResultadoDTO resultadoDTO = new ResultadoDTO();
        if(usuarioDTO == null){
            resultado.setMensaje("No se puede guardar el usuario");
            return resultadoDTO;
        }

        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);

        usuarioRepository.save(usuario);

        resultadoDTO.setExitoso(Boolean.TRUE) ;
        resultadoDTO.setMensaje("El usuario se ha guardado con exito");

        return resultadoDTO;
    }

}
