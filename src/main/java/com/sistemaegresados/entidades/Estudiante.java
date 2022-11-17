package com.sistemaegresados.entidades;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "estudiantes")
@Getter
@Setter
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private Usuario usuario;
    @OneToOne(cascade = {CascadeType.MERGE,CascadeType.REMOVE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_datos_personales", referencedColumnName = "id")
    private DatosPersonales datosPersonales;
    @OneToOne(cascade = {CascadeType.MERGE,CascadeType.REMOVE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_datos_escolares", referencedColumnName = "id")
    private DatosEscolares datosEscolares;
    @OneToOne(cascade = {CascadeType.MERGE,CascadeType.REMOVE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_datos_laborales", referencedColumnName = "id")
    private DatosLaborales datosLaborales;
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_comentario", referencedColumnName = "id")
    private Comentario comentario;
    
    public Estudiante(){
        
    }
}
