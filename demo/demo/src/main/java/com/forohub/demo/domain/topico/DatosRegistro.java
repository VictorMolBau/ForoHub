package com.forohub.demo.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosRegistro(
        @NotBlank String titulo,
        @NotBlank String mensaje,
        @NotNull LocalDateTime fechaCreacion,
        @NotNull StatusTopico status,
        @NotBlank String autor,
        @NotBlank String curso){

}
