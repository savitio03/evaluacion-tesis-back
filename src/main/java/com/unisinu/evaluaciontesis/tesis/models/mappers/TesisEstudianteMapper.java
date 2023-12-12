package com.unisinu.evaluaciontesis.tesis.models.mappers;

import com.unisinu.evaluaciontesis.tesis.models.dto.TesisDTO;
import com.unisinu.evaluaciontesis.tesis.models.dto.TesisEstudianteDTO;
import com.unisinu.evaluaciontesis.tesis.models.entidades.Tesis;
import com.unisinu.evaluaciontesis.tesis.models.entidades.TesisEstudiante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TesisEstudianteMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public TesisEstudianteMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public TesisEstudianteDTO toDTO(TesisEstudiante tesisEstudiante) {
        return modelMapper.map(tesisEstudiante, TesisEstudianteDTO.class);
    }

    public TesisEstudiante toEntity(TesisEstudianteDTO tesisEstudianteDTO) {
        return modelMapper.map(tesisEstudianteDTO, TesisEstudiante.class);
    }
}
