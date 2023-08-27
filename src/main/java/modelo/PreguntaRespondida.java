package modelo;

/**
 *
 * @author mateo
 */
public class PreguntaRespondida extends Pregunta {
    private TipoComodin comodinUsado;

    /**
     *
     * @param e
     * @param n
     * @param t
     * @param x
     * @param y
     * @param z
     */
    public PreguntaRespondida(String e, int n, String t, String x, String y, String z){
        super(e, n, t, x, y, z);
        comodinUsado = TipoComodin.Ninguno;
    }

    /**
     *
     * @param p
     * @param tipo
     */
    public PreguntaRespondida(Pregunta p, TipoComodin tipo){
        super(p.getEnunciado(), p.getNivel(), p.getCorrecta(), p.getPosible1(), p.getPosible2(), p.getPosible3());
        comodinUsado = tipo;
    }

    /**
     *
     * @param p
     */
    public PreguntaRespondida(Pregunta p){
        super(p.getEnunciado(), p.getNivel(), p.getCorrecta(), p.getPosible1(), p.getPosible2(), p.getPosible3());
        comodinUsado = TipoComodin.Ninguno;
    }

    /**
     *
     * @return
     */
    public TipoComodin getComodinUsado() {
        return comodinUsado;
    }

    /**
     *
     * @param comodinUsado
     */
    public void setComodinUsado(TipoComodin comodinUsado) {
        this.comodinUsado = comodinUsado;
        System.out.println("Se uso comodin");
    }
    
}
