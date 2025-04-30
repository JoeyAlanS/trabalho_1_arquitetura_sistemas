import java.util.Scanner;

public interface MenuStrategy {
    void exibirMenu(Scanner scanner, Usuario usuario, DiarioService diarioService, PlanoService planoService);
}
