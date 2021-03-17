package rdcoder.edu.fundamentos_de_poo_com_java.polimorfismo;

import formasgeometricas.*;

/** 
 * A classe {@code Application} tem como função executar a aplicação
 * de console "Formas Geométricas". Ela instancia os objetos do tipo
 * {@code FormaGeometrica} necessários e chama os métodos que os
 * renderizam.
 */
public class Application {
    
    public static void main(String[] args) {
        
        // formas:
        Quadrado quadrado = new Quadrado();
        Triangulo triangulo = new Triangulo();

        // renderiza as formas:
        quadrado.renderForma();
        System.out.println("\n\n");
        triangulo.renderForma();
    
    }

}
