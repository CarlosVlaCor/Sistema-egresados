package com.sistemaegresados;

import com.sistemaegresados.dto.RespuestaComentario;
import com.sistemaegresados.servicio.ComentarioServicio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ListaComentariosApplicationTest {
    @Autowired
    ComentarioServicio comentarioServicio;
            
    @Test
        void contextLoads() {
            RespuestaComentario res =comentarioServicio.obtenerComentarios(0, 1, "ASC");
            System.out.println(res.getTotalPaginas());
            System.out.println(res.getNroPagina());
            System.out.println(res.isUltima());
            res.getContenido().forEach(comentario -> System.out.println(comentario.getEstudiante().getComentario().getFecha()));
            
            
        }
        
}
