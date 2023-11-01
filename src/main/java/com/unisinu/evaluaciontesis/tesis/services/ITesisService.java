package com.unisinu.evaluaciontesis.tesis.services;

import com.unisinu.evaluaciontesis.compartidos.ResultadoDTO;
import com.unisinu.evaluaciontesis.tesis.models.dto.TesisDTO;
import com.unisinu.evaluaciontesis.tesis.models.dto.TesisOutDTO;

public interface ITesisService {

    ResultadoDTO guardarTesis(TesisDTO tesisDTO);
    ResultadoDTO evaluarTesis(TesisDTO tesisDTO);

    TesisOutDTO consultarTesis(TesisDTO tesisDTO);

    TesisOutDTO consultarTesisPrograma(TesisDTO tesisDTO);

    TesisOutDTO consultarTesisEvaluador(TesisDTO tesisDTO);

    TesisOutDTO consultarTesisEstudiante(TesisDTO tesisDTO);


}
