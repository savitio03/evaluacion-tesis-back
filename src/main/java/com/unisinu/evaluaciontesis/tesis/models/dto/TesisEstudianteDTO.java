package com.unisinu.evaluaciontesis.tesis.models.dto;

import com.unisinu.evaluaciontesis.usuarios.models.dto.UsuarioDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TesisEstudianteDTO {
    private Long idTesisEstudiante;
    private TesisDTO tesisDTOEstudiante;
    private UsuarioDTO usuarioDTOEstudiante;

}
