package com.unisinu.evaluaciontesis.tesis.models.dto;

import com.unisinu.evaluaciontesis.compartidos.ResultadoDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TesisOutDTO extends ResultadoDTO {


    private TesisDTO tesisDTO;
    private List<TesisDTO> listaTesisDTO;
    private Long totalTesis;
}
