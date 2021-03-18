package rdcoder.edu.fundamentos_de_poo_com_java.heranca
        .calculadora_salario_liquido.ui;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import  rdcoder.edu.fundamentos_de_poo_com_java.heranca
        .calculadora_salario_liquido.salarios.*;
import rdcoder.edu.fundamentos_de_poo_com_java.heranca
        .calculadora_salario_liquido.ui.cli_components.TextBox;

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
    

    // —> RENDERIZAÇÃO E PROCESSAMENTO

    /** 
     * Executa este programa via terminal. A lógica é a seguinte:
     * 
     * <ol>
     *      <li>
     *          Executa {@link this#janSolicitaValores()} para
     *          capturar as variáveis de cálculo
     *      </li><li>
     *          Solicita o processamento das variáveis para calcular
     *          o salário bonificado;
     *      </li><li>
     *          Exibe esse resultado em tela e reinicia a calculadora.
     *      </li>
     * </ol>
     * 
     * @implNote    Este método executa internamente ações bloqueantes
     *              para limpar o terminal durante a troca de janelas.
     */
    @Override
    public void run() {
        definirEncoding(65001);
        while (true) {
            limparCLI();
            VarsBonificacaoBean entradaDoUsuario = 
                janSolicitaValores();
            Salario totalBonificado = new SalarioLiquido(
                entradaDoUsuario.salarioBase(), 
                entradaDoUsuario.bonus()
            );

            limparCLI();
            janExibeResultado(
                totalBonificado,
                entradaDoUsuario
            );
            sessaoIrParaNovoCalculo();
        }
    }

    /** 
     * Janela que exibe um formulário para solicitar os dados
     * para o cálculo da bonificação. 
     * 
     * @return  um registro do tipo {@code VarsBonificacaoBean}
     *          contendo as variáveis coletadas
     */
    public VarsBonificacaoBean janSolicitaValores() {
        final String[] labels = {
            "Nome do Funcionário:",
            "Salário Base: R$",
            "Bônus: R$"
        };
        final int 
            NOME = 0, SALARIO_BASE = 1, BONUS = 2,
            len = labels.length;
        final var valores = new String[len];
        var txtBox = new TextBox(TextBox.Layout.MULTILINHA);

        exibirCabecalho(comporCabecalho(ProgramInfo.TITUTO_PROGRAMA));
        System.out.println(
            "-> Insira as informações requeridas para o cálculo:\n" );
        for (int id = 0; id < len; id++) {
            while (true) {
                txtBox.label(labels[id]);
                try {
                    valores[id] = txtBox.render().valor();
                    break;
                } catch (IOException io) {
                    System.out.println(
                        "\n\t!!> Erro ao ler valor, tente novamente..."
                        + "\n\t> ");
                    txtBox.layout(TextBox.Layout.SOMENTE_CAIXA_DE_TEXTO);
                    continue;
                }
            }
        }
        return new VarsBonificacaoBean(
            valores[NOME],
            new SalarioBruto(Float.parseFloat(valores[SALARIO_BASE])),
            Float.parseFloat(valores[BONUS]) 
        );
    }

    /** Exibe o salário final resultante do cálculo */
    public void janExibeResultado(
    final Salario resultado, final VarsBonificacaoBean variaveis) {
        exibirCabecalho(comporCabecalho(
            ProgramInfo.TITUTO_PROGRAMA, "Salário Final Líquido") );
        System.out.printf(
            "\n=> Salário final para '%s:'\n\t[R$ %,.2f]\n",
            variaveis.nomeDoFuncionario(),
            resultado.getValor()
        );
    }

    /** 
     * Sessão que aguarda a confirmação do usuário para reiniciar 
     * o cálculo
     */
    public void sessaoIrParaNovoCalculo() {
        try {
            new TextBox(TextBox.Layout.EM_LINHA, 
            "\n> Enter para novo cálculo...").render();
        } catch (Exception e) { return; }
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

    public void definirEncoding(int chcp) {
        try { execute("chcp", Integer.toString(chcp)); }
        catch (IOException io) {}
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
        else
            commandListener.command(cmds);
        try {
            commandListener.start().waitFor(); // bloqueante
        } catch (InterruptedException interrupted) {}
    }

    
    //—> BEANS

    /** Bean que recolhe as variáveis necessárias para o cálculo */
    private final class VarsBonificacaoBean {
        
        private String nome;
        private Salario salarioBase;
        private float bonus;

        public VarsBonificacaoBean(String nomeDoFuncionario,
        Salario salarioBase, float bonus) {
            this.nome = nomeDoFuncionario;
            this.salarioBase = salarioBase;
            this.bonus = bonus;
        }

        public String nomeDoFuncionario() { return nome; }
        public Salario salarioBase() { return salarioBase; }
        public float bonus() { return bonus; }
    }


    //—> UTILIDADES ESTÁTICAS

    /** 
     * Componente que exibe somente o título do programa mais uma
     * linha em branco embaixo.
     */
    public static StringBuilder comporCabecalho(String titulo) {
        return comporCabecalho(titulo, null);
    }

    public static void exibirCabecalho(StringBuilder cabecalho) {
        System.out.println(cabecalho.toString());
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
        cabecalho.append("==> " + titulo.toUpperCase());
        if (Objects.nonNull(subtitulo))
            cabecalho.append(" = " + subtitulo);
        cabecalho.append(" <==");
        return cabecalho;
    }


}