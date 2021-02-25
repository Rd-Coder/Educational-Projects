package formasgeometricas;

/** 
 * A classe {@code Quadrado} subclasse de {@code FormaGeometrica},
 * representa um quadrado, um tipo de forma geométrica.
 */
public class Quadrado extends FormaGeometrica {

    public Quadrado()
    {
        this.descricao = "quadrado";
        this.body = 
            " =====\n" +
            "|     |\n" +
            "|     |\n" +
            " ===== ";
    }

    /** 
     * Renderiza/desenha este quadrado na tela e a sua descrição.
     */
    @Override
    public void renderForma()
    {
        // printa a descrição na tela
        System.out.println(
            this.descricao.toUpperCase() + "\n");
        
        // desenha a forma
        super.renderForma();
    }
}