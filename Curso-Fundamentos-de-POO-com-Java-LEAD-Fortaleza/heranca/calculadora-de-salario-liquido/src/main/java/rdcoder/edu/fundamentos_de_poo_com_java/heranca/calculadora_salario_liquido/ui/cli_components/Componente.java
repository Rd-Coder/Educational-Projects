package 
    rdcoder.edu.fundamentos_de_poo_com_java.heranca
    .calculadora_salario_liquido.ui.cli_components;

import java.io.IOException;

/** 
 * Representa de forma genérica qualquer tipo de componente voltado
 * ao terminal
 */
public interface Componente<T> {

    /** 
     * Renderiza este componente no terminal e o retorna ao final
     * @throws  IOException se algum erro de fluxo de E/S ocorrer
     */
    T render() throws IOException;
}
