package com.sistemaegresados;

import com.sistemaegresados.entidades.Carrera;
import com.sistemaegresados.entidades.DatosEscolares;
import com.sistemaegresados.entidades.DatosPersonales;
import com.sistemaegresados.entidades.Direccion;
import com.sistemaegresados.entidades.Estudiante;
import com.sistemaegresados.entidades.Usuario;
import com.sistemaegresados.repositorio.UsuarioRepositorio;
import com.sistemaegresados.servicio.EgresadoServicio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class SistemaEgresadosApplicationTests {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    EgresadoServicio egresadoServicio;
    @Autowired
    UsuarioRepositorio usuarioRepositorio;
	@Test
	void contextLoads() {
            System.out.println(passwordEncoder.encode("12345"));
	}

}
