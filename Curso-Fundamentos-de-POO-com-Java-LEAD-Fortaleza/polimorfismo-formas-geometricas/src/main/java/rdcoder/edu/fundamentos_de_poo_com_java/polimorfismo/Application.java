package rdcoder.edu.fundamentos_de_poo_com_java.polimorfismo;

import  rdcoder.edu.fundamentos_de_poo_com_java.polimorfismo
        .formasgeometricas.*;

/** 
 * A classe {@code Application} tem como função executar a aplicação
 * de console "Formas Geométricas". Ela renderiza objetos do tipo
 * {@code FormaGeometrica} de diferentes subtipos utlizando, para
 * isso, o paradigma do <strong>polimorfismo</strong>.
 */
public class Application {

    public static void main(String[] args) {
        FormaGeometrica[] formas = {
            new Quadrado(), new Triangulo()
        };
        for (var forma : formas) {
            forma.renderForma();
            System.out.println("\n");
        }
    }


}
