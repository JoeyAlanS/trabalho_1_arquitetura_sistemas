import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class PlanoAlimentar {
    private int id;
    private String nome;
    private String descricao;
    private Date dataCriacao;
    private List<Alimento> refeicoes = new ArrayList<>();

    public PlanoAlimentar(int id, String nome, String descricao, Date dataCriacao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
    }

    public void adicionarRefeicao(Alimento alimento) {
        refeicoes.add(alimento);
    }

    public void removerRefeicao(Alimento alimento) {
        refeicoes.remove(alimento);
    }

    public List<Alimento> getRefeicoes() {
        return refeicoes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Plano: ").append(nome).append(" - ").append(descricao).append("\nRefei��es:\n");
        for (Alimento a : refeicoes) {
            sb.append(a).append("\n");
        }
        return sb.toString();
    }

}
