/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.io.Serializable;
import java.util.ArrayList;
/**
 *
 *
 */
public class UtilitariaReporte implements Serializable{

    /**
     *
     * @param termino
     * @param codigo
     * @param paralelo
     * @param juegos
     * @return
     * @throws Exception
     */
    public static ArrayList<Juego> generarReporte(String termino, String codigo, String paralelo, ArrayList<Juego> juegos) throws Exception{
        int nparalelo = Integer.valueOf(paralelo);
        //Crea un arrayList vacio que va a almacenar las los juegos que cumplan con los parametros
        ArrayList<Juego> juegosReporte = new ArrayList<>();
        //recorre la lista para validar uno por uno
        for(Juego j: juegos){
            Paralelo pJuego = j.getParalelo();
            Materia mJuego = pJuego.getMateria();
            Termino tJuego = pJuego.getTermino();
            String cmJuego = mJuego.getCodigo();
            //valida el codigo de la materia
            if(codigo.equals(cmJuego)){
                String[] cadena = termino.split("-");
                int aniojuego = Integer.parseInt(cadena[0]);
                int terminojuego = Integer.parseInt(cadena[1]);
                //Valida el termino deseado
                if ((aniojuego == tJuego.getAnio())&&(terminojuego == tJuego.getNumTermino())){
                    //valida el paralelo deseado
                    if(nparalelo == pJuego.getNumero()){
                        juegosReporte.add(j);
                    }
                }
            }
        }
        //Se presentan los resultados, si no hay reportes que coincidan con la busqueda se da un mensaje
        if(juegosReporte.size() == 0){
            System.out.println("<<NO EXISTEN REPORTES QUE COINCIDAN CON EL REGISTRO>>");
        }else{
            int cont = 1;
            //Se presentan los juegos del reporte en orden
            for(Juego jreporte: juegosReporte){
                System.out.println(cont+ ". " +jreporte);
                cont++;
            }
            System.out.println("");
        }
    return juegosReporte;
    }
}
