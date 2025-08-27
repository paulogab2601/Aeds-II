package Java;

import java.util.Scanner;

public class tp_01_E01 {

    public static boolean isPalindromo(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    @SuppressWarnings("ConvertToTryWithResources") // Pro vsCode parar de encher o saco com os scanners

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String linha = sc.nextLine();
        while (!(linha.equals("FIM"))) {
            if (isPalindromo(linha)) {
                System.out.println("SIM");
            } else {
                System.out.println("NAO");
            }

            linha = sc.nextLine();
        }

        sc.close();
    }
}
