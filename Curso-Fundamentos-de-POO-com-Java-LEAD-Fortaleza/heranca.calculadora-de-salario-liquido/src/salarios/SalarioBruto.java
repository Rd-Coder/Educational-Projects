package salarios;

/**
 * A classe {@code SalarioBruto} representa um salário bruto qualquer, ou seja,
 * o salário sem adição de bônus.
 */
public class SalarioBruto {
    
    //ATRIBUTOS
    /** O valor bruto do salário. */
    private float valor;

    
    //CONSTRUTORES
    /** 
     * Constrói uma nova instância de {@code Salario}
     * com valor equivalente a R$ 0,00.
     */
    public SalarioBruto() {
        this.valor = 0;
    }

    /**
     * Constrói uma nova instância de {@code Salario}
     * com valor equivalente ao passado como argumento.
     * 
     * @param   valor
     *          o valor bruto do salário, sem bônus.
     */
    public SalarioBruto(float valor) { 
        this.valor = valor; 
    }

    //GETTERS E SETTERS
    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

}
