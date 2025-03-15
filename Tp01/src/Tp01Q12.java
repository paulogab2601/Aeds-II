import java.util.Scanner;

public class Tp01Q12 {

    public static boolean FIM(String str) // Encerra o programa
    {

        String FIM = "FIM";

        if (str.length() != FIM.length()) {
            return false;
        }

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != FIM.charAt(i)) {
                return false;
            }
        }

        return true;
    }

    public static boolean verificaSenha(String str) {

        int maiuscula = 0;
        int minuscula = 0;
        int numero = 0;
        int especial = 0;

        if (str.length() < 8) {
            return false;
        }

        for (int i = 0; i < str.length(); i++) {
            char tempC = str.charAt(i);

            if (tempC > 47 && tempC < 58) {
                numero++;
            }
            if ((tempC >= 33 && tempC <= 47) || // Caracteres especiais entre 33 e 47
                    (tempC >= 58 && tempC <= 64) || // Caracteres especiais entre 58 e 64
                    (tempC >= 91 && tempC <= 96) || // Caracteres especiais entre 91 e 96
                    (tempC >= 123 && tempC <= 126)) { // Caracteres especiais entre 123 e 126
                especial++;
            }

            if (tempC > 65 && tempC < 90) {
                maiuscula++;
            }
            if (tempC > 97 && tempC < 122) {
                minuscula++;
            }
        }
        if (maiuscula == 0 || minuscula == 0 || numero == 0 || especial == 0) {
            return false;
        }

        return true;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        while (!FIM(str)) {
            if (verificaSenha(str)) {
                System.out.println("SIM");
            } else {
                System.out.println("NAO");
            }
            str = sc.nextLine();
        }
        sc.close();
    }

}
