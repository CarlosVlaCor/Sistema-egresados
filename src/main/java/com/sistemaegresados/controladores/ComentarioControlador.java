package com.sistemaegresados.controladores;

import com.sistemaegresados.servicio.ComentarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/egresados")
public class ComentarioControlador {
    @Autowired
    private ComentarioServicio comentarioServicio;
     @GetMapping("/comentarios")
    public String mostrarFeedback(@RequestParam(name = "nroPagina", defaultValue = "0")int nroPagina, 
            @RequestParam(name = "carrera", defaultValue = "0")int idCarrera,
            @RequestParam(name = "orden", defaultValue = "ASC")String orden,Model model){
        
        model.addAttribute("datosPaginacion",comentarioServicio.obtenerComentarios(nroPagina, 1, orden));
        
        return "feedback";
    }
    
}
