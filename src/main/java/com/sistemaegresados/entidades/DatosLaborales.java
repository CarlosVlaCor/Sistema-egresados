package com.sistemaegresados.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "datos_laborales")
@Getter
@Setter
public class DatosLaborales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombreEmpresa;
    private Integer anioInicio;
    private String puesto;
    private int trabajo;
    private int trabajoReferenteACarrera;
    private int tiempoParaEncontrarUnEmpleo;
    public DatosLaborales(){
        
    }
    
    
}
