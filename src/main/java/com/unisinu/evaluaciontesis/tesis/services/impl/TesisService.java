package com.unisinu.evaluaciontesis.tesis.services.impl;

import com.unisinu.evaluaciontesis.compartidos.ProgramaEnum;
import com.unisinu.evaluaciontesis.compartidos.ResultadoDTO;
import com.unisinu.evaluaciontesis.tesis.models.dto.TesisDTO;
import com.unisinu.evaluaciontesis.tesis.models.dto.TesisEstudianteDTO;
import com.unisinu.evaluaciontesis.tesis.models.dto.TesisOutDTO;
import com.unisinu.evaluaciontesis.tesis.models.entidades.Tesis;
import com.unisinu.evaluaciontesis.tesis.models.entidades.TesisEstudiante;
import com.unisinu.evaluaciontesis.tesis.models.enums.CalificadaEnum;
import com.unisinu.evaluaciontesis.tesis.models.mappers.TesisEstudianteMapper;
import com.unisinu.evaluaciontesis.tesis.models.mappers.TesisMapper;
import com.unisinu.evaluaciontesis.tesis.repository.ITesisEstudianteRepository;
import com.unisinu.evaluaciontesis.tesis.repository.ITesisRepository;
import com.unisinu.evaluaciontesis.tesis.services.ITesisService;
import com.unisinu.evaluaciontesis.usuarios.models.entidades.Usuario;
import com.unisinu.evaluaciontesis.usuarios.models.mappers.UsuarioMapper;
import com.unisinu.evaluaciontesis.usuarios.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TesisService implements ITesisService {


    @Autowired
    private ITesisRepository tesisRepository;

    @Autowired
    private ITesisEstudianteRepository tesisEstudianteRepository;

    @Autowired
    private TesisMapper tesisMapper;

    @Autowired
    UsuarioMapper usuarioMapper;

    @Autowired
    IUsuarioRepository usuarioRepository;

    @Autowired
    private TesisEstudianteMapper tesisEstudianteMapper;

    @Override
    @Transactional
    public ResultadoDTO guardarTesis(TesisDTO tesisDTO, MultipartFile archivo) {
        ResultadoDTO resultadoDTO = new ResultadoDTO();
        resultadoDTO.setExitoso(Boolean.FALSE);

        List<Usuario> estudiantes = new ArrayList<>();

        Usuario estudiante1 = consultarUsuarioPorCodigoCarnet(tesisDTO.getEstudiante().getCodigoCarnet());
        if (estudiante1 == null) {
            resultadoDTO.setMensaje("El estudiante con carnet: " + tesisDTO.getEstudiante().getCodigoCarnet() + " no existe");
            return resultadoDTO;
        }
        estudiantes.add(estudiante1);

        if (tesisDTO.getEstudiante2() != null) {
            Usuario estudiante2 = consultarUsuarioPorCodigoCarnet(tesisDTO.getEstudiante2().getCodigoCarnet());
            if (estudiante2 == null) {
                resultadoDTO.setMensaje("El estudiante con carnet: " + tesisDTO.getEstudiante2().getCodigoCarnet() + " no existe");
                return resultadoDTO;
            }
            estudiantes.add(estudiante2);
        }

        String extesion = obtenerExtesionArchivo(archivo.getOriginalFilename());

        tesisDTO.setExtension(extesion);
        tesisDTO.setCalificada(CalificadaEnum.SIN_CALIFICAR);


        Tesis tesis = tesisMapper.toEntity(tesisDTO);
        tesis.setFechaCreacion(LocalDateTime.now());

        try {
            tesis.setDocumento(archivo.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        tesis = tesisRepository.save(tesis);

        for (Usuario estudiante : estudiantes) {
            TesisEstudiante tesisEstudiante = new TesisEstudiante();
            tesisEstudiante.setUsuarioDTOEstudiante(estudiante);
            tesisEstudiante.setTesisDTOEstudiante(tesis);
            tesisEstudianteRepository.save(tesisEstudiante);
        }

        resultadoDTO.setExitoso(Boolean.TRUE);
        resultadoDTO.setMensaje("La tesis se ha registrado correctamente");
        return resultadoDTO;
    }

    private String obtenerExtesionArchivo(String originalFilename) {
        int puntoUltimo = originalFilename.lastIndexOf('.');

        // Verificar si hay un punto en el nombre del archivo
        if (puntoUltimo == -1) {
            return null; // No se encontró un punto en el nombre del archivo
        }
        // Obtener la extensión del archivo
        return originalFilename.substring(puntoUltimo + 1).toLowerCase();

    }

    private Usuario consultarUsuarioPorCodigoCarnet(String codigoCarnet) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByCodigoCarnet(codigoCarnet);

        if (usuarioOptional.isEmpty()) {
            return null;
        }

        return usuarioOptional.get();
    }

    @Override
    public ResultadoDTO evaluarTesis(TesisDTO tesisDTO, MultipartFile archivo) {
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



        tesisOptional.get().setCalificada(CalificadaEnum.CALIFICADA);
        tesisOptional.get().setCalificacion(tesisDTO.getCalificacion());
        tesisOptional.get().setObservaciones(tesisDTO.getObservaciones());
        try {
            if(archivo != null && !archivo.isEmpty()){
                String extesion = obtenerExtesionArchivo(archivo.getOriginalFilename());
                tesisOptional.get().setDocumentoSoporte(archivo.getBytes());
                tesisOptional.get().setExtensionDocumentoSoporte(extesion);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        tesisRepository.save(tesisOptional.get());

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

        Tesis tesisBuscar = tesisRepository.findById(idTesis).orElse(null);

        List<TesisEstudiante> listaTesis = tesisEstudianteRepository.findAllByTesisDTOEstudiante(tesisBuscar);

        if (listaTesis == null || listaTesis.isEmpty()) {
            tesisOutDTO.setMensaje("No se encontró la tesis");
            return tesisOutDTO;
        }

        List<TesisEstudianteDTO> listaTesisEstudianteDTO = listaTesis.stream().map(tesisEstudianteMapper::toDTO).collect(Collectors.toList());

        for (TesisEstudianteDTO tesisEstudianteDTO : listaTesisEstudianteDTO) {
            tesisEstudianteDTO.getUsuarioDTOEstudiante().setPassword(null);

            StringBuilder nombreCompleto = new StringBuilder();
            nombreCompleto.append(tesisEstudianteDTO.getUsuarioDTOEstudiante().getNombre());

            if (tesisEstudianteDTO.getUsuarioDTOEstudiante().getSegundoNombre() != null) {
                nombreCompleto.append(" ").append(tesisEstudianteDTO.getUsuarioDTOEstudiante().getSegundoNombre());
            }
            nombreCompleto.append(" ").append(tesisEstudianteDTO.getUsuarioDTOEstudiante().getApellido());
            if (tesisEstudianteDTO.getUsuarioDTOEstudiante().getSegundoApellido() != null) {
                nombreCompleto.append(" ").append(tesisEstudianteDTO.getUsuarioDTOEstudiante().getSegundoApellido());
            }
            String nombreCompletoString = nombreCompleto.toString();

            tesisEstudianteDTO.getUsuarioDTOEstudiante().setNombreCompleto(nombreCompletoString);
        }

        tesisOutDTO.setTesisDTO(tesisMapper.toDTO(tesisBuscar));
        tesisOutDTO.setTesisEstudianteDTO(listaTesisEstudianteDTO);
        tesisOutDTO.setExitoso(Boolean.TRUE);

        return tesisOutDTO;
    }

    @Override
    public TesisOutDTO consultarTesisPrograma(ProgramaEnum programaEnum) {
        TesisOutDTO tesisOutDTO = new TesisOutDTO();
        tesisOutDTO.setExitoso(Boolean.FALSE);


        List<Tesis> listaTesis = tesisRepository.findAllByProgramaEnum(programaEnum);

        if (listaTesis.isEmpty()) {
            tesisOutDTO.setMensaje("no se encontrarron tesis de este programa ");
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

        Usuario estudiante = new Usuario();
        estudiante.setIdUsuario(idEstudiante);

        List<TesisEstudiante> listaTesisEstudiante = tesisEstudianteRepository.findAllByUsuarioDTOEstudiante(estudiante);
        if (listaTesisEstudiante.isEmpty()) {
            tesisOutDTO.setMensaje("no se encontaron tesis de este estudiante");
            return tesisOutDTO;
        }

        List<TesisEstudianteDTO> listaTesiEstudianteDTO = listaTesisEstudiante.stream().map(tesisEstudiante -> tesisEstudianteMapper.toDTO(tesisEstudiante)).collect(Collectors.toList());

        for (TesisEstudianteDTO tesisEstudianteDTO : listaTesiEstudianteDTO) {
            tesisEstudianteDTO.getUsuarioDTOEstudiante().setPassword(null);
        }

        tesisOutDTO.setTesisEstudianteDTO(listaTesiEstudianteDTO);
        tesisOutDTO.setExitoso(Boolean.TRUE);
        tesisOutDTO.setTotalTesis((long) listaTesiEstudianteDTO.size());


        return tesisOutDTO;

    }

}
