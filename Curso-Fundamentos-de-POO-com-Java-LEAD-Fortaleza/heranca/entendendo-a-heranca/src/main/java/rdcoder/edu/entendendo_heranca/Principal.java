
import modelos.*;

/** 
 * A classe {@code Principal} instancia as classes Superclasse
 * Subclasse e executa os seus métodos {@code run()} para fins
 * de teste.
 * 
 */
public class Principal {

    public static void main(String[] args) {

        /* Instanciado para testar a superclasse */
        Superclasse superclasse = new Superclasse();

        /* Instanciado para testar a subclasse */
        Subclasse subclasse = new Subclasse();

        /*
         * Instanciado para testar o conceito de referência, 
         * em que, no caso, uma variável do tipo Superclasse
         * referencia um objeto do tipo Subclasse.
        */
        Superclasse subclasse2 = new Subclasse();

        /* Teste do método run() da Superclasse */
        superclasse.run();

        /* Teste do método run() da Subclasse */
        subclasse.run();

        /* 
         * Teste do método run() sobrescrito da Superclasse
         * pela Subclasse 
        */
        subclasse2.run();

    }
    
}