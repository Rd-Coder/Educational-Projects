package 
    rdcoder.edu.fundamentos_de_poo_com_java.heranca
    .calculadora_salario_liquido.ui.cli_components;

/** 
 * Representa de forma genérica qualquer tipo de componente voltado
 * ao terminal
 */
public interface Componente<T> {

    /** Renderiza este componente no terminal e o retorna */
    T render();
}
