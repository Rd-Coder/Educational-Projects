package rdcoder.edu.fundamentos_de_poo_com_java.heranca
        .calculadora_salario_liquido.ui.cli_components;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.util.Objects;

/**  
 * {@code CompTextBox} representa o componente
 * <b>Caixa de Texto</b>, para inserção de texto via teclado.
 * Embute também uma <b>Label</b> para descrevê-lo.
 */
public class TextBox implements Componente<TextBox> {
    
    //-> ATRIBUTOS

    /** O texto descritivo deste {@code TextBox} */
    private String label;

    /** O valor contido neste {@code TextBox} definido via teclado. */
    private String valor;

    private Layout layout;


    //-> CONSTRUTORES

    /** 
     * Constrói uma caixa de texto vazia, sem label, 
     * com o layout de <strong>somente caixa de texto</strong>.
     * 
     * @see Layout#SOMENTE_CAIXA_DE_TEXTO
     */
    public TextBox() {
        label = valor = null;
        layout = Layout.SOMENTE_CAIXA_DE_TEXTO;
    }


    //—> SOBRESCRITAS DE 'COMPONENTE'

    /**
     * Renderiza este {@code TextBox}, de acordo com o layout
     * definido.
     * 
     * @throws  IOException
     *          caso ocorra algum erro ao capturar a sequência de
     *          caracteres inseridos neste {@code TextBox}
     */
    @Override
    public TextBox render() {
        String vlr = null;
        try {
            var entradaStream = 
                new BufferedReader(new InputStreamReader(System.in));
            vlr = layout.builder().build(this, entradaStream);
        } catch (IOException ioLeituraTecladoExc) {
            ioLeituraTecladoExc.printStackTrace();
        } finally {
            if (Objects.nonNull(vlr))
                this.valor = vlr;
        }
        return this;
    }


    //—> GETTERS E SETTERS

    /**
     * Define a {@code label} deste TextBox e devolve
     * esta mesma instância de {@code TextBox}.
     * 
     * @param   label
     *          o texto descritivo para este {@code TextBox}.
     *          Se {@code null} ou em branco, {@code label} é
     *          definido como nulo, ou seja, inexistente
     * 
     * @return  este {@code TextBox} com o novo {@code label} 
     *          definido
     */
    public TextBox label(final String label) {
        this.label = (label.isBlank()) ? null : label;
        return this;
    }

    /** Retorna a <strong>label</strong> deste {@code TextBox} */
    public String label() {
        return label;
    }

    /**
     * Define o tipo de layout desta caixa de texto.
     * 
     * @param   layout  um enumerador do tipo {@code Layout}
     */
    public void layout(Layout layout) {
        this.layout = layout;
    }

    public String valor() {
        return valor;
    }


    //—> ENUMS

    /**
     * Enumera os tipos de layouts deste <b>TextBox</b>, em que cada
     * constante retorna uma função do tipo {@code LayoutBuilder} que
     * modela esse layout.
     */
    private static enum Layout {

        /** Modela apenas a caixa de texto com a <i>label</i> oculta */
        SOMENTE_CAIXA_DE_TEXTO (
            (TextBox txtBox, BufferedReader txtField) -> { 
               return LayoutBuilder.buildCampoDeTexto(txtField);
            }
        ),

        /** 
         * Modela a <b>label</b> seguida na mesma linha pelo campo
         * de texto. Vide exemplo:
         * 
         * <blockquote><pre>
         * Uma-label-personalizada: meu campo de texto|
         * </pre></blockquote>
         */
        EM_LINHA(
            (TextBox txtBox, BufferedReader txtField) -> { 
                return 
                    txtBox.label() + " "
                    + LayoutBuilder.buildCampoDeTexto(txtField);
            }
        ),

        /** 
         * Torna a <i>label</i> visível e concatena, na próxima linha,
         * o caractere {@code >} seguido do <strong>campo de texto</strong>.
         * Vide exemplo:
         * 
         * <blockquote><pre>
         * Uma-label-personalizada
         *     > meu campo de texto|
         * </pre></blockquote>
         */
        MULTILINHA(
            (TextBox txtBox, BufferedReader txtField) -> {
                return txtBox.label + " \n\t> " 
                + LayoutBuilder.buildCampoDeTexto(txtField);
            }
        );


        //—> BUILDER
        private LayoutBuilder layoutBuilder;
        private Layout(LayoutBuilder layoutBuilder) {
            this.layoutBuilder = layoutBuilder;
        }

        /**
         * @return  a função {@code LayoutBuilder} que formata o
         *          layout do {@code TextBox}
         */
        public LayoutBuilder builder() {
            return layoutBuilder;
        }


    }

    /** Guarda uma função que constrói o layout de <b>TextBoxes</b> */
    @FunctionalInterface
    public static interface LayoutBuilder {

        /**
         * Constrói o layout do referido {@code txtBox} de acordo com
         * esta função e retorna o {@code InputStream} com o texto
         * digitado pelo usuário.
         * 
         * @param   txtBox
         *          o {@code TextBox} a se modelar o layout
         * @param   campoDeTxt
         *          o {@code Reader} que capturará a entrada via
         *          teclado
         * @return  {@code String} com o texto digitado pelo usuário
         * @throws  IOException
         *          se ocorrer algum problema ao ler o <i>campo 
         *          de texto</i>
         */
        public String build(TextBox txtBox, BufferedReader campoDeTxt) 
        throws IOException;

        /** 
         * Constrói o campo de texto referenciado em três etapas:
         * 
         * <ol>
         *  <li>Mostra o campo de texto ao usuário;</li>
         *  <li>
         *      Captura o texto digitado com o <i>BufferedReader</i>
         *      injetado, em seguida libera os recursos alocados por ele;
         *  <li>Retorna o texto capturado em uma {@code String}.</li> 
         * </ol>
         * 
         * @throws  IOException
         *          caso ocorra algum erro ao capturar a sequência de
         *          caracteres inseridos neste {@code TextBox}.
         */
        static String buildCampoDeTexto(BufferedReader campoDeTxt) 
        throws IOException {
            String txt = campoDeTxt.readLine();
            campoDeTxt.close();
            return txt;
        }
    

    }


}
