package com.unisinu.evaluaciontesis.tesis.services.impl;

import com.unisinu.evaluaciontesis.compartidos.ProgramaEnum;
import com.unisinu.evaluaciontesis.compartidos.ResultadoDTO;
import com.unisinu.evaluaciontesis.tesis.models.dto.TesisDTO;
import com.unisinu.evaluaciontesis.tesis.models.dto.TesisOutDTO;
import com.unisinu.evaluaciontesis.tesis.models.entidades.Tesis;
import com.unisinu.evaluaciontesis.tesis.models.mappers.TesisMapper;
import com.unisinu.evaluaciontesis.tesis.repository.ITesisRepository;
import com.unisinu.evaluaciontesis.tesis.services.ITesisService;
import com.unisinu.evaluaciontesis.usuarios.models.entidades.Usuario;
import com.unisinu.evaluaciontesis.usuarios.models.mappers.UsuarioMapper;
import com.unisinu.evaluaciontesis.usuarios.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
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

    @Autowired
    IUsuarioRepository usuarioRepository;

    @Override
    public ResultadoDTO guardarTesis(TesisDTO tesisDTO) {
        ResultadoDTO resultadoDTO = new ResultadoDTO();
        resultadoDTO.setExitoso(Boolean.FALSE);


        if (tesisDTO == null) {
            resultadoDTO.setMensajeError("La tesis no puede ser nula");
            return resultadoDTO;
        }

        if (tesisDTO.getNombre() == null || tesisDTO.getNombre().isEmpty()) {
            resultadoDTO.setMensajeError("El nombre de la tesis no puede ser nulo o vacío");
            return resultadoDTO;
        }

        Usuario estudiante = consultarUsuarioPorCodigoCarnet(tesisDTO.getEstudiante().getCodigoCarnet());

        Usuario evaluador = consultarUsuarioPorCodigoCarnet(tesisDTO.getEvaluador().getCodigoCarnet());

        if (tesisDTO.getEstudiante2() != null) {
            Usuario estudiante2 = consultarUsuarioPorCodigoCarnet(tesisDTO.getEstudiante2().getCodigoCarnet());
            tesisDTO.setEstudiante2(usuarioMapper.toDTO(estudiante2));
        }

        tesisDTO.setEvaluador(usuarioMapper.toDTO(evaluador));
        tesisDTO.setEstudiante(usuarioMapper.toDTO(estudiante));
        tesisDTO.setCalificada(Boolean.FALSE);

        Tesis tesis = tesisMapper.toEntity(tesisDTO);

        try {
            if (tesisDTO.getArchivo() == null) {
                resultadoDTO.setMensajeError("El documento de la tesis no puede ser nulo");
                return resultadoDTO;
            }

            tesis.setDocumento(tesisDTO.getArchivo().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        tesisRepository.save(tesis);

        resultadoDTO.setExitoso(Boolean.TRUE);
        resultadoDTO.setMensaje("La tesis se ha registrado correctamente");
        return resultadoDTO;
    }

    private Usuario consultarUsuarioPorCodigoCarnet(String codigoCarnet) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByCodigoCarnet(codigoCarnet);

        if (usuarioOptional.isEmpty()) {
            throw new RuntimeException("No se ha encontrado el usuario");
        }

        return usuarioOptional.get();
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
    public TesisOutDTO consultarTesis() {
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
    public TesisOutDTO consultarDetalleTesis(Long idTesis) {
        TesisOutDTO tesisOutDTO = new TesisOutDTO();
        tesisOutDTO.setExitoso(Boolean.FALSE);

        Optional<Tesis> tesisOptional = tesisRepository.findById(idTesis);

        if (tesisOptional.isEmpty()) {
            tesisOutDTO.setMensaje("No se encontró la tesis");
            return tesisOutDTO;
        }

        TesisDTO tesisDTO = tesisMapper.toDTO(tesisOptional.get());

        tesisOutDTO.setTesisDTO(tesisDTO);
        tesisOutDTO.setExitoso(Boolean.TRUE);

        return tesisOutDTO;
    }

    @Override
    public TesisOutDTO consultarTesisPrograma(ProgramaEnum programaEnum) {
        TesisOutDTO tesisOutDTO = new TesisOutDTO();
        tesisOutDTO.setExitoso(Boolean.FALSE);


        List<Tesis> listaTesis = tesisRepository.findAllByProgramaEnum(programaEnum);

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
    public TesisOutDTO consultarTesisEvaluador(Long idEvaluador){
        TesisOutDTO tesisOutDTO = new TesisOutDTO();
        tesisOutDTO.setExitoso(Boolean.FALSE);

        Usuario usuario = new Usuario();
        usuario.setIdUsuario(idEvaluador);

        List<Tesis> listaTesis = tesisRepository.findAllByEvaluador(usuario);

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
    public TesisOutDTO consultarTesisEstudiante(Long idEstudiante) {
        TesisOutDTO tesisOutDTO = new TesisOutDTO();
        tesisOutDTO.setExitoso(Boolean.FALSE);

        Usuario estudiante = new Usuario()  ;
        estudiante.setIdUsuario(idEstudiante);

        List<Tesis> listaTesis = tesisRepository.findAllByEstudiante(estudiante);
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
