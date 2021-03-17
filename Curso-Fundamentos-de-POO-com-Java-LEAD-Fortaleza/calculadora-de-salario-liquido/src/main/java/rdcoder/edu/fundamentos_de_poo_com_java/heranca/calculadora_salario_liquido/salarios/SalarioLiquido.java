package rdcoder.edu.fundamentos_de_poo_com_java.heranca.calculadora_salario_liquido.salarios;

/**
 * A classe {@code SalarioLiquido} representa um salário líquido,
 * ou seja, o salário bruto acrescido de bônus.
 */
public class SalarioLiquido extends Salario {

    //-> ATRUIBUTOS
    /** O bônus ganhado acima deste salário. */
    private float bonus;


    //-> CONSTRUTORES

    /** 
     * Constrói uma nova instância desta classe com base no
     * no salário bruto e no bônus passados como argumento.
     * 
     * @param   salarioBruto
     *          o valor bruto do salário
     * @param   bonus
     *          o valor do bônus recebido
     * @throws  NullPointerException
     *          se {@code salarioBruto} ou {@code bonus}
     *          forem nulos
     * @throws  NumberFormatException
     *          se os argumentos forem números negativos
     */
    public SalarioLiquido(float salarioBruto, float bonus) {
        super(requireValidValue(
            salarioBruto + bonus,
            "Os argumentos não podem ser nulos",
            "Os argumentos não podem ser números negativos"
        ));

        this.bonus = bonus;
    }

    /** 
     * Constrói uma nova instância desta classe com base no
     * em um {@code Salario} qualquer e no bônus passados 
     * como argumento.
     * 
     * @param   salarioABonificar
     *          o {@code Salario} a ser bonificado
     * @param   bonus
     *          o valor {@code float} do bônus recebido
     * @throws  NullPointerException
     *          se {@code salarioBruto} ou {@code bonus}
     *          forem nulos
     * @throws  NumberFormatException
     *          se os argumentos forem números negativos
     */
    public SalarioLiquido(Salario salarioABonificar, float bonus) {
        this(salarioABonificar.getValor(), bonus);
    }


    //-> GETTERS

    /**
     * Retorna o valor bruto/original deste salário, ou seja, sem
     * acréscimo de bônus.
     * 
     * @return  um {@code float} contendo o salário bruto
     */
    public float getValorBruto(){ 
        return super.getValor()-bonus;
    }

    /**
     * Retorna o valor da bonificação sobre este salário líquido.
     */
    public float getBonus(){
        return bonus;
    }


    //-> SETTERS
    /**
     * Define o valor do bônus deste salário líquido.
     * 
     * @param   valor
     *          o novo bônus
     * @throws  NullPointerException
     *          se {@code valor} for nulo
     * @throws  NumberFormatException
     *          se o argumento for um número negativo
     */
    public void setBonus(float valor) {
        requireValidValue(valor, 
            "O parâmetro 'valor' não pode ser nulo",
            "O parâmetro 'valor' não pode ser negativo"
        );
    }

    
}