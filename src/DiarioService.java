import java.util.Date;
import java.util.Optional;
import java.util.Calendar;

class DiarioService {

    public Alimento adicionarAlimento(Paciente paciente, String nome, double quantidade, double calorias) {
        Alimento alimento = new Alimento(nome, quantidade, calorias);
        Diario diario = buscarOuCriarDiarioDoDia(paciente);
        diario.adicionarRefeicao(alimento);
        return alimento;
    }

    public void adicionarAlimentoExistente(Paciente paciente, Alimento alimento) {
        Diario diario = buscarOuCriarDiarioDoDia(paciente);
        System.out.println("Reinserindo alimento em diário de: " + diario.getData());
        diario.adicionarRefeicao(alimento);
    }


    public Alimento removerAlimento(Paciente paciente, int indice) {
        Diario diario = obterDiarioDoDia(paciente);
        if (diario != null && indice >= 0 && indice < diario.getRefeicoes().size()) {
            return diario.getRefeicoes().remove(indice);
        }
        return null;
    }

    public void removerAlimento(Paciente paciente, Alimento alimento) {
        Diario diario = obterDiarioDoDia(paciente);
        if (diario != null) {
            diario.getRefeicoes().remove(alimento);
        }
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
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
}
