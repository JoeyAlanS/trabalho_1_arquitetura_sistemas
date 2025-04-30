import java.util.Date;
import java.util.List;

class PlanoService {

    public void criarPlano(Nutricionista nutricionista, Paciente paciente, String nomePlano, String descricao, List<Alimento> alimentos) {
        PlanoAlimentar plano = new PlanoAlimentar(1, nomePlano, descricao, new Date());
        for (Alimento alimento : alimentos) {
            plano.adicionarRefeicao(alimento);
        }
        nutricionista.criarPlanoAlimentar(paciente, plano);
    }

    public void visualizarPlano(Paciente paciente) {
        paciente.visualizarPlanoAlimentar();
    }

    public void editarPlano(Nutricionista nutricionista, Paciente paciente, Alimento novoAlimento) {
        PlanoAlimentar plano = paciente.getPlanoAlimentar();
        if (plano != null) {
            plano.adicionarRefeicao(novoAlimento);
            nutricionista.editarPlanoAlimentar(paciente, plano);
        } else {
            System.out.println("Paciente não possui plano alimentar.");
        }
    }

    public void removerPlano(Nutricionista nutricionista, Paciente paciente) {
        nutricionista.removerPlanoAlimentar(paciente);
    }

    public void removerRefeicaoDoPlano(Nutricionista nutricionista, Paciente paciente, int indice) {
        PlanoAlimentar plano = paciente.getPlanoAlimentar();
        if (plano != null && indice >= 0 && indice < plano.getRefeicoes().size()) {
            Alimento alimentoRemovido = plano.getRefeicoes().get(indice);
            plano.removerRefeicao(alimentoRemovido);
            nutricionista.editarPlanoAlimentar(paciente, plano);
            System.out.println("Alimento removido do plano com sucesso!");
        } else {
            System.out.println("Índice inválido ou plano não encontrado.");
        }
    }

}
