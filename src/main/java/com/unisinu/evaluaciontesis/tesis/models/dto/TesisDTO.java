package com.unisinu.evaluaciontesis.tesis.models.dto;

import com.unisinu.evaluaciontesis.compartidos.ProgramaEnum;
import com.unisinu.evaluaciontesis.tesis.models.enums.EstadoTesisEnum;
import com.unisinu.evaluaciontesis.usuarios.models.dto.UsuarioDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TesisDTO {

    private Long idTesis;
    private String nombre;
    private String descripcion;
    private Byte documento;
    private UsuarioDTO estudiante;
    private ProgramaEnum programaEnum;
    private String observaciones;
    private BigDecimal calificacion;
    private Boolean calificada;
    private UsuarioDTO evaluador;
    private EstadoTesisEnum estadoTesisEnum;
}
