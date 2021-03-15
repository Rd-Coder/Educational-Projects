package rdcoder.edu.fundamentos_de_poo_com_java.interfaces;

import organizaveis.*;

/** 
 * A classe {@code Application} é a classe principal do programa,
 * responsável pela lógica de execução dele. Primeiramente, são
 * instanciados os objetos necessários — um vetor do tipo
 * {@code Organizavel} contendo intâncias de <i>Ouro</i>, <i>Prata</i>
 * e <i>Ferro</i>, que são subclasses da interface desse mesmo tipo.
 * Por fim, as informações sobre cada um deles são impressos na tela.
 */
public class Application {

    public static void main(String[] args) {

        // organiza os objetos em um vetor do tipo Organizavel
        Organizavel[] listaDeMetais = new Organizavel[3];
        listaDeMetais[0] = new Prata();
        listaDeMetais[1] = new Ouro();
        listaDeMetais[2] = new Ferro();

        // escreve na tela
        for (Organizavel metal : listaDeMetais) {
            metal.escrever();
        }
    }

}
