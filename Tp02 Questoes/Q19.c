#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define TAMANHOS 4

void copiarVetor(int *origem, int *destino, int n) {
    for (int i = 0; i < n; i++) destino[i] = origem[i];
}

void gerarVetorAleatorio(int *vetor, int n) {
    for (int i = 0; i < n; i++)
        vetor[i] = rand() % (n * 10);
}

// Algoritmos
void bubbleSort(int *vet, int n, long long *comp, long long *mov) {
    for (int i = 0; i < n - 1; i++) {
        for (int j = 0; j < n - i - 1; j++) {
            (*comp)++;
            if (vet[j] > vet[j + 1]) {
                int temp = vet[j];
                vet[j] = vet[j + 1];
                vet[j + 1] = temp;
                (*mov) += 3;
            }
        }
    }
}

void selectionSort(int *vet, int n, long long *comp, long long *mov) {
    for (int i = 0; i < n - 1; i++) {
        int min = i;
        for (int j = i + 1; j < n; j++) {
            (*comp)++;
            if (vet[j] < vet[min]) {
                min = j;
            }
        }
        if (min != i) {
            int temp = vet[i];
            vet[i] = vet[min];
            vet[min] = temp;
            (*mov) += 3;
        }
    }
}

void insertionSort(int *vet, int n, long long *comp, long long *mov) {
    for (int i = 1; i < n; i++) {
        int chave = vet[i];
        int j = i - 1;
        (*mov)++;
        while (j >= 0 && vet[j] > chave) {
            (*comp)++;
            vet[j + 1] = vet[j];
            (*mov)++;
            j--;
        }
        (*comp)++; // comparação que falhou
        vet[j + 1] = chave;
        (*mov)++;
    }
}

void quickSort(int *vet, int inicio, int fim, long long *comp, long long *mov) {
    if (inicio < fim) {
        int i = inicio, j = fim, pivo = vet[(inicio + fim) / 2];
        while (i <= j) {
            while (vet[i] < pivo) {
                i++;
                (*comp)++;
            }
            (*comp)++;
            while (vet[j] > pivo) {
                j--;
                (*comp)++;
            }
            (*comp)++;
            if (i <= j) {
                int temp = vet[i];
                vet[i] = vet[j];
                vet[j] = temp;
                (*mov) += 3;
                i++;
                j--;
            }
        }
        quickSort(vet, inicio, j, comp, mov);
        quickSort(vet, i, fim, comp, mov);
    }
}

int main() {
    srand(time(NULL));
    int tamanhos[TAMANHOS] = {100, 1000, 10000, 100000};

    FILE *f = fopen("resultados.csv", "w");
    if (!f) {
        perror("Erro ao abrir o arquivo");
        return 1;
    }

    fprintf(f, "Algoritmo,Tamanho,Tempo(ms),Comparacoes,Movimentacoes\n");

    for (int i = 0; i < TAMANHOS; i++) {
        int n = tamanhos[i];
        int *original = malloc(n * sizeof(int));
        gerarVetorAleatorio(original, n);

        int *v = malloc(n * sizeof(int));
        long long comp, mov;
        clock_t ini, fim;
        double tempo;

        copiarVetor(original, v, n);
        comp = mov = 0;
        ini = clock();
        bubbleSort(v, n, &comp, &mov);
        fim = clock();
        tempo = ((double)(fim - ini)) / CLOCKS_PER_SEC * 1000.0;
        fprintf(f, "Bubble,%d,%.2f,%lld,%lld\n", n, tempo, comp, mov);

        copiarVetor(original, v, n);
        comp = mov = 0;
        ini = clock();
        selectionSort(v, n, &comp, &mov);
        fim = clock();
        tempo = ((double)(fim - ini)) / CLOCKS_PER_SEC * 1000.0;
        fprintf(f, "Selection,%d,%.2f,%lld,%lld\n", n, tempo, comp, mov);

        copiarVetor(original, v, n);
        comp = mov = 0;
        ini = clock();
        insertionSort(v, n, &comp, &mov);
        fim = clock();
        tempo = ((double)(fim - ini)) / CLOCKS_PER_SEC * 1000.0;
        fprintf(f, "Insertion,%d,%.2f,%lld,%lld\n", n, tempo, comp, mov);

        copiarVetor(original, v, n);
        comp = mov = 0;
        ini = clock();
        quickSort(v, 0, n - 1, &comp, &mov);
        fim = clock();
        tempo = ((double)(fim - ini)) / CLOCKS_PER_SEC * 1000.0;
        fprintf(f, "QuickSort,%d,%.2f,%lld,%lld\n", n, tempo, comp, mov);

        free(original);
        free(v);
    }

    fclose(f);
    printf("Resultados salvos em 'resultados.csv'\n");
    return 0;
}
