package com.sistemaegresados.controladores;

import com.sistemaegresados.entidades.Estudiante;
import com.sistemaegresados.repositorio.EstudianteRepositorio;
import com.sistemaegresados.servicio.EgresadoServicio;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/egresados")
public class ControladorEgresado {
    @Autowired
    private EgresadoServicio egresadoServicio;
    
    @GetMapping("/agregarEgresado")
    public String agregarEgresado(Estudiante estudiante){
        return "agregarEgresado";
    }
    
    @PostMapping("/agregarEgresado")
    public String agregar(Estudiante estudiante){
        egresadoServicio.ingresarEstudiante(estudiante);
        return "agregarEgresado";
    }
    
    @GetMapping("/datosEgresados")
    public String datosEgresados(@RequestParam(value = "nroControl",defaultValue = "") String nroControl,Model model){
        if(!nroControl.equals("")){
            Estudiante estudiante = egresadoServicio.buscarEstudiante(nroControl);
            System.out.println(estudiante.getDatosPersonales().getApellidoPaterno());
            model.addAttribute("Egresado", estudiante);
        }
        return "datosEgresados";
    }
    
    @PostMapping("/modificarEgresado/{nroControl}")
    public String modificarDatos(@PathVariable(name = "nroControl") String nroControl, Estudiante estudiante){
        Estudiante estudianteObtenido = egresadoServicio.modificarEstudiante(estudiante, nroControl);
        return "redirect:/egresados/datosEgresados?nroControl="+estudianteObtenido.getUsuario().getNroControl();
    }
    
    @GetMapping("/eliminarEgresado/{nroControl}")
    public String eliminarEgresado(@PathVariable(name = "nroControl") String nroControl){
        egresadoServicio.eliminarEgresado(nroControl);
        return "redirect:/egresados/datosEgresados";
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
    
    
    
    
}
