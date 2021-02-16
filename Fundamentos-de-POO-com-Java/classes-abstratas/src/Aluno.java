/** A classe {@code Aluno} representa um aluno de uma escola. */
public class Aluno extends AbstractPessoa {

    //ATRIBUTOS
    /** O número da matrícula deste aluno */
    private int matricula;

    /** A indetificação da turma a que este aluno pertence. */
    private char turma;

    
    //CONSTRUTORES
    public Aluno(String nome, int matricula, char turma) 
    {
        super(nome);
        this.matricula = matricula;
        setTurma(turma);
    }


    //GETTERS E SETTERS
    public void setMatricula(int matricula) { 
        this.matricula = matricula; 
    }

    public int getMatricula() { 
        return this.matricula; 
    }

    /**
     * Define a turma deste aluno.
     * 
     * @param   turma
     *          a identificação alfabética da turma
     *          a que este aluno pertence.
     */
    public void setTurma(char turma) { 
        if (!String.valueOf(turma).matches("[A-za-z]"))
            throw new IllegalArgumentException(
                "O id da turma não corresponde a uma letra."
            );
    
        this.turma = turma; 
    }

    /**
     * Retorna a identificação da turma deste aluno.
     * @return
     */
    public char getTurma() { 
        return this.turma; 
    }

    
}