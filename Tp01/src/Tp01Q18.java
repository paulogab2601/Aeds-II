import java.util.Scanner;

public class Tp01Q18 {

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

    public static String criptografar(String palavra) {
        // Chama a função recursiva com a palavra e o índice inicial
        return criptografarRec(palavra, 0);
    }

    private static String criptografarRec(String palavra, int index) {
        // Caso base: se o index = tam da string, retorna uma string vazia (finalmente funionou)
        if (index == palavra.length()) {
            return "";
        }

        //desloca o caractere atual
        char letra = palavra.charAt(index);
        char letraCifrada;

        // verifica se é minusculo
        if (letra >= 'a' && letra <= 'z') {
            letraCifrada = (char) ((letra - 'a' + 3) % 26 + 'a'); // Cifra a letra
        } 
        // verifica se é maiusculo
        else if (letra >= 'A' && letra <= 'Z') {
            letraCifrada = (char) ((letra - 'A' + 3) % 26 + 'A'); // Cifra a letra
        } 
        // se nao for letra, mantem o caractere
        else {
            letraCifrada = letra;
        }

        // letra cifrada + a proxima chamada recursiva para o próximo índice
        return letraCifrada + criptografarRec(palavra, index + 1);
    }

    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in)
        String palavra = sc.nextLine();

        while (!FIM(palavra)) {

            if (FIM(palavra))//verifica e fecha o programa quando a palavra for FIM
             {
                System.exit(0);
            } else {
                palavra = criptografar(palavra);
                System.out.println(palavra);

                palavra = sc.nextLine();
            }
        }
    }
}