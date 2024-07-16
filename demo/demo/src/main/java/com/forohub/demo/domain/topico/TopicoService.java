package com.forohub.demo.domain.topico;

import com.forohub.demo.infra.errores.ValidacionDeIntegridad;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    public Topico registrarTopico(DatosRegistro datosRegistro) {
        Optional<Topico> existente = topicoRepository.findByTituloAndMensaje(datosRegistro.titulo(), datosRegistro.mensaje());
        if (existente.isPresent()) {
            throw new ValidacionDeIntegridad("El tópico con el mismo título y mensaje ya existe.");
        }

        Topico topico = new Topico(datosRegistro);
        return topicoRepository.save(topico);
    }

    public Topico obtenerTopicoPorId(Long id) {
        return topicoRepository.findById(id)
                .orElseThrow(() -> new ValidacionDeIntegridad("ID no encontrado: " + id));
    }

//    public Page<DatosListado> listarTopicos(Pageable paginacion) {
//        return topicoRepository.findAll(paginacion).map(DatosListado::new );
//    }

    public Topico eliminarTopico(Long id) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tópico no encontrado"));
        topicoRepository.deleteById(id);
        return topico;
    }

    public Topico actualizar(Long id, DatosActualizar datosActualizar) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ValidacionDeIntegridad("ID no encontrado: " + id));

        topico.actualizar(datosActualizar);
        return topicoRepository.save(topico);
    }

}
