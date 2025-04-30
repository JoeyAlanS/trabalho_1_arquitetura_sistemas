import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuNutricionistaStrategy implements MenuStrategy {
    private UsuarioService usuarioService;

    public MenuNutricionistaStrategy(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public void exibirMenu(Scanner scanner, Usuario usuario, DiarioService diarioService, PlanoService planoService) {
        Nutricionista nutricionista = (Nutricionista) usuario;
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
                case 1, 2, 3, 4 -> {
                    List<Usuario> usuarios = usuarioService.getUsuarios();
                    List<Paciente> pacientes = usuarios.stream()
                            .filter(u -> u instanceof Paciente)
                            .map(u -> (Paciente) u)
                            .toList();

                    if (pacientes.isEmpty()) {
                        System.out.println("Nenhum paciente cadastrado.");
                        break;
                    }

                    System.out.println("Selecione o paciente:");
                    for (int i = 0; i < pacientes.size(); i++) {
                        System.out.println((i + 1) + " - " + pacientes.get(i).getNome());
                    }

                    int escolha = scanner.nextInt();
                    scanner.nextLine();

                    if (escolha < 1 || escolha > pacientes.size()) {
                        System.out.println("Opção inválida.");
                        break;
                    }

                    Paciente paciente = pacientes.get(escolha - 1);

                    switch (opcao) {
                        case 1 -> {
                            System.out.println("Nome do plano:");
                            String nome = scanner.nextLine();
                            System.out.println("Descrição:");
                            String descricao = scanner.nextLine();

                            List<Alimento> alimentos = new ArrayList<>();
                            while (true) {
                                System.out.println("Adicionar alimento (s/n)?");
                                if (!scanner.nextLine().equalsIgnoreCase("s")) break;

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
                        case 2 -> planoService.visualizarPlano(paciente);
                        case 3 -> {
                            PlanoAlimentar plano = paciente.getPlanoAlimentar();
                            if (plano == null) {
                                System.out.println("Paciente não possui plano.");
                                break;
                            }

                            System.out.println("1 - Adicionar alimento");
                            System.out.println("2 - Remover alimento");
                            int ed = scanner.nextInt();
                            scanner.nextLine();

                            if (ed == 1) {
                                System.out.println("Nome do alimento:");
                                String nome = scanner.nextLine();

                                System.out.println("Quantidade (g):");
                                double qtd = scanner.nextDouble();

                                System.out.println("Calorias:");
                                double calorias = scanner.nextDouble();
                                scanner.nextLine();

                                planoService.editarPlano(nutricionista, paciente, new Alimento(nome, qtd, calorias));
                            } else if (ed == 2) {
                                List<Alimento> alimentos = plano.getRefeicoes();
                                for (int i = 0; i < alimentos.size(); i++) {
                                    System.out.println((i + 1) + " - " + alimentos.get(i));
                                }
                                System.out.println("Escolha o número para remover:");
                                int idx = scanner.nextInt() - 1;
                                scanner.nextLine();
                                planoService.removerRefeicaoDoPlano(nutricionista, paciente, idx);
                            }
                        }
                        case 4 -> planoService.removerPlano(nutricionista, paciente);
                    }
                }
                case 5 -> sair = true;
                default -> System.out.println("Opção inválida!");
            }
        }
    }
    
}
