import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class Diario {
    private Date data;
    private List<Alimento> refeicoes = new ArrayList<>();

    public Diario(Date data) {
        this.data = data;
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

    public Date getData() {
        return data;
    }
}
