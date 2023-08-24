package modelo;

/**
 *
 * @author mateo
 */
public class PreguntaRespondida extends Pregunta {
    private TipoComodin comodinUsado;
    public PreguntaRespondida(String e, int n, String t, String x, String y, String z){
        super(e, n, t, x, y, z);
        comodinUsado = TipoComodin.Ninguno;
    }
    public PreguntaRespondida(Pregunta p, TipoComodin tipo){
        super(p.getEnunciado(), p.getNivel(), p.getCorrecta(), p.getPosible1(), p.getPosible2(), p.getPosible3());
        comodinUsado = tipo;
    }
}
