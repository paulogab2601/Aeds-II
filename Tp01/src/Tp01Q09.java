import java.util.Scanner;

public class Tp01Q09 {

    // Bubble Sort (aula do max no canvas)
    public static boolean Anagrama(String str1, String str2) {
        // strings de tamanho != nao podem ser anagramas
        if (str1.length() != str2.length()) {
            return false;
        }

        int[] contador = new int[256]; // todos ASCII

        for (int i = 0; i < str1.length(); i++) {
            contador[str1.charAt(i)]++; // avança o 1° ponteiro
            contador[str2.charAt(i)]--; // recua o 2° ponteiro
        }

        for (int i = 0; i < 256; i++)// Se for tudo 0, é um anagrama
        {
            if (contador[i] != 0) {
                return false;
            }
        }

        return true;
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
        String str2 = sc.nextLine();
        boolean resultado = Anagrama(str, str2);

        while (!FIM(str)) {
            if (resultado) {
                System.out.println("SIM");
            } else {
                System.out.println("NAO");
            }
            str = sc.nextLine();

            if (FIM(str)) {
               break;
            }
            str2 = sc.nextLine();
            resultado = Anagrama(str, str2);

        }

        sc.close();
    }
}
