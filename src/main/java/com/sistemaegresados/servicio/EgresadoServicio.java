package com.sistemaegresados.servicio;

import com.sistemaegresados.dto.RespuestaGraficacion;
import com.sistemaegresados.entidades.Estudiante;

public interface EgresadoServicio {
    
    public String ingresarEstudiante(Estudiante estudiante);
    
    public Estudiante buscarEstudiante(String nroControl);
    
    public Estudiante modificarEstudiante(Estudiante estudiante, String nroControl);
    
    public void eliminarEgresado(String nroControl);
    
    public RespuestaGraficacion datosGraficacion();
}
