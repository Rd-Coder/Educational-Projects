package rdcoder.edu.fundamentos_de_poo_com_java.polimorfismo.formasgeometricas;

/** 
 * A classe abstrata {@code FormaGeometrica} representa genericamente
 * uma forma geométrica (figura tridimensional ou bidimensional 
 * no espaço) qualquer.
 */
public abstract class FormaGeometrica {

    //ATRIBUTOS

    /** 
     * A {@code String} que armazena o desenho, em formato de texto
     * de console, desta forma geométrica.
     */
    protected String body;

    /** O texto descritivo desta forma geométrica. */
    protected String descricao;

    
    //MÉTODOS
    /** 
     * Renderiza/desenha esta forma geométrica na tela.
     */
    public void renderForma() {
        System.out.println(this.body);
    };
}