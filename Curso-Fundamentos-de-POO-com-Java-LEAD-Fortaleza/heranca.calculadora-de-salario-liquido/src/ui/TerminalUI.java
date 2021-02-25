package ui;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.nio.Buffer;
import java.util.Scanner;

import jdk.internal.org.jline.utils.ClosedException;
import salarios.SalarioBruto;
import salarios.SalarioLiquido;

/**
 * Modela a interface de interação do usuário deste programa
 * através do terminal.
 */
public class TerminalUI implements Runnable {

    //-> ATRIBUTOS - ENTRADAS
    private String nomeDoFuncionario;
    private String salarioBruto;
    private String bonus;


    //-> RENDERIZAÇÃO E PROCESSAMENTO

    @Override
    public void run() {
        janSolicitaValores();
        
        SalarioBruto sBruto = new SalarioBruto(
            Float.valueOf(salarioBruto));
        SalarioLiquido sLiquido = new SalarioLiquido(
            sBruto, Float.valueOf(bonus));

        //- parou aqui - implementar janExibeResultado();
    }


    /** Formulário que solicita as informações salariais do funcionário */
    public void janSolicitaValores() {
        String nome, salarioBruto, bonus;

        compCabecalho();
        System.out.println(
            "-> Insira as informações requeridas para o cálculo:");
        
        CompTextBox txtBox = new CompTextBox();
        txtBox.layout(false);

        // solicita os dados
        nome = txtBox.comLabel("Nome do Funcionário:").render();
        salarioBruto = txtBox.comLabel("Salário Bruto: R$").render();
        bonus = txtBox.comLabel("Bônus: R$").render();

        txtBox.close();
        nomeDoFuncionario = nome;
        this.salarioBruto = salarioBruto;
        this.bonus = bonus;
    }


    //-> COMPONENTES

    /** 
     * Componente que exibe o título do programa mais uma linha
     * em branco embaixo 
     * */
    public static void compCabecalho() {
        System.out.println(
            "--> " + ProgramInfo.TITUTO_PROGRAMA.toUpperCase() +
            " <--\n"
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

        /** O buffer de entrada */
        private BufferedReader in;

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
            inLine = false;
            in = new BufferedReader(new InputStreamReader(System.in));
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
         * @throws  Exception   
         *          se este componente estiver fechado
         *          para uso
         */
        @Override
        public String render() {
            if (closed)
                throw new Exception("Este TextBox está fechado para uso");

            String saida = (inLine) ? label + " " : label + " \n";

            System.out.print(saida);
            return in.readLine();
        }

        @Override
        /** Fecha o buffer de entrada do teclado */
        public void close() {
            try {
                in.close();
            } catch (IOException io) {
                io.printStackTrace();
            }
        }

    }




    public static void main2(String[] args) {
        Scanner in = new Scanner(System.in);

        String texto = """
            %Nome: %s
            %Idade: %s
            """;

        in.close();
    }

    public static void main(String[] args) {

    }
}
