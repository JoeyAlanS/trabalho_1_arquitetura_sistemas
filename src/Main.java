import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        System.out.println("MENU DE LOGIN");
        System.out.println("1 - Nutricionista");
        System.out.println("2 - Usuario");
        int tipo = scanner.nextInt();

        if (tipo == 1) {
            System.out.println("Digite 1 para login e 2 para registro");
            int loginType = scanner.nextInt();
            if (loginType == 1) {
                System.out.println("Digite seu CRN para fazer login");
                int crn = scanner.nextInt();

            } else if (loginType == 2) {
                System.out.println("Digite seu CRN para se registrar");
                int crn = scanner.nextInt();
            } else {
                System.out.println("Insira uma opção válida");
            }

        } else if (tipo == 2) {
            System.out.println("Digite 1 para login e 2 para registro");
            int loginType = scanner.nextInt();
            if (loginType == 1) {
                System.out.println("Digite seu email para fazer login");
                System.out.println("Digite sua senha para fazer login");
            }
            if (loginType == 2) {
                System.out.println("Digite seu email para se registrar");
                System.out.println("Digite sua senha para se registrar");
            } else {
                System.out.println("Insira uma opção válida");
            }
        }

    }
}