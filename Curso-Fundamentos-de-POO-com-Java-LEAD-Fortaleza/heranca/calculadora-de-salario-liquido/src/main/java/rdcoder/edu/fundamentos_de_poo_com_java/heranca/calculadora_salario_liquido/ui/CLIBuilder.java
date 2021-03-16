package rdcoder.edu.fundamentos_de_poo_com_java.heranca.calculadora_salario_liquido.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

import  rdcoder.edu.fundamentos_de_poo_com_java.heranca
        .calculadora_salario_liquido.salarios.*;

/**
 * Modela a interface de interação do usuário deste programa
 * através do terminal.
 */
public class CLIBuilder implements Runnable {

    //-> ATRIBUTOS - ENTRADAS

    private ProcessBuilder commandListener;

    
    // -> RENDERIZAÇÃO E PROCESSAMENTO

    /** 
     * Rege sequência de exibição, solicitação e processamento de
     * informações através do termina. A lógica é a seguinte:
     * 
     * <ol>
     *      <li>
     *          Executa {@link this#janExibeResultado(...)} para
     *          capturar do usuário as variáveis de cálculo 
     *          requeridas;
     *      </li><li>
     *          Solicita o processamento das variáveis para calcular
     *          o salário bonificado;
     *      </li><li>
     *          Exibe esse resultado em tela, além de guiar o usuário
     *          para a realização de um novo cálculo.
     *      </li>
     * </ol>
     * 
     * @implNote    Este método executa internamente ações bloqueantes
     *              para limpar o terminal durante a troca de janelas.
     */
    @Override
    public void run() {
        while (true) {
            limparCLI();
            DadosDaBonificacaoBean entradaDoUsuario = 
                janSolicitaValores();
            Salario totalBonificado = 
                new SalarioLiquido(
                    entradaDoUsuario.salarioABonificar(), 
                    entradaDoUsuario.bonus() 
                );

            limparCLI();
            janExibeResultado(
                totalBonificado,
                entradaDoUsuario
            );

            limparCLI();
            sessaoIrParaNovoCalculo();
        }
    }

    /** 
     * Janela que exibe um formulário para solicitar os dados
     * salariais necessários para o cálculo da bonificação. 
     * 
     * @return  um registro do tipo {@code DadosDaBonificacaoBean}
     *          contendo as variáveis coletadas
     */
    public DadosDaBonificacaoBean janSolicitaValores() {
        String nome, salarioBruto, bonus;
        TextBox txtBox = new TextBox();

        compCabecalho();
        System.out.println(
            "-> Insira as informações requeridas para o cálculo:\n");

        // solicita os dados
        nome = txtBox.label("> Nome do Funcionário:").render();
        salarioBruto = txtBox.label("> Salário Bruto: R$").render();
        bonus = txtBox.label("> Bônus: R$").render();
        txtBox.close();

        return new DadosDaBonificacaoBean(
            nome, 
            new SalarioBruto(Float.parseFloat(salarioBruto)),
            Float.parseFloat(bonus) 
        );
    }

    /** Exibe o salário final resultante do cálculo */
    public void janExibeResultado(
    final Salario resultado, final DadosDaBonificacaoBean variaveis){
        compCabecalho("Salário Final Líquido");
        System.out.printf(
            "-> Salário final para '%s:'\n\tR$ %,.2f\n",
            variaveis.nomeDoFuncionario(),
            resultado.getValor()
        );
    }

    /** 
     * Sessão que aguarda a confirmação do usuário para prosseguir 
     * para reiniciar o cálculo.
     */
    public void sessaoIrParaNovoCalculo() {
        TextBox confirmaListener = new TextBox();
        confirmaListener.label("\n> Enter para novo cálculo...");
        confirmaListener.render();
        confirmaListener.close();
    }

    /** TODO: otimizar para reutilizar o subprocesso responsável por limpar o terminal
     * Limpa o terminal.
     * 
     * @implNote esta ação é bloqueante, ou seja, pausa a thread 
     *           até que esteja concluída.
     */
    public void limparCLI() {
        String[] comandos = 
            (System.getProperty("os.name").contains("Windows"))
                ? new String[]{"cmd", "/c", "cls"}
                : new String[]{"clear"};

        if (Objects.isNull(commandListener))
            commandListener = new ProcessBuilder(comandos).inheritIO();
        try {
            commandListener.start().waitFor(); // bloqueante
        } catch (IOException | InterruptedException ioOrInterrupted) {
            ioOrInterrupted.printStackTrace();
        }
    }


    //-> BEANS

    /** Registro que recolhe as variáveis necessárias para o cálculo */
    private record DadosDaBonificacaoBean(
        String nomeDoFuncionario, Salario salarioBase, float bonus
    ) {}


    //-> COMPONENTES EM MÉTODO

    /** 
     * Componente que exibe somente o título do programa mais uma
     * linha em branco embaixo.
     */
    public static void compCabecalho() {
        compCabecalho(null);
    }

    /** 
     * Componente que exibe o título do programa seguido de um
     * subtítulo personalizado, mais uma linha em branco embaixo.
     * Porém, se {@code subtitulo} for {@code null}, não inclui
     * um subtítulo ao cabeçalho. Vide formato:
     * 
     * <blockquote>
     * ——> [titulo] {Subtítulo} <——
     * </blockquote>
     * <blockquote>
     *  *{Subtítulo} = "— [subtitulo]"
     * </blockquote>
     * @param   subtitulo 
     *          o subtítulo personalizado, podendo ser
     *          {@code null} para não adicionar subtítulo
     */
    public static void compCabecalho(String titulo, String subtitulo) {
        StringBuilder cabecalho = new StringBuilder();
        cabecalho.append("——> ");
        cabecalho.append(titulo);
        if (Objects.nonNull(subtitulo)) {
            cabecalho.append(" — ");
            cabecalho.append(subtitulo);
        }
        cabecalho.append(" <——");
        System.out.println(cabecalho.toString());
    }


}