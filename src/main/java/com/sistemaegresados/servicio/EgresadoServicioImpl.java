package com.sistemaegresados.servicio;

import com.sistemaegresados.entidades.Carrera;
import com.sistemaegresados.entidades.DatosEscolares;
import com.sistemaegresados.entidades.DatosLaborales;
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
public class EgresadoServicioImpl implements EgresadoServicio {

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
    @Autowired
    private DatosLaboralesRepositorio datosLaboralesRepositorio;

    @Override
    public String ingresarEstudiante(Estudiante estudiante) {
        Usuario usuario = usuarioRepositorio.findByNroControl(estudiante.getUsuario().getNroControl()).orElse(null);
        if (usuario != null) {
            return "El usuario ya existe";
        }
        Rol rol = rolRepositorio.findById(2L).orElse(null);
        if (rol == null) {
            return "Error rol";
        }

        Carrera carrera = carreraRepositorio.findByEspecialidad(estudiante.getDatosEscolares().getCarrera().getEspecialidad()).orElse(null);

        if (carrera == null) {
            return "Error carrera";
        }
        estudiante.getUsuario().setPassword(passwordEncoder.encode(estudiante.getUsuario().getPassword()));
        estudiante.getUsuario().setRol(rol);
        estudiante.getDatosEscolares().setCarrera(carrera);
        DatosEscolares datosEscolares = datosEscolaresRepositorio.save(estudiante.getDatosEscolares());
        Direccion direccion = direccionRepositorio.save(estudiante.getDatosPersonales().getDireccion());
        estudiante.getDatosPersonales().setDireccion(direccion);
        DatosPersonales datosPersonales = datosPersonalesRepositorio.save(estudiante.getDatosPersonales());
        Usuario usuarioTomado = usuarioRepositorio.save(estudiante.getUsuario());
        DatosLaborales datosLaborales;
        if (estudiante.getDatosLaborales() == null) {
            datosLaborales = datosLaboralesRepositorio.save(new DatosLaborales());

        } else {
            datosLaborales = datosLaboralesRepositorio.save(estudiante.getDatosLaborales());
        }
        estudiante.setDatosLaborales(datosLaborales);
        estudiante.setDatosEscolares(datosEscolares);
        estudiante.setDatosPersonales(datosPersonales);
        estudiante.setUsuario(usuarioTomado);
        estudianteRepositorio.save(estudiante);

        return "exito";
    }

    @Override
    public Estudiante buscarEstudiante(String nroControl) {
        Usuario usuario = usuarioRepositorio.findByNroControl(nroControl).orElse(null);

        return usuario.getEstudiantes();

    }

    @Override
    public Estudiante modificarEstudiante(Estudiante estudiante, String nroControl) {
        Usuario usuario = usuarioRepositorio.findByNroControl(nroControl).orElse(null);

        Estudiante estudianteObtenido = estudianteRepositorio.findById(usuario.getEstudiantes().getId()).orElse(null);
        DatosPersonales datosPersonales = estudiante.getDatosPersonales();
        DatosPersonales datosPersonalesModi = estudianteObtenido.getDatosPersonales();
        datosPersonalesModi.setApellidoMaterno(datosPersonales.getApellidoMaterno());
        datosPersonalesModi.setApellidoPaterno(datosPersonales.getApellidoPaterno());
        datosPersonalesModi.setCelular(datosPersonales.getCelular());
        datosPersonalesModi.setCorreo(datosPersonales.getCorreo());
        datosPersonalesModi.setNombre(datosPersonales.getNombre());
        Direccion direccion = datosPersonales.getDireccion();
        Direccion direccionModi = datosPersonalesModi.getDireccion();
        direccionModi.setCalle(direccion.getCalle());
        direccionModi.setCiudad(direccion.getCiudad());
        direccionModi.setCodigoPostal(direccion.getCodigoPostal());
        direccionModi.setColonia(direccion.getColonia());
        direccionModi.setEstado(direccion.getEstado());

        DatosLaborales datosLaborales = estudiante.getDatosLaborales();
        DatosLaborales datosLaboralesModi = estudianteObtenido.getDatosLaborales();
        datosLaboralesModi.setAnioInicio(datosLaborales.getAnioInicio());
        datosLaboralesModi.setNombreEmpresa(datosLaborales.getNombreEmpresa());
        datosLaboralesModi.setPuesto(datosLaborales.getPuesto());

        DatosEscolares datosEscolares = estudiante.getDatosEscolares();
        DatosEscolares datosEscolaresModi = estudianteObtenido.getDatosEscolares();
        datosEscolaresModi.setAnioEgreso(datosEscolares.getAnioEgreso());
        datosEscolaresModi.setAnioIngreso(datosEscolares.getAnioIngreso());
        datosEscolaresModi.setPromedioFinal(datosEscolares.getPromedioFinal());
        Carrera carrera = datosEscolares.getCarrera();
        Carrera carreraModi = datosEscolaresModi.getCarrera();
        carreraModi.setDatosEscolareses(carrera.getDatosEscolareses());
        carreraModi.setEspecialidad(carrera.getEspecialidad());
        carreraModi.setNombreCarrera(carrera.getEspecialidad());

        Usuario usr = estudiante.getUsuario();
        Usuario usrModi = estudianteObtenido.getUsuario();
        usrModi.setCurp(usr.getCurp());
        usrModi.setNroControl(usr.getNroControl());
        usrModi.setPassword(usr.getPassword());

        estudianteRepositorio.save(estudianteObtenido);

        return estudianteObtenido;
    }

    @Override
    public void eliminarEgresado(String nroControl) {
        Usuario usuario = usuarioRepositorio.findByNroControl(nroControl).orElse(null);
        System.out.println(nroControl);
        Estudiante estudianteObtenido = estudianteRepositorio.findById(usuario.getEstudiantes().getId()).orElse(null);
        System.out.println(estudianteObtenido.getId());
        estudianteRepositorio.delete(estudianteObtenido);

    }
}
