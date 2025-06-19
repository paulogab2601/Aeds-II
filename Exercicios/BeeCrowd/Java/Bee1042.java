package BeeCrowd.Java;

import java.util.Scanner;

/* 
    Descrição: Leia três valores inteiros e os apresente em ordem crescente. Depois imprima os valores na ordem original.

    Objetivo: Implementar um algoritmo de ordenação simples (como Bubble Sort ou Insertion Sort) para ordenar os três valores.

    Dica: Use um vetor de 3 posições, ordene e imprima.
*/

public class Bee1042 {

    // Ordenação simples por BubbleSort
    public static void Ordena(int[] array) {
        int n = array.length;

        for (int i = n - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) {

                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;

                }
            }
        }

        System.out.print("Array ordenado:");

        for (int i = 0; i < 3; i++) {
            System.out.printf("%d ", array[i]);
        }

    }

    public static void main(String[] args) {

        // Entrada:
        Scanner sc = new Scanner(System.in);
        int[] array = new int[3];

        for (int i = 0; i < 3; i++) {
            array[i] = sc.nextInt();
        }

        Ordena(array);

        System.out.print("Array original:");

        for (int i = 0; i < 3; i++) {
            System.out.printf("%d ", array[i]);
        }

        sc.close();
    }

}
