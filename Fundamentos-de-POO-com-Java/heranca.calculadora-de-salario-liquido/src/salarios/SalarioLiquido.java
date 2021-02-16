package salarios;

/**
 * A classe {@code SalarioFinal} representa o salário líquido, ou seja, a soma
 * do salário bruto com o bônus adquirido.
 */
public class SalarioLiquido extends SalarioBruto {

    //ATRUIBUTOS
    /** O bônus ganhado. */
    private float bonus;


    //CONSTRUTORES
    /** 
     * Constrói uma nova instância desta classe com base no
     * no salário bruto e no bônus passados como argumento.
     * 
     * @param   salarioBruto
     *          o valor bruto do salário
     * @param   bonus
     *          o valor do bônus recebido
     */
    public SalarioLiquido (float salarioBruto, float bonus)
    {
        super(salarioBruto);
        this.bonus = bonus;
    }

    /**
     * Calcula e retorna o valor do salário final líquido, somando o
     * salário bruto com o bônus recebido.
     * 
     * @return  um {@code float} contendo o salário final calculado
     */
    public float calcularSalarioFinal(){ 
        return super.getValor() + bonus; 
    }
 }