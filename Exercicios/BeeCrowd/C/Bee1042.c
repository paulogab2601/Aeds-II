#include <stdio.h>
#include <stdlib.h>

/*
       Descrição: Leia três valores inteiros e os apresente em ordem crescente. Depois imprima os valores na ordem original.

       Objetivo: Implementar um algoritmo de ordenação simples (como Bubble Sort ou Insertion Sort) para ordenar os três valores.

       Dica: Use um vetor de 3 posições, ordene e imprima.
   */

int *Ordena(int *array);

int *Ordena(int *array)
{
    int *arrayOriginal = malloc(3 * sizeof(int));
    for (int i = 0; i < 3; i++)
    {
        arrayOriginal[i] = array[i];
    }

    int n = 3;
    for (int i = n - 1; i > 0; i--)
    {
        for (int j = 0; j < i; j++)
        {
            if (array[j] > array[j + 1])
            {
                int tmp = array[j];
                array[j] = array[j + 1];
                array[j + 1] = tmp;
            }
        }
    }

    printf("Array ordenado: ");

    for (int i = 0; i < 3; i++)
    {
        printf("%d ", array[i]);
    }

    return arrayOriginal;
}

int main()
{
    // Entrada
    int *array = malloc(3 * sizeof(int));

    for (int i = 0; i < 3; i++)
    {
        scanf("%d", &array[i]);
    }

    array = Ordena(array);

    printf("\nArray original: ");

    for (int i = 0; i < 3; i++)
    {
        printf("%d ", array[i]);
    }

    free(array);
    return 0;
}