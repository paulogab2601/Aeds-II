package Provas;

public class Q1Prova {
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n / 2; i++) { // Percorre até a metade, pois duas trocas são feitas por vez
            int minIndex = i;
            int maxIndex = n - i - 1;

            for (int j = i; j <= n - i - 1; j++) { // Garante que buscamos apenas nos não ordenados
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
                if (arr[j] > arr[maxIndex]) {
                    maxIndex = j;
                }
            }

            // Troca o menor elemento com a posição inicial
            if (minIndex != i) {
                int temp = arr[minIndex];
                arr[minIndex] = arr[i];
                arr[i] = temp;
            }

            // Se a troca do menor afetou a posição do maior, corrigimos maxIndex
            if (maxIndex == i) {
                maxIndex = minIndex;
            }

            // Troca o maior elemento com a posição final
            if (maxIndex != n - i - 1) {
                int temp = arr[maxIndex];
                arr[maxIndex] = arr[n - i - 1];
                arr[n - i - 1] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {29, 10, 14, 37, 13, 5, 26};
        selectionSort(arr);
        System.out.println("Array ordenado:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}
//A complexidade continua sendo Theta de O (n^2), porém para entradas menores ele pode sim ser mais rápido