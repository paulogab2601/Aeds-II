#include <stdio.h>
#include <stdlib.h>

#define TAM 5

void enfileirar(int fila[], int *inicio, int *fim, int *quantidade, int valor);
int desenfileirar(int fila[], int *inicio, int *fim, int *quantidade);
void imprimirFila(int fila[], int inicio, int quantidade);

void enfileirar(int fila[], int *inicio, int *fim, int *quantidade, int valor)
{

    if (*quantidade == TAM)
    {
        printf("Fila cheia!\n");
        return;
    } // Se a fila estiver cheia, sai da função

    fila[*fim] = valor; // atribui o novo valor ao fim da fila
    *fim = (*fim + 1) % TAM;
    (*quantidade)++; // Aumenta 1 na quantidade de elementos da fila
}

int desenfileirar(int fila[], int *inicio, int *fim, int *quantidade)
{

    // Verifica se a fila esta vazia
    if (*quantidade == 0)
    {
        printf("Fila vazia!\n");
        return -1;
    }

    // Na fila os elementos só podem ser removidos no inicio
    int num = fila[*inicio];
    *inicio = (*inicio + 1) % TAM;
    (*quantidade)--; // Decrementa 1 a quantidade da fila
    return num;
}

void imprimirFila(int fila[], int inicio, int quantidade)
{

    if (quantidade == 0)
    {
        printf("Fila vazia");
        return;
    }

    for (int i = 0; i < quantidade; i++)
    {
        int index = (inicio + i) % TAM; // O index serve para imprimir a posicao correta na fila
        printf(" %d ", fila[index]);
    }
    printf("\n");
}