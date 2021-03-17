package rdcoder.edu.fundamentos_de_poo_com_java.heranca.calculadora_salario_liquido.salarios;

/**
 * A classe {@code SalarioBruto} representa de forma concreta um
 * salário bruto qualquer, ou seja, o salário sem adição de bônus.
 */
public class SalarioBruto extends Salario {
    
    //-> CONSTRUTORES
    /** 
     * Constrói uma nova instância de {@code SalarioBruto}
     * com valor equivalente a R$ 0,00.
     */
    public SalarioBruto() {
        super(0f);
    }

    /**
     * Constrói uma nova instância de {@code SalarioBruto}
     * com valor equivalente ao passado como argumento.
     * 
     * @param   valor
     *          o valor bruto do salário, sem bônus
     * @throws  NullPointerException
     *          se o {@code valor} passado for nulo
     * @throws  NumberFormatException
     *          se o {@code valor} passado for negativo
     */
    public SalarioBruto(float valor) { 
        super(requireValidValue(
            valor, 
            "O parâmetro 'valor' não pode ser nulo", 
            "O parâmetro 'valor' não pode ser negativo"
        ));
    }


}
