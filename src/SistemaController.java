import java.util.Scanner;

class SistemaController {
    private Scanner scanner = new Scanner(System.in);
    private UsuarioService usuarioService = new UsuarioService();
    private MenuService menuService = new MenuService(scanner, usuarioService);

    public void iniciar() {
        if (usuarioService.getUsuarios().isEmpty()) {
            usuarioService.registrarUsuario(UsuarioFactory.criarUsuario(1, "Lucas", "00000000000", "lucas@email.com", "1234", null));
            usuarioService.registrarUsuario(UsuarioFactory.criarUsuario(2, "Ana", "00000000000", "ana@email.com", "5678", "1234"));
        }

        System.out.println("MENU DE LOGIN");
        System.out.println("1 - Fazer login");
        System.out.println("2 - Cadastrar-se");
        int escolha = scanner.nextInt();
        scanner.nextLine();

        if (escolha == 1) {
            menuService.loginUsuario();
        } else if (escolha == 2) {
            menuService.cadastrarUsuario();
        } else {
            System.out.println("Opção inválida!");
        }
    }
}
