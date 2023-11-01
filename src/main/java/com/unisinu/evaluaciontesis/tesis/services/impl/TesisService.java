package com.unisinu.evaluaciontesis.tesis.services.impl;

import com.unisinu.evaluaciontesis.compartidos.ResultadoDTO;
import com.unisinu.evaluaciontesis.tesis.models.dto.TesisDTO;
import com.unisinu.evaluaciontesis.tesis.models.dto.TesisOutDTO;
import com.unisinu.evaluaciontesis.tesis.models.entidades.Tesis;
import com.unisinu.evaluaciontesis.tesis.models.mappers.TesisMapper;
import com.unisinu.evaluaciontesis.tesis.repository.ITesisRepository;
import com.unisinu.evaluaciontesis.tesis.services.ITesisService;
import com.unisinu.evaluaciontesis.usuarios.models.mappers.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TesisService implements ITesisService {


    @Autowired
    private ITesisRepository tesisRepository;

    @Autowired
    private TesisMapper tesisMapper;

    @Autowired
    UsuarioMapper usuarioMapper;


    @Override
    public ResultadoDTO guardarTesis(TesisDTO tesisDTO) {
        ResultadoDTO resultadoDTO = new ResultadoDTO();
        resultadoDTO.setExitoso(Boolean.FALSE);

        if (tesisDTO == null) {
            resultadoDTO.setMensajeError("La tesis no puede ser nula");
            return resultadoDTO;
        }

        Tesis tesis = tesisMapper.toEntity(tesisDTO);

        tesisRepository.save(tesis);

        resultadoDTO.setExitoso(Boolean.TRUE);
        resultadoDTO.setMensaje("La tesis se ha registrado correctamente");
        return resultadoDTO;
    }

    @Override
    public ResultadoDTO evaluarTesis(TesisDTO tesisDTO) {
        ResultadoDTO resultadoDTO = new TesisOutDTO();
        resultadoDTO.setExitoso(Boolean.FALSE);

        if (tesisDTO.getIdTesis() == null) {
            resultadoDTO.setMensajeError("El id de la tesis es nulo");
            return resultadoDTO;
        }


        Optional<Tesis> tesisOptional = tesisRepository.findById(tesisDTO.getIdTesis());

        if (tesisOptional.isEmpty()) {
            resultadoDTO.setMensajeError("No se ha encontrado la tesis");
            return resultadoDTO;
        }

        tesisRepository.save(tesisMapper.toEntity(tesisDTO));

        resultadoDTO.setExitoso(Boolean.TRUE);
        return resultadoDTO;
    }

    @Override
    public TesisOutDTO consultarTesis(TesisDTO tesisDTO) {
        TesisOutDTO tesisOutDTO = new TesisOutDTO();
        tesisOutDTO.setExitoso(Boolean.FALSE);

        List<Tesis> listaTesis = tesisRepository.findAll();

        if (listaTesis.isEmpty()) {
            tesisOutDTO.setMensaje("No se encontraron tesis");
            return tesisOutDTO;
        }

        List<TesisDTO> listaTesisDTO = listaTesis.stream().map(tesis -> tesisMapper.toDTO(tesis)).collect(Collectors.toList());

        tesisOutDTO.setListaTesisDTO(listaTesisDTO);
        tesisOutDTO.setExitoso(Boolean.TRUE);
        tesisOutDTO.setTotalTesis((long) listaTesis.size());

        return tesisOutDTO;
    }

    @Override
    public TesisOutDTO consultarTesisPrograma(TesisDTO tesisDTO) {
        TesisOutDTO tesisOutDTO = new TesisOutDTO();
        tesisOutDTO.setExitoso(Boolean.FALSE);


        List<Tesis> listaTesis = tesisRepository.findAllByProgramaEnum(tesisDTO.getProgramaEnum());

        if (listaTesis.isEmpty()) {
            tesisOutDTO.setMensaje("no se ecnontrarron tesis de este programa ");
            return tesisOutDTO;
        }
        List<TesisDTO> listaTesisDTO = listaTesis.stream().map(tesis -> tesisMapper.toDTO(tesis)).collect(Collectors.toList());


        tesisOutDTO.setListaTesisDTO(listaTesisDTO);
        tesisOutDTO.setExitoso(Boolean.TRUE);
        tesisOutDTO.setTotalTesis((long) listaTesis.size());

        return tesisOutDTO;
    }

    @Override
    public TesisOutDTO consultarTesisEvaluador(TesisDTO tesisDTO) {
        TesisOutDTO tesisOutDTO = new TesisOutDTO();
        tesisOutDTO.setExitoso(Boolean.FALSE);


        List<Tesis> listaTesis = tesisRepository.findAllByEvaluador(usuarioMapper.toEntity(tesisDTO.getEvaluador()));

        if (listaTesis.isEmpty()) {
            tesisOutDTO.setMensaje("no se encontro evaluador ");
            return tesisOutDTO;
        }
        List<TesisDTO> listaTesisDTO = listaTesis.stream().map(tesis -> tesisMapper.toDTO(tesis)).collect(Collectors.toList());

        tesisOutDTO.setListaTesisDTO(listaTesisDTO);
        tesisOutDTO.setExitoso(Boolean.TRUE);
        tesisOutDTO.setTotalTesis((long) listaTesis.size());

        return tesisOutDTO;
    }

    @Override
    public TesisOutDTO consultarTesisEstudiante(TesisDTO tesisDTO) {
        TesisOutDTO tesisOutDTO = new TesisOutDTO();
        tesisOutDTO.setExitoso(Boolean.FALSE);

        List<Tesis> listaTesis = tesisRepository.findAllByEstudiante(usuarioMapper.toEntity(tesisDTO.getEstudiante()));
        if (listaTesis.isEmpty()) {
            tesisOutDTO.setMensaje("no se encontro evaluador ");
            return tesisOutDTO;
        }
        List<TesisDTO> listaTesisDTO = listaTesis.stream().map(tesis -> tesisMapper.toDTO(tesis)).collect(Collectors.toList());

        tesisOutDTO.setListaTesisDTO(listaTesisDTO);
        tesisOutDTO.setExitoso(Boolean.TRUE);
        tesisOutDTO.setTotalTesis((long) listaTesis.size());


        return tesisOutDTO;

    }

}
