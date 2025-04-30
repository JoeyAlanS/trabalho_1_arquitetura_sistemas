import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class MenuService {
    private Scanner scanner;
    private UsuarioService usuarioService;
    private DiarioService diarioService;
    private PlanoService planoService;

    public MenuService(Scanner scanner, UsuarioService usuarioService) {
        this.scanner = scanner;
        this.usuarioService = usuarioService;
        this.diarioService = new DiarioService();
        this.planoService = new PlanoService();
    }

    public void exibirMenuLogin() {
        System.out.println("MENU DE LOGIN");
        System.out.println("1 - Fazer login");
        System.out.println("2 - Cadastrar-se");

        int escolha = scanner.nextInt();
        scanner.nextLine();

        if (escolha == 1) {
            loginUsuario();
        } else if (escolha == 2) {
            cadastrarUsuario();
        } else {
            System.out.println("Opção inválida!");
        }
    }

    public void loginUsuario() {
        System.out.println("Digite seu email:");
        String email = scanner.nextLine();
        System.out.println("Digite sua senha:");
        String senha = scanner.nextLine();

        Usuario usuario = usuarioService.autenticar(email, senha);
        if (usuario != null) {
            usuario.login();
            MenuStrategy strategy = null;

            if (usuario instanceof Paciente) {
                strategy = new MenuPacienteStrategy();
            } else if (usuario instanceof Nutricionista) {
                strategy = new MenuNutricionistaStrategy(usuarioService);
            }

            if (strategy != null) {
                strategy.exibirMenu(scanner, usuario, diarioService, planoService);
            }
        } else {
            System.out.println("Credenciais inválidas!");
        }
    }

    public void cadastrarUsuario() {
        System.out.println("Escolha o tipo de usuário");
        System.out.println("1 - Paciente");
        System.out.println("2 - Nutricionista");

        int tipo = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Digite seu nome:");
        String nome = scanner.nextLine();

        System.out.println("Digite seu CPF:");
        String cpf = scanner.nextLine();

        System.out.println("Digite seu email:");
        String email = scanner.nextLine();

        System.out.println("Digite sua senha:");
        String senha = scanner.nextLine();

        String crn = null;
        if (tipo == 2) {
            System.out.println("Digite o CRN:");
            crn = scanner.nextLine();
        }

        Usuario usuario = UsuarioFactory.criarUsuario(tipo, nome, cpf, email, senha, crn);
        if (usuario != null) {
            usuarioService.registrarUsuario(usuario);
            System.out.println("Usuário cadastrado com sucesso!");
        } else {
            System.out.println("Erro ao criar usuário.");
        }
    }


}
