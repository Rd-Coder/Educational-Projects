package formasgeometricas;

/** 
 * A classe {@code Triangulo} subclasse de {@code FormaGeometrica},
 * representa um triângulo, um tipo de forma geométrica.
 */
public class Triangulo extends FormaGeometrica {

    //CONSTRUTORES
    public Triangulo()
    {
        this.descricao = "triângulo";
        this.body = 
            "   *\n" + 
            " *   *\n" + 
            "*******\n";
    }


    //MÉTODOS

    /** 
     * Renderiza/desenha este triângulo na tela e a sua
     * descrição.
     */
    @Override
    public void renderForma()
    {
        // printa a descrição
        System.out.println(
            this.descricao.toUpperCase() + "\n"
        );

        // desenha a forma
        super.renderForma();
    }
}