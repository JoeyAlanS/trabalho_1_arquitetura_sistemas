import java.util.ArrayList;
import java.util.List;

class Paciente extends Usuario {
    private List<Diario> diarios = new ArrayList<>();
    private PlanoAlimentar planoAlimentar;

    public Paciente(int id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
    }

    public void adicionarDiario(Diario diario) {
        diarios.add(diario);
    }

    public void removerDiario(Diario diario) {
        diarios.remove(diario);
    }

    public List<Diario> getDiarios() {
        return diarios;
    }

    public void visualizarDiario() {
        System.out.println("\nVisualizando alimentos no diário de " + nome);
        for (Diario diario : diarios) {
            System.out.println("Data: " + diario.getData());
            for (Alimento alimento : diario.getRefeicoes()) {
                System.out.println(alimento);
            }
        }
    }

    public PlanoAlimentar buscarPlanoAlimentar() {
        return planoAlimentar;
    }

    public void setPlanoAlimentar(PlanoAlimentar plano) {
        this.planoAlimentar = plano;
    }

    @Override
    public void login() {
        System.out.println("Paciente logado com sucesso!");
    }
}
