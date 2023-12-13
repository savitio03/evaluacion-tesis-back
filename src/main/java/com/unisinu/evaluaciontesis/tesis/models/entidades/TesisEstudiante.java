package com.unisinu.evaluaciontesis.tesis.models.entidades;

import com.unisinu.evaluaciontesis.usuarios.models.entidades.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "EVA_TESIS_ESTUDIANTES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TesisEstudiante {

    @Id
    @Column(name = "EVA_COLUMN_IDTESIS_ESTUDIANTE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EVA_TESIS_ESTUDIANTE_SEQ")
    private Long idTesisEstudiante;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "EVA_COLUMN_TESIS", referencedColumnName = "EVA_COLUMN_IDTESIS")
    private Tesis tesisDTOEstudiante;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "EVA_COLUMN_ESTUDIANTE", referencedColumnName = "EVA_COLUMN_IDUSUARIO")
    private Usuario usuarioDTOEstudiante;

}
