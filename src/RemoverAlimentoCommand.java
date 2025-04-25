public class RemoverAlimentoCommand implements Comando {
    private DiarioService diarioService;
    private Paciente paciente;
    private int indice;
    private Alimento alimentoRemovido;

    public RemoverAlimentoCommand(DiarioService diarioService, Paciente paciente, int indice) {
        this.diarioService = diarioService;
        this.paciente = paciente;
        this.indice = indice;
    }

    @Override
    public void executar() {
        alimentoRemovido = diarioService.removerAlimento(paciente, indice);
    }

    @Override
    public void desfazer() {
        if (alimentoRemovido != null) {
            diarioService.adicionarAlimentoExistente(paciente, alimentoRemovido);
        }
    }
}
