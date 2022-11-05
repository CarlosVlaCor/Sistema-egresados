package com.sistemaegresados;

import com.sistemaegresados.entidades.Carrera;
import com.sistemaegresados.entidades.DatosEscolares;
import com.sistemaegresados.entidades.DatosPersonales;
import com.sistemaegresados.entidades.Direccion;
import com.sistemaegresados.entidades.Estudiante;
import com.sistemaegresados.entidades.Usuario;
import com.sistemaegresados.servicio.EgresadoServicio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SistemaEgresadosApplicationTests {
    
    @Autowired
    EgresadoServicio egresadoServicio;
	@Test
	void contextLoads() {
            Estudiante estudiante = new Estudiante();
            Usuario usuario = new Usuario();
            usuario.setNroControl("19440380");
            usuario.setPassword("12345");
            estudiante.setUsuario(usuario);
            DatosPersonales datosPersonales = new DatosPersonales();
            datosPersonales.setNombre("carlos");
            datosPersonales.setAprllidoPaterno("Valenzuela");
            datosPersonales.setApellidoMaterno("Corrales");
            datosPersonales.setCorreo("carlos@gmail.com");
            datosPersonales.setCelular("66812132");
            
            Direccion direccion = new Direccion();
            direccion.setCalle("GAASDSDA");
            direccion.setCiudad("DSAsa");
            direccion.setEstado("SADDSADSA");
            direccion.setColonia("SADDSA");
            direccion.setCodigoPostal("dsadasdsa");
            datosPersonales.setDireccion(direccion);
            
            DatosEscolares datosEscolares = new DatosEscolares();
            datosEscolares.setAnioEgreso(2022);
            datosEscolares.setAnioIngreso(2019);
            datosEscolares.setPromedioFinal(71);
            Carrera carrera = new Carrera();
            carrera.setEspecialidad("móviles");
            carrera.setNombreCarrera("Ing. informatica");
            datosEscolares.setCarrera(carrera);
            estudiante.setDatosEscolares(datosEscolares);
                    
            estudiante.setDatosPersonales(datosPersonales);
           
            System.out.println(egresadoServicio.ingresarEstudiante(estudiante));
	}

}
