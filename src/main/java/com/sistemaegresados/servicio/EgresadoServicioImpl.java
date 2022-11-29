package com.sistemaegresados.servicio;

import com.sistemaegresados.dto.RespuestaGraficacion;
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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
            System.out.println("    sadasddsa");
            return "El usuario ya existe";
        }
        Rol rol = rolRepositorio.findById(2L).orElse(null);
        if (rol == null) {
            System.out.println("    sadasddsa");
            return "Error rol";
        }
        System.out.println(estudiante.getDatosEscolares().getCarrera().getId());
        Carrera carrera = carreraRepositorio.findById(estudiante.getDatosEscolares().getCarrera().getId()).orElse(null);

        if (carrera == null) {
                System.out.println("    sadasddsa");
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
        datosLaborales = datosLaboralesRepositorio.save(estudiante.getDatosLaborales());
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
        Carrera carrera = carreraRepositorio.findById(estudiante.getDatosEscolares().getCarrera().getId()).orElse(null);
        Estudiante estudianteObtenido = usuario.getEstudiantes();
        //Modificar DATOS PERSONALES
        DatosPersonales datosPersonales = estudiante.getDatosPersonales();
        DatosPersonales datosPersonalesModi = estudianteObtenido.getDatosPersonales();
        datosPersonalesModi.setApellidoMaterno(datosPersonales.getApellidoMaterno());
        datosPersonalesModi.setApellidoPaterno(datosPersonales.getApellidoPaterno());
        datosPersonalesModi.setCelular(datosPersonales.getCelular());
        datosPersonalesModi.setCorreo(datosPersonales.getCorreo());
        datosPersonalesModi.setNombre(datosPersonales.getNombre());
        //Modificar DIRECCION
        Direccion direccion = datosPersonales.getDireccion();
        Direccion direccionModi = datosPersonalesModi.getDireccion();
        direccionModi.setCalle(direccion.getCalle());
        direccionModi.setCiudad(direccion.getCiudad());
        direccionModi.setCodigoPostal(direccion.getCodigoPostal());
        direccionModi.setColonia(direccion.getColonia());
        direccionModi.setEstado(direccion.getEstado());
        //Modificar DATOS LABORALES
        DatosLaborales datosLaborales = estudiante.getDatosLaborales();
        DatosLaborales datosLaboralesModi = estudianteObtenido.getDatosLaborales();
        datosLaboralesModi.setAnioInicio(datosLaborales.getAnioInicio());
        datosLaboralesModi.setNombreEmpresa(datosLaborales.getNombreEmpresa());
        datosLaboralesModi.setPuesto(datosLaborales.getPuesto());
        //Modificar DATOS ESCOLARES
        DatosEscolares datosEscolares = estudiante.getDatosEscolares();
        DatosEscolares datosEscolaresModi = estudianteObtenido.getDatosEscolares();
        datosEscolaresModi.setAnioEgreso(datosEscolares.getAnioEgreso());
        datosEscolaresModi.setAnioIngreso(datosEscolares.getAnioIngreso());
        datosEscolaresModi.setPromedioFinal(datosEscolares.getPromedioFinal());
        datosEscolaresModi.setCarrera(carrera);

        //Modificar usuario
        Usuario usr = estudiante.getUsuario();
        Usuario usrModi = estudianteObtenido.getUsuario();
        usrModi.setCurp(usr.getCurp());
        usrModi.setNroControl(usr.getNroControl());

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

    @Override
    public RespuestaGraficacion datosGraficacion() {
        List<Carrera> carreras = carreraRepositorio.findAllByNombreCarrera("Ingeniería informática");

        RespuestaGraficacion respuestaGraficacion = new RespuestaGraficacion();
        respuestaGraficacion.setInfoCarreras(nroEgresadosEnEspecialidades(carreras));
        respuestaGraficacion.setPeriodoEgreso(periodoTiempoParaEgresar(carreras));
        respuestaGraficacion.setEgresadosTitulos(egresadosConTitulo(carreras));
        respuestaGraficacion.setEgresadosTrabajo(cuentaConTrabajo(carreras));
        respuestaGraficacion.setEgresadosTrabjoReferenteACarrera(trabajoReferenteACarrera(carreras));
        respuestaGraficacion.setTiemposParaEncontrarUnEmpleo(tiempoParaEncontrarUnEmpleo(carreras));
        respuestaGraficacion.setEgresadosPostgrados(egresadosPostGrados(carreras));
        return respuestaGraficacion;
    }

    private List<List<Object>> nroEgresadosEnEspecialidades(List<Carrera> carreras) {
        List<List<Object>> datosCarreras = new ArrayList<>();
        for (Carrera carrera : carreras) {
            datosCarreras.add(List.of(carrera.getEspecialidad(), carrera.getDatosEscolareses().size()));
        }
        return datosCarreras;
    }

    private List<List<Object>> periodoTiempoParaEgresar(List<Carrera> carreras) {
        List<DatosEscolares> datos = new ArrayList<>();
        carreras.forEach(carrera -> datos.addAll(carrera.getDatosEscolareses()));
        long nueveOMenos = datos.stream().filter(dato -> (dato.getAnioEgreso() - dato.getAnioIngreso()) <= 4).count();
        long nueveOMas = datos.stream().filter(dato -> (dato.getAnioEgreso() - dato.getAnioIngreso()) > 4).count();
        List<List<Object>> periodoEgreso = new ArrayList<>();
        periodoEgreso.add(List.of("9 semestres o menos", nueveOMenos));
        periodoEgreso.add(List.of("Más de 9 semestres", nueveOMas));
        return periodoEgreso;
    }

    private List<List<Object>> egresadosConTitulo(List<Carrera> carreras) {
        List<DatosEscolares> datos = new ArrayList<>();
        carreras.forEach(carrera -> datos.addAll(carrera.getDatosEscolareses()));
        long tieneTitulo = datos.stream().filter(dato -> dato.getTitulo() == 1).count();
        long noTieneTitulo = datos.stream().filter(dato -> dato.getTitulo() == 2).count();
        List<List<Object>> egresadosTitulos = new ArrayList<>();
        if (tieneTitulo == 0 && noTieneTitulo == 0) {
            return null;
        }

        egresadosTitulos.add(List.of("Cuentan con título universitario", tieneTitulo));
        egresadosTitulos.add(List.of("No cuentan con título universitario", noTieneTitulo));
        return egresadosTitulos;

    }

    private List<List<Object>> cuentaConTrabajo(List<Carrera> carreras) {
        List<DatosEscolares> datos = new ArrayList<>();
        carreras.forEach(carrera -> datos.addAll(carrera.getDatosEscolareses()));
        long tieneTrabajo = datos.stream().filter(dato -> dato.getEstudiante().getDatosLaborales().getTrabajo()== 1).count();
        long noTieneTrabajo = datos.stream().filter(dato -> dato.getEstudiante().getDatosLaborales().getTrabajo()== 2).count();
        List<List<Object>> egresadosTitulos = new ArrayList<>();
        if (tieneTrabajo == 0 && noTieneTrabajo == 0) {
            return null;
        }

        egresadosTitulos.add(List.of("Trabaja actualmente", tieneTrabajo));
        egresadosTitulos.add(List.of("No trabaja actualmente", noTieneTrabajo));
        return egresadosTitulos;
    }
    
    private List<List<Object>> trabajoReferenteACarrera(List<Carrera> carreras){
       List<DatosEscolares> datos = new ArrayList<>();
        carreras.forEach(carrera -> datos.addAll(carrera.getDatosEscolareses()));
        long tieneTrabajoReferente = datos.stream().filter(dato -> dato.getEstudiante().getDatosLaborales().getTrabajoReferenteACarrera()== 1).count();
        long noTieneTrabajoReferente = datos.stream().filter(dato -> dato.getEstudiante().getDatosLaborales().getTrabajoReferenteACarrera()== 2).count();
        List<List<Object>> egresadosTrabajoReferenteACarrera = new ArrayList<>();
        if (tieneTrabajoReferente == 0 && noTieneTrabajoReferente == 0) {
            return null;
        }

        egresadosTrabajoReferenteACarrera.add(List.of("Cuenta con un trabajo referente a la carrera", tieneTrabajoReferente));
        egresadosTrabajoReferenteACarrera.add(List.of("No cuenta con un trabajo referente a la carrera", noTieneTrabajoReferente));
        return egresadosTrabajoReferenteACarrera;
    }
    
    private List<List<Object>> tiempoParaEncontrarUnEmpleo(List<Carrera> carreras){
       List<DatosEscolares> datos = new ArrayList<>();
        carreras.forEach(carrera -> datos.addAll(carrera.getDatosEscolareses()));
        long unAnioOMenos = datos.stream().filter(dato -> dato.getEstudiante().getDatosLaborales().getTiempoParaEncontrarUnEmpleo()== 1).count();
        long unoODosAnios = datos.stream().filter(dato -> dato.getEstudiante().getDatosLaborales().getTiempoParaEncontrarUnEmpleo()== 2).count();
        long tresAniosOMas = datos.stream().filter(dato -> dato.getEstudiante().getDatosLaborales().getTiempoParaEncontrarUnEmpleo()== 3).count();
        long sinEmpleo = datos.stream().filter(dato -> dato.getEstudiante().getDatosLaborales().getTiempoParaEncontrarUnEmpleo()== 4).count();
        long noInteresado = datos.stream().filter(dato -> dato.getEstudiante().getDatosLaborales().getTiempoParaEncontrarUnEmpleo()== 5).count();
        List<List<Object>> tiemposParaEncontrarUnEmpleo = new ArrayList<>();
        if (unAnioOMenos == 0 && unoODosAnios == 0 && tresAniosOMas == 0 && sinEmpleo == 0&& noInteresado==0) {
            return null;
        }

        tiemposParaEncontrarUnEmpleo.add(List.of("Consiguieron un empleo en menos de 1 año", unAnioOMenos));
        tiemposParaEncontrarUnEmpleo.add(List.of("Consiguieron un empleo entre 1 año o 2", unoODosAnios));
        tiemposParaEncontrarUnEmpleo.add(List.of("Consiguieron un empleo en 3 años o más", tresAniosOMas));
        tiemposParaEncontrarUnEmpleo.add(List.of("No han conseguido", tresAniosOMas));
        tiemposParaEncontrarUnEmpleo.add(List.of("No están interesados en un empleo relacionado", tresAniosOMas));
        return tiemposParaEncontrarUnEmpleo; 
    }
    
    private List<List<Object>> egresadosPostGrados(List<Carrera> carreras){
        List<DatosEscolares> datos = new ArrayList<>();
        carreras.forEach(carrera -> datos.addAll(carrera.getDatosEscolareses()));
        long tienenPostGrado = datos.stream().filter(dato -> dato.getPostGrado()== 1).count();
        long noTienenPostGrado = datos.stream().filter(dato -> dato.getPostGrado()== 2).count();
        List<List<Object>> egresadosPostGrados = new ArrayList<>();
        if (tienenPostGrado == 0 && noTienenPostGrado == 0) {
            return null;
        }

        egresadosPostGrados.add(List.of("Cuentan con un postgrado", tienenPostGrado));
        egresadosPostGrados.add(List.of("No cuenta con un postgrado", noTienenPostGrado));
        return egresadosPostGrados;
    }

}
