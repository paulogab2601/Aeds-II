package Java;

import java.util.Random;
import java.util.Scanner;

@SuppressWarnings("ConvertToTryWithResources") // Pro vsCode parar de encher o saco com os scanners

public class tp_01_E04 {
    public static String substituirLetraAleatoria(String input, Random gerador) {
        char letra1 = (char) ('a' + (Math.abs(gerador.nextInt()) % 26));
        char letra2 = (char) ('a' + (Math.abs(gerador.nextInt()) % 26));

        return input.replace(letra1, letra2);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random gerador = new Random();
        gerador.setSeed(4);

        while (sc.hasNextLine()) {
            String linha = sc.nextLine();
            if (linha.equals("FIM")) {
                break;
            }
            System.out.println(substituirLetraAleatoria(linha, gerador));
        }
        sc.close();
    }
}