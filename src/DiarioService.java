import java.util.Date;
import java.util.Scanner;

class DiarioService {
    private Scanner scanner;

    public DiarioService(Scanner scanner) {
        this.scanner = scanner;
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

        Diario diario;
        if (paciente.getDiarios().isEmpty() || paciente.getDiarios().get(paciente.getDiarios().size() - 1).getData().before(new Date())) {
            diario = new Diario(new Date());
            paciente.adicionarDiario(diario);
        } else {
            diario = paciente.getDiarios().get(paciente.getDiarios().size() - 1);
        }

        diario.adicionarRefeicao(alimento);
        System.out.println("Alimento adicionado: " + alimento);
    }

    public void removerAlimento(Paciente paciente) {
        if (paciente.getDiarios().isEmpty()) {
            System.out.println("Nenhum di�rio encontrado.");
            return;
        }

        paciente.visualizarDiario();

        System.out.println("Escolha o di�rio (n�mero da refei��o):");
        int escolhaDiario = scanner.nextInt();
        scanner.nextLine();

        if (escolhaDiario < 1 || escolhaDiario > paciente.getDiarios().size()) {
            System.out.println("Op��o inv�lida!");
            return;
        }

        Diario diario = paciente.getDiarios().get(escolhaDiario - 1);

        if (diario.getRefeicoes().isEmpty()) {
            System.out.println("N�o h� alimentos para remover.");
            return;
        }

        System.out.println("Escolha o alimento a ser removido:");
        for (int i = 0; i < diario.getRefeicoes().size(); i++) {
            System.out.println((i + 1) + " - " + diario.getRefeicoes().get(i));
        }

        int escolhaAlimento = scanner.nextInt();
        scanner.nextLine();

        if (escolhaAlimento < 1 || escolhaAlimento > diario.getRefeicoes().size()) {
            System.out.println("Op��o inv�lida!");
            return;
        }

        Alimento alimento = diario.getRefeicoes().get(escolhaAlimento - 1);
        diario.removerRefeicao(alimento);
        System.out.println("Alimento removido: " + alimento);

        if (diario.getRefeicoes().isEmpty()) {
            paciente.removerDiario(diario);
            System.out.println("Di�rio removido pois n�o h� mais alimentos.");
        }
    }
}
