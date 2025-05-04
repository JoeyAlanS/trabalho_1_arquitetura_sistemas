import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PlanoAlimentar {
    private int id;
    private String nome;
    private String descricao;
    private Date dataCriacao;
    private List<Alimento> refeicoes = new ArrayList<>();

    // Mudança no construtor, tornando ele privado para o padrão builder
    private PlanoAlimentar() {}

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
        sb.append("Plano: ").append(nome).append(" - ").append(descricao).append("\nRefeições:\n");
        for (Alimento a : refeicoes) {
            sb.append(a).append("\n");
        }
        return sb.toString();
    }

    // --- Builder ---
    public static class Builder {
        private int id;
        private String nome;
        private String descricao;
        private Date dataCriacao;
        private List<Alimento> refeicoes = new ArrayList<>();

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setNome(String nome) {
            this.nome = nome;
            return this;
        }

        public Builder setDescricao(String descricao) {
            this.descricao = descricao;
            return this;
        }

        public Builder setDataCriacao(Date dataCriacao) {
            this.dataCriacao = dataCriacao;
            return this;
        }

        public Builder adicionarRefeicao(Alimento alimento) {
            this.refeicoes.add(alimento);
            return this;
        }

        public PlanoAlimentar build() {
            PlanoAlimentar plano = new PlanoAlimentar();
            plano.id = this.id;
            plano.nome = this.nome;
            plano.descricao = this.descricao;
            plano.dataCriacao = this.dataCriacao;
            plano.refeicoes = this.refeicoes;
            return plano;
        }
    }
}
