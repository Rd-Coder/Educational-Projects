package rdcoder.edu.fundamentos_de_poo_com_java.heranca.calculadora_salario_liquido.salarios;

import java.util.Objects;

/** A classe {@code Salario} representa genericamente um salário. */
public abstract class Salario {

    //-> ATRIBUTOS
    /** O valor deste salário */
    private float valor;
    

    //-> CONSTRUTORES
    /** 
     * Construtor explícito que indica que o valor não pode 
     * ser deixado em branco. 
     * 
     * @throws  NullPointerException
     *          se o {@code valor} passado for nulo
     * @throws  NumberFormatException
     *          se o {@code valor} passado for negativo
     */
    public Salario(float valor) throws NullPointerException,
    NumberFormatException {
        setValor(valor);
    }


    //-> ACESSORES
    /**
     * Define o valor não negativo deste salário.
     * 
     * @param   valor
     *          o novo valor deste salário
     * @throws  NullPointerException
     *          se o {@code valor} passado for nulo
     * @throws  NumberFormatException
     *          se o {@code valor} passado for negativo
     */
    public void setValor(float valor) {
        requireValidValue(valor, 
            "O parâmetro 'valor' não pode ser nulo",
            "O parâmetro 'valor' não pode ser negativo"
        );
        
        this.valor = valor;
    }

    /** Retorna o valor total deste salário. */
    public float getValor() {
        return valor;
    }


    //-> UTILS
    /**
     * Semelhante a
     * {@link Salario#requireValidValue(float, String, String)},
     * mas sem mensagem personalizada.
     * 
     * @param   valor
     *          o valor a ser validado
     * @throws  NullPointerException
     *          se {@code valor} for nulo
     * @throws  NumberFormatException
     *          se o argumento for um número negativo
     */
    protected static void requireValidValue(float valor)
    throws NullPointerException, NumberFormatException {
        requireValidValue(valor, "", "");
    }

    /**
     * Analisa se {@code value} obedece à seguinte formatação,
     * caso contrário lança exceções:
     * <ol>
     *    <li>Não pode ser nulo;</li>
     *    <li>É um número decimal não negativo.</li> 
     * </ol>
     * 
     * Por fim, se válido, retorna o mesmo {@code valor} passado.
     * 
     * @param   valor
     *          o valor a ser validado;
     * @param   msgIfNull
     *          a mensagem personalizada da exceção 
     *          {@code NullPointerException};
     * @param   msgIfNegative
     *          a mensagem personalizada de
     *          {@code NumberFormatException}
     * @throws  NullPointerException
     *          se {@code valor} for nulo
     * @throws  NumberFormatException
     *          se o argumento for um número negativo
     * @return  {@code value} se válido
     */
    public static float requireValidValue(
        float valor, String msgIfNull, String msgIfNegative
    ){
        if (Objects.isNull(valor))
            throw new NullPointerException(msgIfNull);
        else if (valor < 0f)
            throw new IllegalArgumentException(msgIfNegative);

        return valor;
    }

    
}
