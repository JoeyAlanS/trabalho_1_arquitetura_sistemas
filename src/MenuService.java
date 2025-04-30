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
            System.out.println("3 - Ver diário de hoje");
            System.out.println("4 - Ver plano alimentar");
            System.out.println("5 - Sair");

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

                    diarioService.adicionarAlimento(paciente, nome, quantidade, calorias);
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

                    if (diarioService.removerAlimento(paciente, indice)) {
                        System.out.println("Alimento removido com sucesso!");
                    } else {
                        System.out.println("Opção inválida ou alimento não encontrado.");
                    }
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
                case 4 -> paciente.visualizarPlanoAlimentar();
                case 5 -> sair = true;
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private void mostrarMenuNutricionista(Nutricionista nutricionista) {
        boolean sair = false;
        while (!sair) {
            System.out.println("\nMENU NUTRICIONISTA");
            System.out.println("1 - Criar plano alimentar");
            System.out.println("2 - Visualizar plano alimentar");
            System.out.println("3 - Editar plano alimentar");
            System.out.println("4 - Remover plano alimentar");
            System.out.println("5 - Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> {
                    Paciente paciente = selecionarPaciente();
                    if (paciente == null) break;

                    System.out.println("Nome do plano:");
                    String nome = scanner.nextLine();

                    System.out.println("Descrição:");
                    String descricao = scanner.nextLine();

                    List<Alimento> alimentos = new ArrayList<>();
                    boolean adicionando = true;

                    while (adicionando) {
                        System.out.println("Adicionar alimento (s/n)?");
                        String resp = scanner.nextLine();
                        if (!resp.equalsIgnoreCase("s")) break;

                        System.out.println("Nome do alimento:");
                        String nomeAli = scanner.nextLine();

                        System.out.println("Quantidade (g):");
                        double qtd = scanner.nextDouble();

                        System.out.println("Calorias:");
                        double calorias = scanner.nextDouble();
                        scanner.nextLine();

                        alimentos.add(new Alimento(nomeAli, qtd, calorias));
                    }

                    planoService.criarPlano(nutricionista, paciente, nome, descricao, alimentos);
                }
                case 2 -> {
                    Paciente paciente = selecionarPaciente();
                    if (paciente != null) {
                        planoService.visualizarPlano(paciente);
                    }
                }
                case 3 -> {
                    Paciente paciente = selecionarPaciente();
                    if (paciente == null) break;

                    PlanoAlimentar plano = paciente.getPlanoAlimentar();
                    if (plano == null) {
                        System.out.println("Paciente não possui plano alimentar.");
                        break;
                    }

                    System.out.println("Editar plano alimentar:");
                    System.out.println("1 - Adicionar alimento");
                    System.out.println("2 - Remover alimento");

                    int escolha = scanner.nextInt();
                    scanner.nextLine();

                    switch (escolha) {
                        case 1 -> {
                            System.out.println("Nome do alimento:");
                            String nome = scanner.nextLine();

                            System.out.println("Quantidade (g):");
                            double qtd = scanner.nextDouble();

                            System.out.println("Calorias:");
                            double calorias = scanner.nextDouble();
                            scanner.nextLine();

                            Alimento novoAli = new Alimento(nome, qtd, calorias);
                            planoService.editarPlano(nutricionista, paciente, novoAli);
                        }
                        case 2 -> {
                            List<Alimento> alimentos = plano.getRefeicoes();
                            if (alimentos.isEmpty()) {
                                System.out.println("Plano alimentar está vazio.");
                                break;
                            }

                            System.out.println("Alimentos no plano:");
                            for (int i = 0; i < alimentos.size(); i++) {
                                System.out.println((i + 1) + " - " + alimentos.get(i));
                            }

                            System.out.println("Informe o número do alimento para remover:");
                            int indice = scanner.nextInt() - 1;
                            scanner.nextLine();

                            planoService.removerRefeicaoDoPlano(nutricionista, paciente, indice);
                        }
                        default -> System.out.println("Opção inválida!");
                    }
                }
                case 4 -> {
                    Paciente paciente = selecionarPaciente();
                    if (paciente != null) {
                        planoService.removerPlano(nutricionista, paciente);
                    }
                }
                case 5 -> sair = true;
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private Paciente selecionarPaciente() {
        List<Usuario> usuarios = usuarioService.getUsuarios();
        List<Paciente> pacientes = new ArrayList<>();

        for (Usuario u : usuarios) {
            if (u instanceof Paciente) {
                pacientes.add((Paciente) u);
            }
        }

        if (pacientes.isEmpty()) {
            System.out.println("Nenhum paciente cadastrado.");
            return null;
        }

        System.out.println("Selecione o paciente:");
        for (int i = 0; i < pacientes.size(); i++) {
            System.out.println((i + 1) + " - " + pacientes.get(i).getNome());
        }

        int escolha = scanner.nextInt();
        scanner.nextLine();

        if (escolha < 1 || escolha > pacientes.size()) {
            System.out.println("Opção inválida.");
            return null;
        }

        return pacientes.get(escolha - 1);
    }


}
