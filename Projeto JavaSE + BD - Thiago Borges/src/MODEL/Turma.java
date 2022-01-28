package MODEL;

public class Turma {

    private String nome, turno;
    private int id;

    public Turma(String nome) {}

    public Turma(String nome, String turno, int id) {
        this.nome = nome;
        this.turno = turno;
        this.id = id;
    }

    public Turma(String nome, String turno) {
        this.nome = nome;
        this.turno = turno;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
