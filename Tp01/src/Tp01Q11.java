import java.util.Scanner;

public class Tp01Q11 {

    public static int StringRep(String str) {
        int n = str.length();
        int maior = 0;
        int inicio = 0;
        
        //marca os caracteres visitados
        boolean[] passou = new boolean[256]; //todos ASCII
        for (int i = 0; i < n; i++) {
            while (passou[str.charAt(i)]) {
                passou[str.charAt(inicio)] = false;
                inicio++;
            }

            // Marca o caractere atual como ja visto antes
            passou[str.charAt(i)] = true;

            // Atualiza a maior substring sem repeticao
            maior = Math.max(maior, i - inicio + 1); //Classe math funciona igual um math.h no C, MATH.max retorna o maior numero entre os parametros
        }

        return maior;
    }

    public static boolean FIM(String str)// Função de parada
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

        int maior = StringRep(str);

        while (!FIM(str)) {
            System.out.println(maior);
            str = sc.nextLine();
            maior = StringRep(str);
        }
        sc.close();
    }

}
