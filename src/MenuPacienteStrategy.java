import java.util.List;
import java.util.Scanner;

public class MenuPacienteStrategy implements MenuStrategy {
    @Override
    public void exibirMenu(Scanner scanner, Usuario usuario, DiarioService diarioService, PlanoService planoService) {
        Paciente paciente = (Paciente) usuario;
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
}
