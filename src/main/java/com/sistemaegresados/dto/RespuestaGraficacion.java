package com.sistemaegresados.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RespuestaGraficacion {
    
    private List<List<Object>> infoCarreras;
    private List<List<Object>> periodoEgreso;
    private List<List<Object>> egresadosTitulos;
    private List<List<Object>> egresadosTrabajo;
    private List<List<Object>> egresadosTrabjoReferenteACarrera;
    private List<List<Object>> tiemposParaEncontrarUnEmpleo;
    private List<List<Object>> egresadosPostgrados;

}
