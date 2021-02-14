import java.util.Scanner;
import salarios.*;

/** 
 * A classe {@code Principal} tem como função executar a aplicação
 * de console "Cálculadora de Salário Líquido". Ela solicita ao
 * usuário as informações sobre o salário e chama as operações
 * necessárias para calcular e exibir na tela o salário final
 * líquido requerido.
 */
@SuppressWarnings("resource")
public class Principal {
    public static void main(String[] args) {
        
        //VARIAVEIS E OBJETOS
        Scanner in = new Scanner(System.in);
        SalarioLiquido salarioFinal;
        float memValor, memBonus;

        //CHAMADAS DE MÉTODOS
        
        while (true)
        {
            System.out.println("--> CÁLCULO DE SALÁRIO LÍQUIDO <--\n");

            // Solicitação de dados
            System.out.print("Salário Bruto: R$ ");
            memValor = in.nextFloat();

            System.out.print("Bônus: R$ ");
            memBonus = in.nextFloat();

            // Processamento e retorno
            salarioFinal = new SalarioLiquido(memValor, memBonus);
            System.out.println(
                String.format("\nSalário Líquido = R$ %,.2f \n", 
                salarioFinal.calcularSalarioFinal())
            );

            System.out.println();
        }
    }
}