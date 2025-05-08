public class Ordenacoes {

    // Bubble Sort:
    public static void Bubble(int arr[]) {
        int n = arr.length; // Pega o tamanho do array
        boolean trocou = false; // Variavel para otimização

        // Primeiro for, percorre até arr.length - 1, para garantir a ordenação de todo
        // o vetor
        for (int i = 0; i < n - 1; i++) {
            trocou = false;

            // Segundo for, passa da posicao 0 até arr.length-1 ordenando o vetor
            for (int j = 0; j < n - 1; j++) {
                // Se o numero imediatamente anterior a j+1 for menor, faz a troca
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];

                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    trocou = true;
                }
            }
            if (!trocou) // If para otimização de codigo
                break;
        }
    }

    // Selection Sort:
    public static void Selection(int arr[]) {
        int n = arr.length; // Pega o tamanho do vetor

        // Define por padrao o menor elemento como sendo o 1° e faz a seleção dentro do
        // 2° for
        for (int i = 0; i < n - 1; i++) {
            int minIndice = i;

            // Faz a seleção, j começa na segunda posicao do array até a última
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndice]) {
                    minIndice = j;
                }
            }

            // Após o 2° for ter feito a seleção do menor, faz a troca de posiçôes
            int tmp = arr[i];
            arr[i] = arr[minIndice];
            arr[minIndice] = tmp;

        }

    }

    // Inserction Sort:
    public static void Inserction(int arr[]) {
        int n = arr.length; // Define o tamanho do array

        for (int i = 1; i < n; i++) {
            int chave = arr[i]; // a chave começa sendo o 2° elemento do array
            int j = i - 1; // j começa na 1° posicao do array

            // j>=0 é para evitar vazamentos de memoria, com o array acessando pontos
            // invalidos, se a chave for menor que arr[j] faz a troca dos elementos
            while (j >= 0 && arr[j] > chave) {
                arr[j + 1] = arr[j];
                j--;
            }

            // Insere a chave na posicao correta
            arr[j + 1] = chave;
        }
    }

    // Merge Sort:

    // Função principal do mergeSort
    public static void MergeSort(int[] arr, int inicio, int fim) {
        if (inicio < fim) {
            int meio = (inicio + fim) / 2; // acha o meio do vetor

            MergeSort(arr, inicio, meio); // passa a parte esquerda do vetor
            MergeSort(arr, meio + 1, fim); // passa a parte direita do vetor

            Merge(arr, inicio, meio, fim); // Chama o Merge para ordenar o vetor
        }
    }

    // Função auxiliar do merge

    public static void Merge(int[] arr, int inicio, int meio, int fim) {
        int n1 = meio - inicio + 1; // Define o tamanho do vetor a esquerda
        int n2 = fim - meio; // Define o tamanho do vetor a direita

        int[] esquerda = new int[n1];
        int[] direita = new int[n2];

        // Copia os dados temporariamente
        for (int i = 0; i < n1; i++)
            esquerda[i] = arr[inicio + i];
        for (int j = 0; j < n2; j++)
            direita[j] = arr[meio + 1 + j];

        // Mescla os arrays temporários
        int i = 0, j = 0, k = inicio;
        while (i < n1 && j < n2) {
            if (esquerda[i] <= direita[j]) {
                arr[k++] = esquerda[i++];
            } else {
                arr[k++] = direita[j++];
            }
        }

        // Copia elementos restantes (se houver)
        while (i < n1) {
            arr[k++] = esquerda[i++];
        }
        while (j < n2) {
            arr[k++] = direita[j++];
        }
    }


    //Quick Sort:

    public static void quickSort(int[] vetor, int inicio, int fim) {
        if (inicio < fim) {
            int indicePivo = particionar(vetor, inicio, fim);
            quickSort(vetor, inicio, indicePivo - 1);  // Lado esquerdo
            quickSort(vetor, indicePivo + 1, fim);     // Lado direito
        }
    }

    private static int particionar(int[] vetor, int inicio, int fim) {
        int pivo = vetor[fim];
        int i = inicio - 1;

        for (int j = inicio; j < fim; j++) {
            if (vetor[j] <= pivo) {
                i++;
                trocar(vetor, i, j);
            }
        }

        trocar(vetor, i + 1, fim);
        return i + 1;  // Nova posição do pivô
    }

    private static void trocar(int[] vetor, int i, int j) {
        int temp = vetor[i];
        vetor[i] = vetor[j];
        vetor[j] = temp;
    }

    // ============== MAIN ==============
    public static void main(String[] args) {

        int Bubblearr[] = { 1, 2, 5, 6, 0, 9, 1, 7, 1, 0 };
        int Selectionarr[] = { 1, 3, 5, 4, 6, 9, 1, 7, 4, 0 };
        int Inserctionarr[] = { 1, 7, 5, 1, 0, 3, 0, 8, 9, 0 };
        int Mergearr[] = { 1, 4, 6, 4, 0, 2, 6, 9, 1, 0 };

        System.out.println("Bubble sem ordenaçao: \n");

        for (int i = 0; i < Bubblearr.length; i++) {
            System.out.print(Bubblearr[i] + " ");
        }
        System.out.println("\n");

        System.out.println("Selection sem ordenaçao: \n");

        for (int i = 0; i < Selectionarr.length; i++) {
            System.out.print(Selectionarr[i] + " ");
        }
        System.out.println("\n");

        System.out.println("Inserction sem ordenaçao: \n");

        for (int i = 0; i < Inserctionarr.length; i++) {
            System.out.print(Inserctionarr[i] + " ");
        }
        System.out.println("\n");

        System.out.println("Merge sem ordenaçao: \n");

        for (int i = 0; i < Mergearr.length; i++) {
            System.out.print(Mergearr[i] + " ");
        }
        System.out.println("\n");

        // Chama os algoritmos de ordenação:
        Bubble(Bubblearr);
        Selection(Selectionarr);
        Inserction(Inserctionarr);
        MergeSort(Mergearr, 0, Mergearr.length - 1);

        System.out.println("Bubble com ordenaçao: \n");

        for (int i = 0; i < Bubblearr.length; i++) {
            System.out.print(Bubblearr[i] + " ");
        }
        System.out.println("\n");

        System.out.println("Selection com ordenaçao: \n");

        for (int i = 0; i < Selectionarr.length; i++) {
            System.out.print(Selectionarr[i] + " ");
        }
        System.out.println("\n");

        System.out.println("Inserction com ordenaçao: \n");

        for (int i = 0; i < Inserctionarr.length; i++) {
            System.out.print(Inserctionarr[i] + " ");
        }
        System.out.println("\n");

        System.out.println("Merge com ordenaçao: \n");

        for (int i = 0; i < Mergearr.length; i++) {
            System.out.print(Mergearr[i] + " ");
        }
        System.out.println("\n");

    }
}