package com.unisinu.evaluaciontesis.compartidos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResultadoDTO {

    private Boolean exitoso;

    private String mensajeError;

    private String mensaje;
}
