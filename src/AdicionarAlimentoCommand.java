public class AdicionarAlimentoCommand implements Comando {
    private DiarioService diarioService;
    private Paciente paciente;
    private String nome;
    private double quantidade;
    private double calorias;
    private Alimento alimentoAdicionado;

    public AdicionarAlimentoCommand(DiarioService diarioService, Paciente paciente, String nome, double quantidade, double calorias) {
        this.diarioService = diarioService;
        this.paciente = paciente;
        this.nome = nome;
        this.quantidade = quantidade;
        this.calorias = calorias;
    }

    @Override
    public void executar() {
        alimentoAdicionado = diarioService.adicionarAlimento(paciente, nome, quantidade, calorias);
    }

    @Override
    public void desfazer() {
        if (alimentoAdicionado != null) {
            diarioService.removerAlimento(paciente, alimentoAdicionado);
        }
    }
}
