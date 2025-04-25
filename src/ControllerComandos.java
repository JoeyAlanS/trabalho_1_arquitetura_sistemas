import java.util.Stack;

public class ControllerComandos {
    private Stack<Comando> historico = new Stack<>();

    public void executarComando(Comando comando) {
        comando.executar();
        historico.push(comando);
    }

    public void desfazerUltimoComando() {
        if (!historico.isEmpty()) {
            Comando ultimo = historico.pop();
            ultimo.desfazer();
            System.out.println("Última ação desfeita.");
        } else {
            System.out.println("Nada a desfazer.");
        }
    }
}
