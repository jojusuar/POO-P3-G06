/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;

/**
 *Enumera los Comodines.
 */
public enum TipoComodin implements Serializable{

    /**
     *Representa al Comodín 50/50.
     */
    Fifty_Fifty,

    /**
     *Representa al Comodín Consulta al Compañero.
     */
    ConsultaCompanero,

    /**
     *Representa al Comodín Consulta a la Clase.
     */
    ConsultaClase,

    /**
     *Representa el no-uso de comodines.
     */
    Ninguno;
    
    
}
