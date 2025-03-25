import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

class SistemaController {
    private Scanner scanner = new Scanner(System.in);
    private List<Usuario> usuarios = new ArrayList<>();

    public void registrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public void iniciar() {
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
            registrarUsuario(new Paciente(1, "Lucas", "00000000000", "lucas@email.com", "1234"));
            registrarUsuario(new Nutricionista(2, "Ana", "00000000000", "ana@email.com", "5678", "1234"));
        }

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
        System.out.println("Escolha sua forma de login");
        System.out.println("1 - Nutricionista");
        System.out.println("2 - Paciente");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Digite seu email:");
        String email = scanner.nextLine();

        System.out.println("Digite sua senha:");
        String senha = scanner.nextLine();

        for (Usuario usuario : usuarios) {
            if (usuario.email.equals(email) && usuario.senha.equals(senha)) {
                usuario.login();
                if (usuario instanceof Paciente) {
                    Paciente paciente = (Paciente) usuario;
                    mostrarMenuPaciente(paciente);
                } else if (usuario instanceof Nutricionista) {
                    Nutricionista nutricionista = (Nutricionista) usuario;
                    mostrarMenuNutricionista(nutricionista);
                }
                return;
            }
        }
        System.out.println("Credenciais inválidas!");
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

        if (tipo == 1) {
            Paciente paciente = new Paciente(usuarios.size() + 1, nome, cpf, email, senha);
            registrarUsuario(paciente);
            paciente.cadastrar();
        } else if (tipo == 2) {
            System.out.println("Digite o CRN:");
            String crn = scanner.nextLine();
            Nutricionista nutricionista = new Nutricionista(usuarios.size() + 1, nome, cpf, email, senha, crn);
            registrarUsuario(nutricionista);
            nutricionista.cadastrar();
        } else {
            System.out.println("Opção inválida!");
        }
    }

    public void adicionarAlimento(Paciente paciente) {
        System.out.println("Digite o nome do alimento:");
        String nome = scanner.nextLine();
        System.out.println("Digite a quantidade em gramas:");
        double quantidade = scanner.nextDouble();
        System.out.println("Digite as calorias:");
        double calorias = scanner.nextDouble();
        scanner.nextLine();

        Alimento alimento = new Alimento(nome, quantidade, calorias);
        Diario diario = new Diario(new Date());
        diario.adicionarRefeicao(alimento);
        paciente.adicionarDiario(diario);
        System.out.println("Alimento adicionado: " + alimento);
    }

    public void removerAlimento(Paciente paciente) {
        paciente.visualizarDiario();
        System.out.println("Escolha o diário (número da refeição):");
        int escolha = scanner.nextInt();
        scanner.nextLine();

        if (escolha < 1 || escolha > paciente.getDiarios().size()) {
            System.out.println("Opção inválida!");
        } else {
            Diario diario = paciente.getDiarios().get(escolha - 1);
            System.out.println("Escolha o alimento a ser removido (número da refeição):");

            for (int i = 0; i < diario.getRefeicoes().size(); i++) {
                System.out.println(i + 1 + " - " + diario.getRefeicoes().get(i));
            }

            int alimentoEscolhido = scanner.nextInt();
            scanner.nextLine();
            if (alimentoEscolhido < 1 || alimentoEscolhido > diario.getRefeicoes().size()) {
                System.out.println("Opção inválida!");
            } else {
                Alimento alimento = diario.getRefeicoes().get(alimentoEscolhido - 1);
                diario.removerRefeicao(alimento);
                System.out.println("Alimento removido: " + alimento);

                if (diario.getRefeicoes().isEmpty()) {
                    paciente.removerDiario(diario);
                    System.out.println("Diário removido pois não há mais alimentos.");
                }
            }
        }
    }


    public void mostrarMenuPaciente(Paciente paciente) {
        boolean sair = false;
        while (!sair) {
            System.out.println("\nMENU PACIENTE");
            System.out.println("1 - Adicionar alimento");
            System.out.println("2 - Remover alimento");
            System.out.println("3 - Ver alimentos adicionados");
            System.out.println("4 - Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    adicionarAlimento(paciente);
                    break;
                case 2:
                    removerAlimento(paciente);
                    break;
                case 3:
                    paciente.visualizarDiario();
                    break;
                case 4:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    public void mostrarMenuNutricionista(Nutricionista nutricionista) {
        boolean sair = false;
        while (!sair) {
            System.out.println("\nMENU NUTRICIONISTA");
            System.out.println("1 - Adicionar plano alimentar ao paciente");
            System.out.println("2 - Remover plano alimentar do paciente");
            System.out.println("3 - Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    PlanoAlimentar plano = new PlanoAlimentar(1, "Plano A", "Plano de dieta balanceada", new Date());
                    nutricionista.adicionarPlanoAlimentar(new Paciente(1, "Lucas", "00000000000", "lucas@email.com", "1234"), plano);
                    break;
                case 2:
                    nutricionista.removerPlanoAlimentar(new Paciente(1, "Lucas", "00000000000", "lucas@email.com", "1234"));
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
