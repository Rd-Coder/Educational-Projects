package organizaveis;

/**
 * A classe {@code Ferro} representa um metal do tipo ferro.
 */
public class Ferro implements Organizavel {

/**
     * O método sobrescrito da interface {@code Organizavel} que
     * escreve na tela detalhes sobre este objeto. A constante
     * {@code metais} da superinterface também é chamada.
     */
    @Override
    public void escrever(){
        System.out.println("Sou o " + metais[0] + ", um objeto organizável");
    }

}