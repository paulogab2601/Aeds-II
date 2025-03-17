import java.util.Scanner;

public class Tp01Q20 {

    public static boolean FIM(String str) // Encerra o programa, (OBS: Coloquei essa função em todas questoes de string, por isso nao expliquei)
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

    public static boolean ehVogal(String str) {
        return ehVogalRec(str, 0);
    }

    private static boolean ehVogalRec(String str, int index) {
        if (index == str.length()) {
            return true; // se chegar no final da frase/palavra é true
        }
        char tempC = str.charAt(index);
        if (!(tempC == 'a' || tempC == 'e' || tempC == 'i' || tempC == 'o' || tempC == 'u' ||
              tempC == 'A' || tempC == 'E' || tempC == 'I' || tempC == 'O' || tempC == 'U')) {
            return false; // se achar uma letra que não é vogal, retorna false
        }
        return ehVogalRec(str, index + 1);
    }

    public static boolean ehConsoante(String str) {
        return ehConsoanteRec(str, 0);
    }

    private static boolean ehConsoanteRec(String str, int index) {
        if (index == str.length()) {
            return true; // mesmo esquema das vogais, mas com consoante
        }
        char tempC = str.charAt(index);
        if ((tempC == 'a' || tempC == 'e' || tempC == 'i' || tempC == 'o' || tempC == 'u' ||
             tempC == 'A' || tempC == 'E' || tempC == 'I' || tempC == 'O' || tempC == 'U' ||
             (tempC >= '0' && tempC <= '9'))) {
            return false; // se achar algo q nao é consoante, retorna false
        }
        return ehConsoanteRec(str, index + 1);
    }

    public static boolean ehInteiro(String str) {
        return ehInteiroRec(str, 0, 0);
    }

    private static boolean ehInteiroRec(String str, int index, int count) {
        if (index == str.length()) {
            return count <= 1; // se não houver mais de um ponto ou vírgula 
        }
        char tempC = str.charAt(index);
        if (tempC == ',' || tempC == '.') {
            count++;
        }
        if ((tempC < '0' || tempC > '9') && tempC != ',' && tempC != '.') {
            return false; // se não for numero, virgula ou ponto, retorna false
        }
        return ehInteiroRec(str, index + 1, count); //recursividade
    }

    public static boolean ehReal(String str) {
        return ehRealRec(str, 0, 0);
    }

    private static boolean ehRealRec(String str, int index, int count) {
        if (index == str.length()) {
            return count <= 1; // se não houver mais de um ponto ou vírgula
        }
        char tempC = str.charAt(index);
        if (tempC == ',' || tempC == '.') {
            count++;
        }
        if ((tempC < '0' || tempC > '9') && tempC != ',' && tempC != '.') {
            return false; // se não for dígito, vírgula ou ponto, retorna false
        }
        return ehRealRec(str, index + 1, count); // Chamada recursiva
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        while (!FIM(str)) {
            System.out.printf("%s ", ehVogal(str) ? "SIM" : "NAO");
            System.out.printf("%s ", ehConsoante(str) ? "SIM" : "NAO");
            System.out.printf("%s ", ehInteiro(str) ? "SIM" : "NAO");
            System.out.println(ehReal(str) ? "SIM" : "NAO");

            str = sc.nextLine();
        }
        sc.close();
    }
}
