package com.forohub.demo.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizar (
        @NotNull Long id,
        @NotBlank String titulo,
        @NotBlank String mensaje,
        @NotNull StatusTopico status,
        @NotBlank String autor,
        @NotBlank String curso) {
}
