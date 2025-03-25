
public class Nutricionista extends Usuario {
    String crn;

    public Nutricionista(int id, String nome, String cpf, String email, String senha, String crn) {
        super(id, nome, cpf, email, senha);
        this.crn = crn;
    }

    @Override
    public void login() {
        System.out.println("Bem-vinda, " + nome + " (Nutricionista)!");
    }

    public void adicionarPlanoAlimentar(Paciente paciente, PlanoAlimentar plano) {
        paciente.setPlanoAlimentar(plano);
        System.out.println("Plano alimentar adicionado ao paciente: " + paciente.getNome());
    }

    public void removerPlanoAlimentar(Paciente paciente) {
        paciente.setPlanoAlimentar(null);
        System.out.println("Plano alimentar removido do paciente: " + paciente.getNome());
    }
}