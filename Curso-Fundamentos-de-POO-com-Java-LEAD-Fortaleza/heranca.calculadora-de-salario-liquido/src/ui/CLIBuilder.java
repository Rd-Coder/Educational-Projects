package ui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import salarios.Salario;
import salarios.SalarioBruto;
import salarios.SalarioLiquido;

/**
 * Modela a interface de interação do usuário deste programa
 * através do terminal.
 */
public class CLIBuilder implements Runnable {

    //-> ATRIBUTOS - ENTRADAS
    private String nomeDoFuncionario;
    private String salarioBruto;
    private String bonus;
    private ProcessBuilder commandListener;


    //-> MÉTODOS ÚTEIS
    
    // -> RENDERIZAÇÃO E PROCESSAMENTO

    @Override
    public void run() {
        while (true) {
            limparCLI();
            janSolicitaValores();
            limparCLI();

            SalarioBruto sBruto = new SalarioBruto(Float.valueOf(salarioBruto));
            SalarioLiquido sLiquido = new SalarioLiquido(sBruto, Float.valueOf(bonus));

            janExibeResultado(sLiquido);

            CompTextBox novoCalculo = new CompTextBox();
            novoCalculo.comLabel("\n> Enter para novo cálculo...");
                
            novoCalculo.render();
            novoCalculo.close();
        }
    }

    /** Formulário que solicita as informações salariais do funcionário */
    public void janSolicitaValores() {
        String nome, salarioBruto, bonus;

        compCabecalho();
        System.out.println("-> Insira as informações requeridas para o cálculo:\n");

        CompTextBox txtBox = new CompTextBox();

        // solicita os dados
        nome = txtBox.comLabel("> Nome do Funcionário:").render();
        salarioBruto = txtBox.comLabel("> Salário Bruto: R$").render();
        bonus = txtBox.comLabel("> Bônus: R$").render();

        txtBox.close();
        this.nomeDoFuncionario = nome;
        this.salarioBruto = salarioBruto;
        this.bonus = bonus;

    }

    /** Exibe o salário final resultante do cálculo */
    public void janExibeResultado(final Salario resultado) {
        compCabecalho("Salário Final Líquido");

        System.out.printf(
            "-> Salário final para '%s:'\n\tR$ %,.2f\n",
            nomeDoFuncionario, resultado.getValor()
        );
    }

    /** Limpa o terminal, uma ação que pausa a thread até que esteja concluída */
    public void limparCLI() {
        String[] commands = 
            ( System.getProperty("os.name").contains("Windows") )
                ? new String[]{"cmd", "/c", "cls"}
                : new String[]{"clear"};

        if (commandListener == null)
            commandListener = new ProcessBuilder(commands).inheritIO();

        try {
            commandListener.start().waitFor();
        } catch (IOException | InterruptedException ioOrInterrupted) {
            ioOrInterrupted.printStackTrace();
        }
    }


    //-> COMPONENTES

    /** 
     * Componente que exibe o título do programa mais uma linha
     * em branco embaixo, sem subtítulo.
     */
    public static void compCabecalho() {
        System.out.println(
            "--> " + ProgramInfo.TITUTO_PROGRAMA.toUpperCase() +
            " <--\n"
        );
    }

    /** 
     * Componente que exibe o título do programa seguido de um
     * subtítulo personalizado, mais uma linha em branco embaixo.
     * 
     * @param   subtituo    o subtítulo personalizado 
     */
    public static void compCabecalho(String subtitulo) {
        System.out.println(
            "--> " 
            + ProgramInfo.TITUTO_PROGRAMA.toUpperCase()
            + " — " + subtitulo
            + " <--\n"
        );
    }

    
    //-> COMPONENTES EMBUTIDOS EM CLASSES
    
    /** Representa genericamente qualquer tipo de componente */
    public static interface Componente {

        /** Renderiza este componente e retorna um objeto qualquer */
        public Object render();

    }

    /** 
     * {@code CompTextBox} representa o componente 
     * <b>Caixa de Texto</b>, que aceita escrita de texto via teclado.
     * Implementa também uma <b>Label</b>, seu campo descritivo.
     */
    public static class CompTextBox implements Componente, AutoCloseable {
        
        //-> ATRIBUTOS

        /** O texto descritivo deste TextBox */
        private String label;

        /** Tipo de layout (se em linha ou em coluna) */
        private boolean inLine;

        /** 
         * Marca se este TextBox está fechado. Se sim, não poderá ser
         * renderizado.
         */
        private boolean closed;


        //-> CONSTRUTORES

        /** 
         * Constrói uma caixa de texto inicialmente sem <i>label</i>
         * e em linha (<i>label</i> e <i>caixa de texto na mesma
         * linha</i>).
         */
        public CompTextBox() {
            inLine = true;
        }

        /**
         * Devolve este {@code TextBox} com a {@code label} corrente.
         * 
         * @param   label
         *          o texto descritivo deste {@code TextBox}.
         *          Se {@code null}, não adiciona texto descritivo.
         * 
         * @return  este {@code TextBox} com seu {@code label} 
         *          correspondente
         */
        public CompTextBox comLabel(String label) {
            this.label = label;
            return this;
        }

        /**
         * Devolve este {@code TextBox} sem texto descritivo.
         * 
         * @return  este {@code TextBox} com seu {@code label} 
         *          correspondente
         */
        public CompTextBox semLabel() {
            label = null;
            return this;
        }


        //-> GETTERS

        /** Define o tipo de layout (se em linha ou multilinha) */
        public void layout(boolean emLinha) {
            inLine = emLinha;
        }


        //-> MÉTODOS

        /**
         * Renderiza este {@code TextBox}, se aberto para uso, e 
         * retorna o texto inserido no formato de {@code String}.
         * 
         * Se o seu <b>layout</b> for <i>em linha</i>,
         * a <i>label</i> e o <i>campo de texto</i> serão
         * posicionados na mesma linha, em ordem respectiva. Caso
         * contrário, serão renderizados em linhas diferentes.
         * 
         * @return  o texto digitado sobre o campo
         * 
         * @throws  RuntimeException   
         *          se este componente estiver fechado
         *          para uso
         */
        @Override
        public String render() {
            if (closed)
                throw new RuntimeException("Este TextBox está fechado para uso");

            String saida = (inLine) ? label + " " : label + " \n";
            System.out.print(saida);

            String entrada;
            try {
                entrada = new BufferedReader(new InputStreamReader(System.in)).readLine();
            } catch (IOException ioException) {
                ioException.printStackTrace();
                entrada = null;
            }

            return entrada;
        }

        @Override
        /** Fecha o buffer de entrada do teclado */
        public void close() {
            closed = true;
        }

    }

    
}
