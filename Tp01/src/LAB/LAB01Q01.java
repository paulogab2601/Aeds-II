package LAB;

import java.util.Scanner;

public class LAB01Q01 {

    public static int Maiuscula(String str) {

        int n = 0;

        for (int i = 0; i < str.length(); i++) {

            if (str.charAt(i) >= 65 && str.charAt(i) <= 90) {
                n++;
            }
        }

        return n;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String FIM = "FIM";
        while (sc.hasNext()) {
            String str = sc.nextLine();

            if (!str.equals(FIM)) {
                int n = Maiuscula(str);
                System.out.println(n);
            }
        }
        sc.close();
    }

}