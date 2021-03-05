import ui.CLIBuilder;

/** 
 * A classe {@code Principal} tem como função executar a aplicação
 * de console "Cálculadora de Salário Líquido". Ela solicita ao
 * usuário as informações sobre o salário e chama as operações
 * necessárias para calcular e exibir na tela o salário final
 * líquido requerido.
 */
public class Principal {
    public static void main(String[] args) {
        Runnable cli = new CLIBuilder();
        cli.run();
    }
}