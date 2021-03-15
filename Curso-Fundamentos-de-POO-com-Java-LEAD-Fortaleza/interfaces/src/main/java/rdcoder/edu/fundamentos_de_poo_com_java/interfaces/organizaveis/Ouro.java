package organizaveis;

/**
 * A classe {@code Ouro} representa um metal do tipo ouro.
 */
public class Ouro implements Organizavel {

    /**
     * O método sobrescrito da interface {@code Organizavel} que
     * escreve na tela detalhes sobre este objeto. A constante
     * {@code metais} da superinterface também é chamada.
     */
    @Override
    public void escrever(){
        System.out.println("Sou o " + metais[1] + ", um objeto organizável");
    }

}