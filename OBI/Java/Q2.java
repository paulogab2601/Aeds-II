package OBI.Java;

import java.util.Scanner;

public class Q2 {

    public static int calculaNota(int k, int[] candidatos) {

        bubbleSort(candidatos);

        return candidatos[k + 1];
    }

    public static void bubbleSort(int[] arr) // Ordena o vetor
    {
        int n = arr.length;
        boolean trocou;

        do {
            trocou = false;
            for (int i = 0; i < n - 1; i++) {
                if (arr[i] > arr[i + 1]) {
                    // Troca os elementos
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    trocou = true;
                }
            }
        } while (trocou);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {

            int n = sc.nextInt();
            int k = sc.nextInt();

            int[] arr = new int[n];

            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }
            n = calculaNota(k, arr);
            System.out.println(n);
        }
        sc.close();
    }
}
