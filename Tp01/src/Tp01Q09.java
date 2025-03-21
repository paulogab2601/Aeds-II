import java.util.Scanner;

public class Tp01Q09 {

    public static boolean Anagrama(String str) {

        str = str.toLowerCase(); //passa toda a string para minusculo

        int Isep = -1; //index do separador

        for (int i = 0; i < str.length(); i++) //procura o separador
         {
            if (str.charAt(i) == '-') {
                Isep = i;
                break;
            }
        }

        if (Isep == -1) {
            return false;
        }

        String str1 = "";
        String str2 = "";
        //reconstroi as strings
        for (int i = 0; i < Isep; i++) {
            str1 += str.charAt(i);
        }
        for (int i = Isep + 1; i < str.length(); i++) {
            str2 += str.charAt(i);
        }

        //verifica se as strings possuem tamanhos iguais
        if (str1.length() != str2.length()) {
            return false;
        }

        int[] contador = new int[256];

        for (int i = 0; i < str1.length(); i++) {
            contador[str1.charAt(i)]++;
            contador[str2.charAt(i)]--;
        }

        for (int i = 0; i < 256; i++) {
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

        while (!FIM(str)) {

            if (Anagrama(str)) {
                System.out.println("SIM");
            } else {
                System.out.println("N\u00c3O"); //Usei o unicode
            }
            str = sc.nextLine();
        }
        sc.close();
    }
}
