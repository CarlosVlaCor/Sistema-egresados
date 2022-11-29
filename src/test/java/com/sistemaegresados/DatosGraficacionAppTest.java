/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sistemaegresados;

import com.sistemaegresados.dto.RespuestaComentario;
import com.sistemaegresados.entidades.Carrera;
import com.sistemaegresados.entidades.DatosEscolares;
import com.sistemaegresados.repositorio.CarreraRepositorio;
import com.sistemaegresados.repositorio.DatosEscolaresRepositorio;
import com.sistemaegresados.servicio.EgresadoServicio;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author carlosvla
 */
@SpringBootTest
public class DatosGraficacionAppTest {
    @Autowired
    private EgresadoServicio egresadoServicio;
    @Autowired
    private DatosEscolaresRepositorio datosEscolares;
    @Autowired
    private CarreraRepositorio carreraRepo;
    @Test
        void contextLoads() {
            List<Carrera> carreras = carreraRepo.findAllByNombreCarrera("Ingeniería informática");
            
            List<DatosEscolares> d = new ArrayList<>();
            carreras.forEach(carrera -> d.addAll(carrera.getDatosEscolareses()));
            System.out.println(d);
        }
}
