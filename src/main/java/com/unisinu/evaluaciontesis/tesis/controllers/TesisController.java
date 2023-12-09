package com.unisinu.evaluaciontesis.tesis.controllers;

import com.unisinu.evaluaciontesis.compartidos.ProgramaEnum;
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

    @GetMapping("consultarTesis")
    public TesisOutDTO consultarTesis() {
        return tesisService.consultarTesis();
    }

    @GetMapping("consultarDetalleTesis")
    public TesisOutDTO consultarDetalleTesis(@RequestParam Long idTesis) {
        return tesisService.consultarDetalleTesis(idTesis);
    }
    @PostMapping("consultarTesisPrograma")
    public TesisOutDTO consultarTesisPrograma(@RequestParam ProgramaEnum programaEnum) {
        return tesisService.consultarTesisPrograma(programaEnum);
    }

    @GetMapping("consultarTesisEvaluador")
    public TesisOutDTO consultarTesisEvaluador(@RequestParam Long idEvaluador) {
        return tesisService.consultarTesisEvaluador(idEvaluador);
    }

    @GetMapping("consultarTesisEstudiante")
    public TesisOutDTO consultarTesisEstudiante(@RequestParam Long idEstudiante) {
        return tesisService.consultarTesisEstudiante(idEstudiante);
    }

}
