package organizaveis;

/** 
 * A interface {@code Organizavel} representa um objeto organizável
 * qualquer. Ela atribui às classes que a implementam a qualidade de
 * poder ser organizada de alguma maneira.
 * 
*/
public interface Organizavel {

    //CONSTANTES
    /** Um vetor constante que armazena um conjunto de metais. */
    public String[] metais = new String[]{"ferro", "ouro", "prata"};

    //MÉTODOS ABSTRATOS
    /** 
     * Atribui à classe que implementa esta interface a habilidade
     * de escrever na tela.
     */
    void escrever();

}