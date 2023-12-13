package com.unisinu.evaluaciontesis.usuarios.models.entidades;

import com.unisinu.evaluaciontesis.compartidos.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "EVA_USUARIO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @Column(name = "EVA_COLUMN_IDUSUARIO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EVA_USUARIO_SEQ")
    private Long idUsuario;

    @Column(name = "EVA_COLUMN_NOMBRE")
    private String nombre;

    @Column(name = "EVA_COLUMN_SEGUNDONOMBRE")
    private String segundoNombre;

    @Column(name = "EVA_COLUMN_APELLIDO")
    private String apellido;

    @Column(name = "EVA_COLUMN_SEGUNDOAPELLIDO")
    private String segundoApellido;

    @Column(name = "EVA_COLUMN_CODIGOCARNET")
    private String codigoCarnet;

    @Column(name = "EVA_COLUMN_TIPOIDENTIFICACIONENUM")
    @Enumerated(EnumType.STRING)
    private TipoIdentificacionEnum tipoIdentificacionEnum;

    @Column(name = "EVA_COLUMN_NUMEROIDENTIFICACION")
    private String numeroIdenticacion;

    @Column(name = "EVA_COLUMN_SEXOENUM")
    @Enumerated(EnumType.STRING)
    private SexoEnum sexoEnum;

    @Column(name = "EVA_COLUMN_ROL")
    @Enumerated(EnumType.STRING)
    private RolUsuarioEnum rol;

    @Column(name = "EVA_COLUMN_NUMEROCELULAR")
    private String numeroCelular;

    @Column(name = "EVA_COLUMN_CORREO")
    private String correo;

    @Column(name = "EVA_COLUMN_PASSWORD")
    private String password;

    @Column(name = "EVA_COLUMN_PROGRAMAENUM")
    @Enumerated(EnumType.STRING)
    private ProgramaEnum programaEnum;

    @Column(name = "EVA_COLUMN_FECHACREACION")
    private LocalDateTime fechaCreacion;

    @Column(name = "EVA_COLUMN_ESTADO_CUENTA")
    @Enumerated(EnumType.STRING)
    private EstadoCuentaEnum estadoCuentaEnum;
}
