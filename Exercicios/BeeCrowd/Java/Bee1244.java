package BeeCrowd.Java;

/*Crie um programa para ordenar um conjunto de strings pelo seu tamanho. O programa deve receber um conjunto de strings e retornar esse mesmo conjunto de palavras ordenadas pelo tamanho. Se os tamanhos das strings forem iguais, deve-se manter a ordem original do conjunto.

Entrada
A primeira linha da entrada contém um número inteiro único N que indica o número de conjuntos de strings. Cada conjunto pode conter entre 1 e 50 elementos, e cada uma das strings do conjunto pode conter entre 1 e 50 caracteres.

Saída
A saída deve conter o conjunto de strings de entrada ordenado pelo comprimento das palavras. Um espaço em branco deve ser impresso entre duas palavras. */

import java.util.Scanner;

public class Bee1244 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int numFrases = sc.nextInt();
        sc.nextLine(); // Consome a quebra de linha pendente

        String[] frases = new String[numFrases];

        for (int i = 0; i < numFrases; i++) {
            frases[i] = sc.nextLine();
        }

        for (int i = 0; i < numFrases; i++) {
            String[] frase = frases[i].split("\\s+");

            // Ordenação por tamanho decrescente
            for (int j = frase.length - 1; j > 0; j--) {
                for (int k = 0; k < j; k++) {
                    if (frase[k].length() < frase[k + 1].length()) {
                        String tmp = frase[k];
                        frase[k] = frase[k + 1];
                        frase[k + 1] = tmp;
                    }
                }
            }

            // Impressão sem espaço extra no final
            for (int j = 0; j < frase.length; j++) {
                System.out.print(frase[j]);
                if (j < frase.length - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }

        sc.close();
    }
}
