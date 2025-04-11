import java.util.Date;
import java.util.Optional;

class DiarioService {

    public void adicionarAlimento(Paciente paciente, String nome, double quantidade, double calorias) {
        Alimento alimento = new Alimento(nome, quantidade, calorias);
        Diario diario = buscarOuCriarDiarioDoDia(paciente);
        diario.adicionarRefeicao(alimento);
    }

    public boolean removerAlimento(Paciente paciente, int indiceAlimento) {
        Diario diario = obterDiarioDoDia(paciente);
        if (diario == null || diario.getRefeicoes().isEmpty()) return false;

        if (indiceAlimento < 0 || indiceAlimento >= diario.getRefeicoes().size()) return false;

        Alimento removido = diario.getRefeicoes().get(indiceAlimento);
        diario.removerRefeicao(removido);

        if (diario.getRefeicoes().isEmpty()) {
            paciente.removerDiario(diario);
        }
        return true;
    }

    public Diario obterDiarioDoDia(Paciente paciente) {
        Date hoje = normalizarData(new Date());
        Optional<Diario> diario = paciente.getDiarios().stream()
                .filter(d -> normalizarData(d.getData()).equals(hoje))
                .findFirst();
        return diario.orElse(null);
    }

    public Diario buscarOuCriarDiarioDoDia(Paciente paciente) {
        Diario diario = obterDiarioDoDia(paciente);
        if (diario == null) {
            diario = new Diario(new Date());
            paciente.adicionarDiario(diario);
        }
        return diario;
    }

    private Date normalizarData(Date data) {
        return new Date(data.getYear(), data.getMonth(), data.getDate());
    }
}
