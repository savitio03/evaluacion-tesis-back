package com.unisinu.evaluaciontesis.tesis.controllers;

import com.unisinu.evaluaciontesis.compartidos.ResultadoDTO;
import com.unisinu.evaluaciontesis.tesis.models.dto.TesisDTO;
import com.unisinu.evaluaciontesis.tesis.models.dto.TesisOutDTO;
import com.unisinu.evaluaciontesis.tesis.services.ITesisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http:localhost:4200")
@RestController
@RequestMapping("tesis")
public class TesisController {

    @Autowired
    private ITesisService tesisService;

    @PostMapping("guardarTesis")
    public ResultadoDTO guardarTesis(@RequestBody TesisDTO tesisDTO) {
        return tesisService.guardarTesis(tesisDTO);
    }

    @PostMapping("evaluarTesis")
    public ResultadoDTO evaluarTesis(@RequestBody TesisDTO tesisDTO) {
        return tesisService.evaluarTesis(tesisDTO);
    }

    @PostMapping("consultarTesis")
    public TesisOutDTO consultarTesis(@RequestBody TesisDTO tesisDTO) {
        return tesisService.consultarTesis(tesisDTO);
    }

    @PostMapping("consultarTesisPrograma")
    public TesisOutDTO consultarTesisPrograma(@RequestBody TesisDTO tesisDTO) {
        return tesisService.consultarTesisPrograma(tesisDTO);
    }

    @PostMapping("consultarTesisEvaluador")
    public TesisOutDTO consultarTesisEvaluador(@RequestBody TesisDTO tesisDTO) {
        return tesisService.consultarTesisEvaluador(tesisDTO);
    }

    @PostMapping("consultarTesisEstudiante")
    public TesisOutDTO consultarTesisEstudiante(@RequestBody TesisDTO tesisDTO) {
        return tesisService.consultarTesisEstudiante(tesisDTO);
    }

}
