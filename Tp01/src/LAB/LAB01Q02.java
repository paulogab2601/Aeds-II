package LAB;

import java.util.Scanner;

public class LAB01Q02 {

    public static int Maiuscula(String str, int n, int tam) {

        if (n == tam) {
            return 0;
        }

        int count = (str.charAt(n) >= 65 && str.charAt(n) <= 90) ? 1 : 0;

        return count + Maiuscula(str, n + 1, tam);

    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {

            String str = sc.nextLine();
            if (!str.equals("FIM")) {
                int n = Maiuscula(str, 0, str.length());
                System.out.println(n);
            }

        }
        sc.close();
    }

}
