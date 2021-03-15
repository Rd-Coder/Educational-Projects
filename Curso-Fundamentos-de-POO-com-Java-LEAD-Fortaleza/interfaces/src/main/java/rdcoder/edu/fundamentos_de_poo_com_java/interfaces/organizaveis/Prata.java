package organizaveis;

/**
 * A classe {@code Prata} representa um metal do tipo prata.
 */
public class Prata implements Organizavel {

    /**
     * O método sobrescrito da interface {@code Organizavel} que
     * escreve na tela detalhes sobre este objeto. A constante
     * {@code metais} da superinterface também é chamada.
     */
    @Override
    public void escrever(){
        System.out.println("Sou a " + metais[2] + ", um objeto organizável");
    }

}