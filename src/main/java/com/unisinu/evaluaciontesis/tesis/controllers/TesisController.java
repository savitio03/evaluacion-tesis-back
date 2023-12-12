package com.unisinu.evaluaciontesis.tesis.controllers;

import com.unisinu.evaluaciontesis.compartidos.ProgramaEnum;
import com.unisinu.evaluaciontesis.compartidos.ResultadoDTO;
import com.unisinu.evaluaciontesis.tesis.models.dto.TesisDTO;
import com.unisinu.evaluaciontesis.tesis.models.dto.TesisOutDTO;
import com.unisinu.evaluaciontesis.tesis.services.ITesisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "http:localhost:4200")
@RestController
@RequestMapping("tesis")
public class TesisController {

    @Autowired
    private ITesisService tesisService;

    @PostMapping(value = "guardarTesis", consumes = MediaType.ALL_VALUE)
    public ResultadoDTO guardarTesis(@RequestPart("tesisDTO") TesisDTO tesisDTO,
                                     @RequestPart("archivo") MultipartFile archivo) {
        return tesisService.guardarTesis(tesisDTO, archivo);
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

    @GetMapping("consultarTesisPrograma")
    public TesisOutDTO consultarTesisPrograma(@RequestParam ProgramaEnum programaEnum) {
        return tesisService.consultarTesisPrograma(programaEnum);
    }

    @GetMapping("consultarTesisEstudiante")
    public TesisOutDTO consultarTesisEstudiante(@RequestParam Long idEstudiante) {
        return tesisService.consultarTesisEstudiante(idEstudiante);
    }

}
