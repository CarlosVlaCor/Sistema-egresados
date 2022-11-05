package com.sistemaegresados.servicio;

import com.sistemaegresados.entidades.Carrera;
import com.sistemaegresados.entidades.DatosEscolares;
import com.sistemaegresados.entidades.DatosPersonales;
import com.sistemaegresados.entidades.Direccion;
import com.sistemaegresados.entidades.Estudiante;
import com.sistemaegresados.entidades.Rol;
import com.sistemaegresados.entidades.Usuario;
import com.sistemaegresados.repositorio.CarreraRepositorio;
import com.sistemaegresados.repositorio.DatosEscolaresRepositorio;
import com.sistemaegresados.repositorio.DatosLaboralesRepositorio;
import com.sistemaegresados.repositorio.DatosPersonalesRepositorio;
import com.sistemaegresados.repositorio.DireccionRepositorio;
import com.sistemaegresados.repositorio.EstudianteRepositorio;
import com.sistemaegresados.repositorio.RolRepositorio;
import com.sistemaegresados.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EgresadoServicioImpl implements EgresadoServicio{
    @Autowired
    private RolRepositorio rolRepositorio;
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private CarreraRepositorio carreraRepositorio;
    @Autowired
    private EstudianteRepositorio estudianteRepositorio;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private DatosEscolaresRepositorio datosEscolaresRepositorio;
    @Autowired
    private DatosPersonalesRepositorio datosPersonalesRepositorio;
    @Autowired
    private DireccionRepositorio direccionRepositorio;
    
    @Override
    public String ingresarEstudiante(Estudiante estudiante) {
        Usuario usuario = usuarioRepositorio.findByNroControl(estudiante.getUsuario().getNroControl());
        if(usuario != null){
            return "El usuario ya existe";
        }
        Rol rol = rolRepositorio.findById(2L).orElse(null);
        if(rol == null){
            return "Error rol";
        }
        
        Carrera carrera = carreraRepositorio.findByEspecialidad(estudiante.getDatosEscolares().getCarrera().getEspecialidad()).orElse(null);
        
        if(carrera == null){
            return "Error carrera";
        }
        estudiante.getUsuario().setPassword(passwordEncoder.encode(estudiante.getUsuario().getPassword()));
        estudiante.getUsuario().setRol(rol);
        estudiante.getDatosEscolares().setCarrera(carrera);
        DatosEscolares datosEscolares= datosEscolaresRepositorio.save(estudiante.getDatosEscolares());
        Direccion direccion =direccionRepositorio.save(estudiante.getDatosPersonales().getDireccion());
        estudiante.getDatosPersonales().setDireccion(direccion);
        DatosPersonales datosPersonales= datosPersonalesRepositorio.save(estudiante.getDatosPersonales());
        Usuario usuarioTomado = usuarioRepositorio.save(estudiante.getUsuario());
        
        estudiante.setDatosEscolares(datosEscolares);
        estudiante.setDatosPersonales(datosPersonales);
        estudiante.setUsuario(usuarioTomado);
        estudianteRepositorio.save(estudiante);
        
        
        return "exito";
    }
    
}
