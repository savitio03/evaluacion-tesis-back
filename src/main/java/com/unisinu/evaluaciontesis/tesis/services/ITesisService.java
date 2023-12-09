package com.unisinu.evaluaciontesis.tesis.services;

import com.unisinu.evaluaciontesis.compartidos.ProgramaEnum;
import com.unisinu.evaluaciontesis.compartidos.ResultadoDTO;
import com.unisinu.evaluaciontesis.tesis.models.dto.TesisDTO;
import com.unisinu.evaluaciontesis.tesis.models.dto.TesisOutDTO;

public interface ITesisService {

    ResultadoDTO guardarTesis(TesisDTO tesisDTO);
    ResultadoDTO evaluarTesis(TesisDTO tesisDTO);

    TesisOutDTO consultarTesis();

    TesisOutDTO consultarDetalleTesis(Long idTesis);

    TesisOutDTO consultarTesisPrograma(ProgramaEnum programaEnum);

    TesisOutDTO consultarTesisEvaluador(Long idEvaluador);

    TesisOutDTO consultarTesisEstudiante(Long idEstudiante);


}
