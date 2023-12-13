package com.unisinu.evaluaciontesis.tesis.models.dto;

import com.unisinu.evaluaciontesis.compartidos.ProgramaEnum;
import com.unisinu.evaluaciontesis.tesis.models.enums.CalificadaEnum;
import com.unisinu.evaluaciontesis.tesis.models.enums.EstadoTesisEnum;
import com.unisinu.evaluaciontesis.usuarios.models.dto.UsuarioDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TesisDTO {

    private Long idTesis;
    private String nombre;
    private String descripcion;
    private MultipartFile archivo;
    private byte[] documento;
    private UsuarioDTO estudiante;
    private ProgramaEnum programaEnum;
    private String observaciones;
    private BigDecimal calificacion;
    private CalificadaEnum calificada;
    private UsuarioDTO evaluador;
    private EstadoTesisEnum estadoTesisEnum;
    private UsuarioDTO estudiante2;
    private LocalDateTime fechaCreacion;
    private String extension;
}
