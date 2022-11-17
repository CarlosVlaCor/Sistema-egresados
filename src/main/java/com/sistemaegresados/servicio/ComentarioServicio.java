package com.sistemaegresados.servicio;

import com.sistemaegresados.dto.RespuestaComentario;

public interface ComentarioServicio {
    
    public RespuestaComentario obtenerComentarios(int nroPagina, int carrera,String modoOrden);
}
