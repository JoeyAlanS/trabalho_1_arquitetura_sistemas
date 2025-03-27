
public class Nutricionista extends Usuario {
    private String crn;

    public Nutricionista(int id, String nome, String cpf, String email, String senha, String crn) {
        super(id, nome, cpf, email, senha);
        this.crn = crn;
    }

    public String getCrn() {
        return crn;
    }

    public void setCrn(String crn) {

    }

    @Override
    public void login() {
        System.out.println("Bem-vinda, " + getNome() + " (Nutricionista)!");
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