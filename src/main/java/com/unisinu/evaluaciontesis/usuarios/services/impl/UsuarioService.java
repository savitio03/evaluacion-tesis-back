package com.unisinu.evaluaciontesis.usuarios.services.impl;

import com.unisinu.evaluaciontesis.compartidos.EstadoCuentaEnum;
import com.unisinu.evaluaciontesis.compartidos.PasswordEncoder;
import com.unisinu.evaluaciontesis.compartidos.ResultadoDTO;
import com.unisinu.evaluaciontesis.usuarios.models.dto.FiltroConsultaUsuariosDTO;
import com.unisinu.evaluaciontesis.usuarios.models.dto.UsuarioDTO;
import com.unisinu.evaluaciontesis.usuarios.models.dto.UsuarioOutDTO;
import com.unisinu.evaluaciontesis.usuarios.models.entidades.Usuario;
import com.unisinu.evaluaciontesis.usuarios.models.mappers.UsuarioMapper;
import com.unisinu.evaluaciontesis.usuarios.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.unisinu.evaluaciontesis.usuarios.services.IUsuarioService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements IUsuarioService {


    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

        Usuario usuario2 = consultarUsuarioPorCodigoCarnet(usuarioDTO.getCodigoCarnet());
        if (usuario2 != null) {
            resultadoDTO.setMensaje("Ya existe un usuario con el código de carnet: " + usuarioDTO.getCodigoCarnet());
            return resultadoDTO;
        }

        String correoMinuscula = usuarioDTO.getCorreo().toLowerCase();

        usuarioDTO.setCorreo(correoMinuscula);
        Usuario usuario3 = consultarUsuarioPorCorreo(usuarioDTO.getCorreo());
        if (usuario3 != null) {
            resultadoDTO.setMensaje("Ya existe un usuario con el correo: " + usuarioDTO.getCorreo());
            return resultadoDTO;
        }

        usuarioDTO.setEstadoCuentaEnum(EstadoCuentaEnum.PENDIENTE);
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        usuario.setFechaCreacion(LocalDateTime.now());

        usuario.setPassword(passwordEncoder.cifrarContrasena(usuario.getPassword()));

        usuarioRepository.save(usuario);

        resultadoDTO.setExitoso(Boolean.TRUE);
        return resultadoDTO;
    }

    private Usuario consultarUsuarioPorCodigoCarnet(String codigoCarnet) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByCodigoCarnet(codigoCarnet);

        if (usuarioOptional.isEmpty()) {
            return null;
        }

        return usuarioOptional.get();
    }

    private Usuario consultarUsuarioPorCorreo(String correo) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByCorreo(correo);

        if (usuarioOptional.isEmpty()) {
            return null;
        }

        return usuarioOptional.get();
    }


    @Override
    public UsuarioOutDTO consultarUsuario(UsuarioDTO usuarioDTO) {
        UsuarioOutDTO usuarioOutDTO = new UsuarioOutDTO();
        usuarioOutDTO.setExitoso(Boolean.FALSE);

        String correoMinuscula = usuarioDTO.getCorreo().toLowerCase();
        usuarioDTO.setCorreo(correoMinuscula);


        Optional<Usuario> usuario = usuarioRepository.findByCorreo(usuarioDTO.getCorreo());

        if (usuario.isEmpty()) {
            usuarioOutDTO.setMensaje("El usuario no existe");
            return usuarioOutDTO;
        }

        if (!passwordEncoder.compararContrasena(usuarioDTO.getPassword(), usuario.get().getPassword())) {
            return usuarioOutDTO;
        }
        usuario.get().setPassword(null);
        usuarioOutDTO.setUsuarioDTO(usuarioMapper.toDTO(usuario.get()));
        usuarioOutDTO.setExitoso(Boolean.TRUE);
        return usuarioOutDTO;
    }

    @Override
    public UsuarioOutDTO consultarUsuarios(FiltroConsultaUsuariosDTO filtroConsultaUsuariosDTO) {
        UsuarioOutDTO usuarioOutDTO = new UsuarioOutDTO();
        usuarioOutDTO.setExitoso(Boolean.FALSE);

        List<Usuario> listaUsuarios = usuarioRepository.findByFilters(
                filtroConsultaUsuariosDTO.getNombre(),
                filtroConsultaUsuariosDTO.getNumeroCarnet(),
                filtroConsultaUsuariosDTO.getPrograma().name(),
                filtroConsultaUsuariosDTO.getNumeroIdentificacion(),
                filtroConsultaUsuariosDTO.getTipoIdentificacionEnum().name()
        );

        if (listaUsuarios.isEmpty()) {
            usuarioOutDTO.setMensaje("No se encontraron usuarios");
            return usuarioOutDTO;
        }

        usuarioOutDTO.setListaUsuarioDTO(listaUsuarios.stream().map(usuario -> usuarioMapper.toDTO(usuario)).collect(Collectors.toList()));
        usuarioOutDTO.setExitoso(Boolean.TRUE);
        return usuarioOutDTO;
    }

    @Override
    public ResultadoDTO actualizarUsuario(UsuarioDTO usuarioDTO) {
        ResultadoDTO resultadoDTO = new ResultadoDTO();
        resultadoDTO.setExitoso(Boolean.FALSE);

        if (usuarioDTO == null) {
            resultadoDTO.setMensaje("El usuario no puede ser nulo");
            return resultadoDTO;
        }

        Optional<Usuario> usuario = usuarioRepository.findById(usuarioDTO.getIdUsuario());

        if (usuario.isEmpty()) {
            resultadoDTO.setMensaje("El usuario no existe");
            return resultadoDTO;
        }

        usuario.get().setCorreo(usuarioDTO.getCorreo());
        usuario.get().setNombre(usuarioDTO.getNombre());
        usuario.get().setSegundoNombre(usuarioDTO.getSegundoNombre());
        usuario.get().setApellido(usuarioDTO.getApellido());
        usuario.get().setSegundoApellido(usuarioDTO.getSegundoApellido());
        usuario.get().setCodigoCarnet(usuarioDTO.getCodigoCarnet());
        usuario.get().setTipoIdentificacionEnum(usuarioDTO.getTipoIdentificacionEnum());
        usuario.get().setNumeroIdenticacion(usuarioDTO.getNumeroIdenticacion());
        usuario.get().setSexoEnum(usuarioDTO.getSexoEnum());
        usuario.get().setRol(usuarioDTO.getRol());
        usuario.get().setNumeroCelular(usuarioDTO.getNumeroCelular());
        usuario.get().setCorreo(usuarioDTO.getCorreo());
        usuario.get().setPassword(usuarioDTO.getPassword());
        usuario.get().setProgramaEnum(usuarioDTO.getProgramaEnum());
        usuario.get().setFechaCreacion(usuarioDTO.getFechaCreacion());

        usuarioRepository.save(usuario.get());

        resultadoDTO.setExitoso(Boolean.TRUE);
        resultadoDTO.setMensaje("El usuario se ha actualizado exitosamente");
        return resultadoDTO;
    }

    @Override
    public UsuarioOutDTO consultarUsuariosPorAprobar() {
        UsuarioOutDTO usuarioOutDTO = new UsuarioOutDTO();
        usuarioOutDTO.setExitoso(Boolean.FALSE);

        List<Usuario> listaUsuarios = new ArrayList<>();

        List<Usuario> listaUsuariosPendienes = usuarioRepository.findByEstadoCuentaEnum(EstadoCuentaEnum.PENDIENTE);
        List<Usuario> listaUsuariosRechazados = usuarioRepository.findByEstadoCuentaEnum(EstadoCuentaEnum.RECHAZADO);

        listaUsuarios.addAll(listaUsuariosPendienes);
        listaUsuarios.addAll(listaUsuariosRechazados);

        if (listaUsuarios.isEmpty()) {
            usuarioOutDTO.setMensaje("No se encontraron usuarios por aprobar");
            return usuarioOutDTO;
        }

        List<UsuarioDTO> listaUsuarioDTO = listaUsuarios.stream().map(usuario -> usuarioMapper.toDTO(usuario)).collect(Collectors.toList());

        for (UsuarioDTO usuarioDTO : listaUsuarioDTO) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(usuarioDTO.getNombre());
            if (usuarioDTO.getSegundoNombre() != null) {
                stringBuilder.append(" ");
                stringBuilder.append(usuarioDTO.getSegundoNombre());
            }
            stringBuilder.append(" ");
            stringBuilder.append(usuarioDTO.getApellido());
            if (usuarioDTO.getSegundoApellido() != null) {
                stringBuilder.append(" ");
                stringBuilder.append(usuarioDTO.getSegundoApellido());
            }
            usuarioDTO.setNombreCompleto(stringBuilder.toString());
            usuarioDTO.setPassword(null);
        }

        usuarioOutDTO.setListaUsuarioDTO(listaUsuarioDTO);
        usuarioOutDTO.setExitoso(Boolean.TRUE);
        return usuarioOutDTO;
    }

    @Override
    public ResultadoDTO aprobarUsuario(Long idUsuario) {
        ResultadoDTO resultadoDTO = new ResultadoDTO();
        resultadoDTO.setExitoso(Boolean.FALSE);

        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);

        if (usuario.isEmpty()) {
            resultadoDTO.setMensaje("El usuario no existe");
            return resultadoDTO;
        }

        usuario.get().setEstadoCuentaEnum(EstadoCuentaEnum.APROBADO);
        usuarioRepository.save(usuario.get());

        resultadoDTO.setExitoso(Boolean.TRUE);
        resultadoDTO.setMensaje("El usuario se ha aprobado exitosamente");
        return resultadoDTO;
    }

    @Override
    public ResultadoDTO rechazarUsuario(Long idUsuario) {
        ResultadoDTO resultadoDTO = new ResultadoDTO();
        resultadoDTO.setExitoso(Boolean.FALSE);

        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);

        if (usuario.isEmpty()) {
            resultadoDTO.setMensaje("El usuario no existe");
            return resultadoDTO;
        }

        usuario.get().setEstadoCuentaEnum(EstadoCuentaEnum.RECHAZADO);
        usuarioRepository.save(usuario.get());

        resultadoDTO.setExitoso(Boolean.TRUE);
        resultadoDTO.setMensaje("El usuario se ha rechazado exitosamente");
        return resultadoDTO;
    }

}
