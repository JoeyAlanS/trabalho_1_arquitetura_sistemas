import java.util.List;

public class ListaAlimentoIterator implements AlimentoIterator {
    private List<Alimento> refeicoes;
    private int posicao = 0;

    public ListaAlimentoIterator(List<Alimento> refeicoes) {
        this.refeicoes = refeicoes;
    }

    @Override
    public boolean hasNext() {
        return posicao < refeicoes.size();
    }

    @Override
    public Alimento next() {
        return refeicoes.get(posicao++);
    }
}
