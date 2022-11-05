package com.sistemaegresados.entidades;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "datos_escolares")
@Getter
@Setter
public class DatosEscolares {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int anioIngreso;
    private int anioEgreso;
    private double promedioFinal;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_carrera", referencedColumnName = "id")
    private Carrera carrera;
    @OneToOne(mappedBy = "datosEscolares", fetch = FetchType.LAZY)
    private Estudiante estudiante;
    
}
