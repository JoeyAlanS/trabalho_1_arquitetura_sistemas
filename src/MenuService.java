import java.util.List;
import java.util.Scanner;

class MenuService {
    private Scanner scanner;
    private UsuarioService usuarioService;
    private DiarioService diarioService;

    public MenuService(Scanner scanner, UsuarioService usuarioService) {
        this.scanner = scanner;
        this.usuarioService = usuarioService;
        this.diarioService = new DiarioService();
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
        ControllerComandos controlador = new ControllerComandos();
        boolean sair = false;
        while (!sair) {
            System.out.println("\nMENU PACIENTE");
            System.out.println("1 - Adicionar alimento");
            System.out.println("2 - Remover alimento");
            System.out.println("3 - Ver diário de hoje");
            System.out.println("4 - Desfazer última ação");
            System.out.println("5 - Ver plano alimentar");
            System.out.println("6 - Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> {
                    System.out.println("Nome do alimento:");
                    String nome = scanner.nextLine();

                    System.out.println("Quantidade em gramas:");
                    double quantidade = scanner.nextDouble();

                    System.out.println("Calorias:");
                    double calorias = scanner.nextDouble();
                    scanner.nextLine();

                    Comando adicionar = new AdicionarAlimentoCommand(diarioService, paciente, nome, quantidade, calorias);
                    controlador.executarComando(adicionar);
                    System.out.println("Alimento adicionado com sucesso!");
                }
                case 2 -> {
                    Diario diario = diarioService.obterDiarioDoDia(paciente);
                    if (diario == null || diario.getRefeicoes().isEmpty()) {
                        System.out.println("Nenhum alimento encontrado no diário de hoje.");
                        break;
                    }

                    System.out.println("Alimentos registrados hoje:");
                    List<Alimento> alimentos = diario.getRefeicoes();
                    for (int i = 0; i < alimentos.size(); i++) {
                        System.out.println((i + 1) + " - " + alimentos.get(i));
                    }

                    System.out.println("Escolha o alimento para remover:");
                    int indice = scanner.nextInt() - 1;
                    scanner.nextLine();

                    Comando remover = new RemoverAlimentoCommand(diarioService, paciente, indice);
                    remover.executar();
                    System.out.println("Alimento removido com sucesso!");
                }
                case 3 -> {
                    Diario diario = diarioService.obterDiarioDoDia(paciente);
                    if (diario == null || diario.getRefeicoes().isEmpty()) {
                        System.out.println("Nenhum alimento registrado hoje.");
                    } else {
                        System.out.println("Diário de hoje:");
                        for (Alimento a : diario.getRefeicoes()) {
                            System.out.println(a);
                        }
                    }
                }
                case 4 -> controlador.desfazerUltimoComando();
                case 5 -> paciente.visualizarPlanoAlimentar();
                case 6 -> sair = true;
                default -> System.out.println("Opção inválida!");
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
