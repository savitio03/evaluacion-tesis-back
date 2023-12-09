package com.unisinu.evaluaciontesis.tesis.models.entidades;

import com.unisinu.evaluaciontesis.compartidos.ProgramaEnum;
import com.unisinu.evaluaciontesis.tesis.models.enums.EstadoTesisEnum;
import com.unisinu.evaluaciontesis.usuarios.models.entidades.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "EVA_TESIS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tesis {

    @Id
    @Column(name = "EVA_COLUMN_IDTESIS")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EVA_TESIS_SEQ")
    private Long idTesis;

    @Column(name = "EVA_COLUMN_NOMBRE")
    private String nombre;

    @Column(name = "EVA_COLUMN_DESCRIPCION")
    private String descripcion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "EVA_COLUMN_ESTUDIANTE_ID", referencedColumnName = "EVA_COLUMN_IDUSUARIO")
    private Usuario estudiante;

    @Column(name = "EVA_COLUMN_PROGRAMAENUM")
    @Enumerated(EnumType.STRING)
    private ProgramaEnum programaEnum;

    @Column(name = "EVA_COLUMN_OBSERVACIONES")
    private String observaciones;

    @Column(name = "EVA_COLUMN_CALIFICACION")
    private BigDecimal calificacion;

    @Column(name = "EVA_COLUMN_CALIFICADA")
    private Boolean calificada;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "EVA_COLUMN_EVALUADOR_ID", referencedColumnName = "EVA_COLUMN_IDUSUARIO")
    private Usuario evaluador;

    @Column(name = "EVA_COLUMN_ESTADO")
    @Enumerated(EnumType.STRING)
    private EstadoTesisEnum estadoTesisEnum;

    @Lob
    @Column(name = "EVA_COLUMN_DOCUMENTO")
    private byte[] documento;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "EVA_COLUMN_ESTUDIANTE2_ID", referencedColumnName = "EVA_COLUMN_IDUSUARIO")
    private Usuario estudiante2;

}
