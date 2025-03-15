import java.util.Scanner;

public class Tp01Q07 {

    public static String inverteString(String str)// Usa um for invertido para preencher uma nova string com os mesmos
                                                  // caracteres, só que invertida
    {

        String modStr = "";

        for (int i = str.length() - 1; i >= 0; i--) {
            modStr += str.charAt(i);
        }

        return modStr;
    }

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

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String str = sc.nextLine();

        while (!FIM(str)) {
            str = inverteString(str);
            System.out.printf("%s \n", str);
            str = sc.nextLine();
        }
        sc.close();

    }

}
