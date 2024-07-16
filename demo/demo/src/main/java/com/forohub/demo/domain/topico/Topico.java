package com.forohub.demo.domain.topico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table (name = "topico")
@Entity (name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")


public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    @Enumerated(EnumType.STRING)
    private StatusTopico status;

    private String autor;
    private String curso;

    public Topico(DatosRegistro datosRegistro) {
        this.titulo = datosRegistro.titulo();
        this.mensaje = datosRegistro.mensaje();
        this.fechaCreacion =LocalDateTime.now();
        this.status = datosRegistro.status();
        this.autor = datosRegistro.autor();
        this.curso = datosRegistro.curso();
    }

    public void actualizar(DatosActualizar datosActualizar) {
        if (datosActualizar.titulo() != null){
            this.titulo = datosActualizar.titulo();
        }
        if (datosActualizar.mensaje() != null){
            this.mensaje = datosActualizar.mensaje();
        }
        if (datosActualizar.status() != null) {
            this.status = datosActualizar.status();
        }
        if (datosActualizar.autor() != null) {
            this.autor = datosActualizar.autor();
        }
        if (datosActualizar.curso() != null) {
            this.curso = datosActualizar.curso();
        }


    }

}
