package com.unisinu.evaluaciontesis.tesis.models.entidades;

import com.unisinu.evaluaciontesis.compartidos.ProgramaEnum;
import com.unisinu.evaluaciontesis.tesis.models.enums.CalificadaEnum;
import com.unisinu.evaluaciontesis.tesis.models.enums.EstadoTesisEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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

    @Column(name = "EVA_COLUMN_PROGRAMAENUM")
    @Enumerated(EnumType.STRING)
    private ProgramaEnum programaEnum;

    @Column(name = "EVA_COLUMN_OBSERVACIONES")
    private String observaciones;

    @Column(name = "EVA_COLUMN_CALIFICACION")
    private BigDecimal calificacion;

    @Column(name = "EVA_COLUMN_CALIFICADA")
    @Enumerated(EnumType.STRING)
    private CalificadaEnum calificada;

    @Column(name = "EVA_COLUMN_ESTADO")
    @Enumerated(EnumType.STRING)
    private EstadoTesisEnum estadoTesisEnum;

    @Lob
    @Column(name = "EVA_COLUMN_DOCUMENTO")
    private byte[] documento;

    @Column(name = "EVA_COLUMN_FECHACREACION")
    private LocalDateTime fechaCreacion;

    @Column(name = "EVA_COLUMN_EXTENSION")
    private String extension;

    @Lob
    @Column(name = "EVA_COLUMN_DOCUMENTO_SOPORTE")
    private byte[] documentoSoporte;

    @Column(name = "EVA_COLUMN_EXTENSION_DOCUMENTO_SOPORTE")
    private String extensionDocumentoSoporte;

}
