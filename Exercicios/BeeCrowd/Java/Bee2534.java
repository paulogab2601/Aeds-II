package BeeCrowd.Java;

import java.util.Scanner;

/* 
    Descrição: Dado um conjunto de notas de alunos e consultas pedindo o k-ésimo maior valor, imprima o resultado.

    Objetivo: Ordenar o vetor de notas em ordem decrescente e responder às consultas.

    Dica: Ordene uma única vez e use o índice direto: notas[k - 1].
*/

public class Bee2534 {

    public static int[] Ordena(int[] array) {
        int n = array.length;
        int[] ArrayOrdenado = new int[n];

        for (int i = n - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {

                if (array[j] < array[j + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
        }
        ArrayOrdenado = array;

        return ArrayOrdenado;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int Index = 0;

        int cidadaos = sc.nextInt();
        int consultas = sc.nextInt();

        int[] arrayNotas = new int[cidadaos];

        for(int i = 0; i < cidadaos; i++){
            arrayNotas[i] = sc.nextInt();
        }

        arrayNotas = Ordena(arrayNotas);

        for(int i = 0; i < consultas;i++){
            Index = sc.nextInt();
            System.err.println(arrayNotas[Index-1]);
        }

        sc.close();
    }
}