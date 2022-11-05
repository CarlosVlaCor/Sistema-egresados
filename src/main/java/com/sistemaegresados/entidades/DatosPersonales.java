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
@Table(name = "datos_personales")
@Getter
@Setter
public class DatosPersonales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String aprllidoPaterno;
    private String apellidoMaterno;
    private String correo;
    private String celular;
    @OneToOne(cascade = {CascadeType.MERGE,CascadeType.REMOVE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_direccion", referencedColumnName = "id")
    private Direccion direccion;
    
    public DatosPersonales(){
        
    }
}
