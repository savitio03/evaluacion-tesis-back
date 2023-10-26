package com.unisinu.evaluaciontesis.usuarios.models.mappers;

import com.unisinu.evaluaciontesis.usuarios.models.dto.UsuarioDTO;
import com.unisinu.evaluaciontesis.usuarios.models.entidades.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public UsuarioMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UsuarioDTO toDTO(Usuario usuario) {
        return modelMapper.map(usuario, UsuarioDTO.class);
    }

    public Usuario toEntity(UsuarioDTO suarioDTO) {
        return modelMapper.map(suarioDTO, Usuario.class);
    }
}
