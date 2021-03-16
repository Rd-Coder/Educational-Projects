package rdcoder.edu.fundamentos_de_poo_com_java.heranca
        .calculadora_salario_liquido.ui;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Objects;

import  rdcoder.edu.fundamentos_de_poo_com_java.heranca
        .calculadora_salario_liquido.salarios.*;

/**
 * Modela a interface de interação do usuário deste programa
 * através do terminal.
 */
public class CLIBuilder implements Runnable {

    //-> ATRIBUTOS

    /** 
     * O número máximo de tentativas para executar um comando via
     * subprocesso
     */
    private static int MAX_TENTATIVAS_EXEC = 5;

    /**
     * O ProcessBuilder responsável por lidar com operações de I/O
     * em {@link ProcessBuilder#inheritIO()}
     */
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
            VarsBonificacaoBean entradaDoUsuario = 
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
    public VarsBonificacaoBean janSolicitaValores() {
        String nome, salarioBruto, bonus;
        TextBox txtBox = new TextBox();

        comporCabecalho();
        System.out.println(
            "-> Insira as informações requeridas para o cálculo:\n");

        // solicita os dados
        nome = txtBox.label("> Nome do Funcionário:").render();
        salarioBruto = txtBox.label("> Salário Bruto: R$").render();
        bonus = txtBox.label("> Bônus: R$").render();
        txtBox.close();

        return new VarsBonificacaoBean(
            nome, 
            new SalarioBruto(Float.parseFloat(salarioBruto)),
            Float.parseFloat(bonus) 
        );
    }

    /** Exibe o salário final resultante do cálculo */
    public void janExibeResultado(
    final Salario resultado, final VarsBonificacaoBean variaveis){
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

    /** 
     * Limpa o terminal, mas se falho, imprime uma quebra de sessão.
     * 
     * @implNote esta ação é bloqueante, ou seja, pausa a thread 
     *           até que esteja concluída.
     */
    public void limparCLI() {
        String[] cmds =
        (System.getProperty("os.name").contains("Windows"))
            ? new String[]{"cmd", "/c", "cls"}
            : new String[]{"clear"};
        try { 
            execute(MAX_TENTATIVAS_EXEC, cmds); 
        } catch (IOException e) {
            System.out.println(
                "\n//////////////////////////////////////////////////////\n" 
            );
        }
    }

    /**
     * Executa comandos via {@link ProcessBuilder#inheritIO()} e,
     * se falho, tenta novamente até um limite de {@code maxTentativas}.
     * 
     * @throws  IOException 
     *          se excedido o número máximo de tentativas
     * @implNote esta ação é bloqueante, ou seja, pausa a thread 
     *           até que esteja concluída.
     */
    private synchronized void execute(int maxTentativas,
    String... cmds) throws IOException {
        int tentRestantes = maxTentativas;
        while (--tentRestantes > 0) {
            try {
                execute(cmds);
                break;
            } catch (IOException io) { continue; }
        }
    }

    /**
     * Executa comandos via {@link ProcessBuilder#inheritIO()}
     * 
     * @throws  IOException 
     *          se um erro de E/S ocorrer durante a execução
     * @implNote esta ação é bloqueante, ou seja, pausa a thread 
     *           até que esteja concluída.
     */
    private synchronized void execute(String... cmds) 
    throws IOException {
        if (Objects.isNull(commandListener))
            commandListener = new ProcessBuilder(cmds).inheritIO();
        try {
            commandListener.start().waitFor(); // bloqueante
        } catch (InterruptedException interrupted) {}
    }

    
    //—> BEANS

    /** Registro que recolhe as variáveis necessárias para o cálculo */
    private record VarsBonificacaoBean(
        String nomeDoFuncionario, Salario salarioBase, float bonus
    ) {}


    //—> UTILIDADES ESTÁTICAS

    /** 
     * Componente que exibe somente o título do programa mais uma
     * linha em branco embaixo.
     */
    public static StringBuilder comporCabecalho(String titulo) {
        return comporCabecalho(titulo, null);
    }

    /** 
     * Componente que modela o título do programa seguido de um
     * subtítulo personalizado, mais uma linha em branco embaixo.
     * Porém, se {@code subtitulo} for {@code null}, o subtítulo
     * não é incluído no cabeçalho. Vide formato:
     * 
     * <blockquote>
     * ——> [titulo] {Subtítulo} <——
     * </blockquote>
     * <blockquote>
     *  *{Subtítulo} = "— [subtitulo]"
     * </blockquote>
     * 
     * @param   subtitulo 
     *          o subtítulo personalizado, podendo ser
     *          {@code null} para não adicionar subtítulo
     * @return  um {@code StringBuilder} com o cabeçalho modelado
     */
    public static StringBuilder comporCabecalho(String titulo, String subtitulo) {
        StringBuilder cabecalho = new StringBuilder();
        cabecalho.append("——> " + titulo);
        if (Objects.nonNull(subtitulo))
            cabecalho.append(" — " + subtitulo);
        cabecalho.append(" <——");
        return cabecalho;
    }


}