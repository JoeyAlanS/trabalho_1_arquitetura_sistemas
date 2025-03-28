import java.util.Scanner;

class MenuService {
    private Scanner scanner;
    private UsuarioService usuarioService;
    private DiarioService diarioService;

    public MenuService(Scanner scanner, UsuarioService usuarioService) {
        this.scanner = scanner;
        this.usuarioService = usuarioService;
        this.diarioService = new DiarioService(scanner);
    }

    public void loginUsuario() {
        System.out.println("Digite seu email:");
        String email = scanner.nextLine();

        System.out.println("Digite sua senha:");
        String senha = scanner.nextLine();

        Usuario usuario = usuarioService.autenticar(email, senha);
        if (usuario != null) {
            usuario.login();
            if (usuario instanceof Paciente) {
                mostrarMenuPaciente((Paciente) usuario);
            } else if (usuario instanceof Nutricionista) {
                mostrarMenuNutricionista((Nutricionista) usuario);
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

    private void mostrarMenuPaciente(Paciente paciente) {
        boolean sair = false;
        while (!sair) {
            System.out.println("\nMENU PACIENTE");
            System.out.println("1 - Adicionar alimento");
            System.out.println("2 - Remover alimento");
            System.out.println("3 - Ver alimentos adicionados");
            System.out.println("4 - Ver Plano Alimentar");
            System.out.println("5 - Sair");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    diarioService.adicionarAlimento(paciente);
                    break;
                case 2:
                    diarioService.removerAlimento(paciente);
                    break;
                case 3:
                    paciente.visualizarDiario();
                    break;
                case 4:
                    paciente.visualizarPlanoAlimentar();
                    break;
                case 5:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void mostrarMenuNutricionista(Nutricionista nutricionista) {
        boolean sair = false;
        while (!sair) {
            System.out.println("\nMENU NUTRICIONISTA");
            System.out.println("1 - Criar plano alimentar");
            System.out.println("2 - Remover plano alimentar");
            System.out.println("3 - Sair");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Funcionalidade criar plano alimentar.");
                    break;
                case 2:
                    System.out.println("Funcionalidade remover plano alimentar.");
                    break;
                case 3:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}
