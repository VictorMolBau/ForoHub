package com.forohub.demo.controller;

import com.forohub.demo.domain.topico.*;

import com.forohub.demo.infra.errores.IdRequeridoException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
//@Security(name = "bearer-key")
public class ForoControler {

    @Autowired
    private TopicoService topicoService;

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    public ResponseEntity<?> registrarTopico(@RequestBody @Valid DatosRegistro datosRegistro){
        Topico topico = topicoService.registrarTopico(datosRegistro);
        return ResponseEntity.ok().body(new DatosDetalle(topico));
    }
    @GetMapping
    public ResponseEntity<Page<DatosListado>> listar(
            @PageableDefault(size = 10, sort = "fechaCreacion", direction = Sort.Direction.ASC) Pageable paginacion
    ) {
        return ResponseEntity.ok().body(
                topicoRepository.findAll(paginacion).map(DatosListado::new)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerTopicoPorId(@PathVariable(required = false) Long id) {
        if (id == null) {
            throw new IdRequeridoException("ID es obligatorio para esta consulta");
        }
        Topico topico = topicoService.obtenerTopicoPorId(id);
        DatosDetalle detalleTopico = new DatosDetalle(topico);
        return ResponseEntity.ok().body(detalleTopico);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarTopico(@PathVariable Long id) {
        Topico topico = topicoService.eliminarTopico(id);
        return ResponseEntity.ok().body(new DatosDetalle(topico));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarTopico(@PathVariable Long id, @Valid @RequestBody DatosActualizar datosActualizar) {
        Topico topico = topicoService.actualizar(id, datosActualizar);
        return ResponseEntity.ok().body(new DatosDetalle(topico));
    }

}
