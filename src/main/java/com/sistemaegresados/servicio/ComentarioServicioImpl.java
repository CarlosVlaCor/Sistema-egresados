package com.sistemaegresados.servicio;

import com.sistemaegresados.dto.RespuestaComentario;
import com.sistemaegresados.entidades.Comentario;
import com.sistemaegresados.repositorio.ComentarioRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ComentarioServicioImpl implements ComentarioServicio {

    @Autowired
    private ComentarioRepositorio comentarioRepositorio;

    @Override
    public RespuestaComentario obtenerComentarios(int nroPagina, int carrera, String modoOrden) {
        Sort acomodo = modoOrden.equalsIgnoreCase(Sort.Direction.DESC.name())
                ? Sort.by("fecha").descending() : Sort.by("fecha").ascending();

        Pageable pageable = PageRequest.of(nroPagina, RespuestaComentario.TAMANO_PAGINA, acomodo);
        Page<Comentario> comentarios = comentarioRepositorio.findAll(pageable);
        
        RespuestaComentario respuestaComentario = new RespuestaComentario();
        List<Comentario> listaComentarios = comentarios.getContent();
        
        if (carrera != 0) {
            List<Comentario> comentariosFiltrados = listaComentarios.stream()
                            .filter(comentario -> comentario.getEstudiante()
                            .getDatosEscolares()
                            .getCarrera()
                            .getId() == carrera).toList();
            respuestaComentario.setContenido(comentariosFiltrados);
        }else{
            respuestaComentario.setContenido(listaComentarios);
        }
        respuestaComentario.setSeccionActual(1);
        respuestaComentario.setNroPagina(comentarios.getNumber());
        respuestaComentario.setIdCarrera(carrera);
        respuestaComentario.setTotalElementos(comentarios.getNumberOfElements());
        respuestaComentario.setTotalPaginas(comentarios.getTotalPages());
        respuestaComentario.setUltima(comentarios.isLast());
        respuestaComentario.setPrimerPagina(comentarios.isFirst());
        respuestaComentario.setVacio(comentarios.isEmpty());
        
        return respuestaComentario;
    }

}
