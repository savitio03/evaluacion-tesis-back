package com.unisinu.evaluaciontesis.tesis.models.mappers;

import com.unisinu.evaluaciontesis.tesis.models.dto.TesisDTO;
import com.unisinu.evaluaciontesis.tesis.models.entidades.Tesis;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TesisMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public TesisMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public TesisDTO toDTO(Tesis tesis) {
        return modelMapper.map(tesis, TesisDTO.class);
    }

    public Tesis toEntity(TesisDTO tesisDTO) {
        return modelMapper.map(tesisDTO, Tesis.class);
    }
}
