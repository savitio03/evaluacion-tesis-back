package com.unisinu.evaluaciontesis.usuarios.models.dto;

import com.unisinu.evaluaciontesis.compartidos.ProgramaEnum;
import com.unisinu.evaluaciontesis.compartidos.TipoIdentificacionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FiltroConsultaUsuariosDTO {

    private String nombre;
    private String numeroCarnet;
    private ProgramaEnum programa;
    private String numeroIdentificacion;
    private TipoIdentificacionEnum tipoIdentificacionEnum;
}
