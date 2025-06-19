#include <stdio.h>
#include <stdlib.h>

/*
    Descrição: Dado um conjunto de notas de alunos e consultas pedindo o k-ésimo maior valor, imprima o resultado.

    Objetivo: Ordenar o vetor de notas em ordem decrescente e responder às consultas.

    Dica: Ordene uma única vez e use o índice direto: notas[k - 1].
*/

void Ordena(int *, int);

void Ordena(int *array, int tam)
{
    for (int i = tam - 1; i > 0; i--)
    {
        for (int j = 0; j < i; j++)
        {

            if (array[j] < array[j + 1])
            {
                int tmp = array[j];
                array[j] = array[j + 1];
                array[j + 1] = tmp;
            }
        }
    }
}

int main()
{
    int index = 0;
    int cidadaos, consultas;

    scanf("%d", &cidadaos);

    int *arrayNotas = malloc(cidadaos * sizeof(int));

    scanf("%d", &consultas);

    for (int i = 0; i < cidadaos; i++)
    {
        scanf("%d", &arrayNotas[i]);
    }

    Ordena(arrayNotas, cidadaos);

    for (int i = 0; i < consultas; i++)
    {
        scanf("%d", &index);
        printf("%d\n", arrayNotas[index - 1]);
    }

    free(arrayNotas);
    return 0;
}