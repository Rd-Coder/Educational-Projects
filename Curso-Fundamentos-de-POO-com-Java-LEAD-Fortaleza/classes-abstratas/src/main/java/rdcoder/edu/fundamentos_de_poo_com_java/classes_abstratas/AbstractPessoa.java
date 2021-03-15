/**
 * A classe abstrata {@code Pessoa} representa um indivíduo humano.
 */
public abstract class AbstractPessoa {

    //ATRIBUTOS
    /** O nome desta pessoa. */
    private String nome;


    //CONSTRUTORES
    public AbstractPessoa(String nome) { 
        this.nome = nome; 
    }

    //GETTERS E SETTERS
    public void setNome(String nome) { 
        this.nome = nome; }

    public String getNome() { return this.nome; 
    }

    /** Retorna uma {@code String} contendo o nome desta pessoa. */
    public String toString() { 
        return this.nome; 
    }
}