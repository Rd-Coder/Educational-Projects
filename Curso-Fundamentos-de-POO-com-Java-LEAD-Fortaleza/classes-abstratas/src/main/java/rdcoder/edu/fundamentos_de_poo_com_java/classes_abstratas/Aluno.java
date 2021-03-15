package rdcoder.edu.fundamentos_de_poo_com_java.classes_abstratas;

/** A classe {@code Aluno} representa um aluno de uma escola. */
public class Aluno extends AbstractPessoa {

    //ATRIBUTOS
    private int matricula;
    private char turma;

    
    //CONSTRUTORES
    public Aluno(String nome, int matricula, char turma) 
    {
        super(nome);
        this.matricula = matricula;
        this.turma = turma;
    }


    //GETTERS E SETTERS
    public void setMatricula(int matricula) { 
        this.matricula = matricula; 
    }

    public int getMatricula() { 
        return this.matricula; 
    }

    public void setTurma(char turma) { 
        this.turma = turma; }
    public int getTurma() { return this.turma; 
    }


    //OUTROS MÉTODOS
    @Override
    public String toString() { 
        return super.toString(); 
    }
}