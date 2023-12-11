package com.unisinu.evaluaciontesis.usuarios.models.dto;

import com.unisinu.evaluaciontesis.compartidos.ProgramaEnum;
import com.unisinu.evaluaciontesis.compartidos.RolUsuarioEnum;
import com.unisinu.evaluaciontesis.compartidos.SexoEnum;
import com.unisinu.evaluaciontesis.compartidos.TipoIdentificacionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    private Long idUsuario;
    private String nombre;
    private String segundoNombre;
    private String apellido;
    private String segundoApellido;
    private String codigoCarnet;
    private TipoIdentificacionEnum tipoIdentificacionEnum;
    private String numeroIdenticacion;
    private SexoEnum sexoEnum;
    private RolUsuarioEnum rol;
    private String numeroCelular;
    private String correo;
    private String password;
    private ProgramaEnum programaEnum;
    private LocalDateTime fechaCreacion;
    private String nombreCompleto;
}
