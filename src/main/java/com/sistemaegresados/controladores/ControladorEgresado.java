package com.sistemaegresados.controladores;

import com.sistemaegresados.entidades.Estudiante;
import com.sistemaegresados.repositorio.EstudianteRepositorio;
import com.sistemaegresados.servicio.EgresadoServicio;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/egresados")
public class ControladorEgresado {
    @Autowired
    private EgresadoServicio egresadoServicio;
    @GetMapping("/agregarEgresado")
    public String agregarEgresado(Estudiante estudiante){
        return "agregarEgresado";
    }
    @GetMapping("/datosPersonales")
    public String mostrarDatosPersonales(){
        return "datosPersonales";
    }
    @GetMapping("/datosEscolares")
    public String mostrarDatosEscolares(){
        return "datosEscolares";
    }
    @GetMapping("/datosLaborales")
    public String mostrarDatosLaborales(){
        return "datosLaborales";
    }
    @GetMapping("/estadisticas")
    public String mostrarEstadisticas(){
        return "estadisticas";
    }
    @GetMapping("/comentarios")
    public String mostrarFeedback(){
        return "feedback";
    }
    
    @PostMapping("/agregarEgresado")
    public String agregarUsuario(Estudiante estudiante){

        egresadoServicio.ingresarEstudiante(estudiante);
        return "agregarEgresado";
    }
    
}
