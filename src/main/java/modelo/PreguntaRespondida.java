package modelo;

/**
 *Representa una Pregunta respondida en el Juego, hereda de la clase Pregunta.
 */
public class PreguntaRespondida extends Pregunta {
    private TipoComodin comodinUsado;
    
    /**
     *Constructor sin Pregunta ni Comodín.
     * @param e Enunciado de la Pregunta.
     * @param n Nivel de la Pregunta.
     * @param t Respuesta correcta de la Pregunta.
     * @param x Respuesta falsa 1.
     * @param y Respuesta falsa 2.
     * @param z Respuesta falsa 3.
     */
    public PreguntaRespondida(String e, int n, String t, String x, String y, String z){
        super(e, n, t, x, y, z);
        comodinUsado = TipoComodin.Ninguno;
    }

    /**
     * Constructor con comodín.
     * @param p Objeto de tipo Pregunta.
     * @param tipo Tipo de Comodín.
     */
    public PreguntaRespondida(Pregunta p, TipoComodin tipo){
        super(p.getEnunciado(), p.getNivel(), p.getCorrecta(), p.getPosible1(), p.getPosible2(), p.getPosible3());
        comodinUsado = tipo;
    }

    /**
     *Constructor sin Comodín.
     * @param p Tipo de Comodín.
     */
    public PreguntaRespondida(Pregunta p){
        super(p.getEnunciado(), p.getNivel(), p.getCorrecta(), p.getPosible1(), p.getPosible2(), p.getPosible3());
        comodinUsado = TipoComodin.Ninguno;
    }

    /**
     * 
     * @return Devuelve el Comodín usado en la Pregunta respondida.
     */
    public TipoComodin getComodinUsado() {
        return comodinUsado;
    }

    /**
     *
     * @param comodinUsado Sobreescribe el Comodín usado en la Pregunta respondida.
     */
    public void setComodinUsado(TipoComodin comodinUsado) {
        this.comodinUsado = comodinUsado;
        System.out.println("Se uso comodin");
    }
    
}
