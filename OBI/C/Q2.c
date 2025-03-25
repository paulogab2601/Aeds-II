#include <stdio.h>
#include <stdlib.h>

int *bubbleSort(int candidatos[], int n)
{
    int boolean = 0;
    int* candidatosOrdenado = malloc(n * sizeof(int));

    do
    {
        boolean = 0;

        for (int i = 0; i < n - 1; i++)
        {
            if (candidatos[i] > candidatos[i + 1])
            {
                int temp = candidatos[i];
                candidatos[i] = candidatos[i + 1];
                candidatos[i + 1] = temp;

                boolean = 1;
            }
        }

    } while (boolean);

    candidatosOrdenado = candidatos;

    return candidatosOrdenado;
}

int calculaNota(int k, int n, int candidatos[n])
{
    candidatos = bubbleSort(candidatos, n);

    return candidatos[n - k];
}

int main()
{
    int n = 0;

    while (scanf("%d", &n) != EOF)
    {
        int k;
        scanf("%d", &k);
        int candidatos[n];
        for (int i = 0; i < n; i++)
        {
            scanf("%d", &candidatos[i]);
        }
        k = calculaNota(k, n, candidatos);
        printf("%d\n", k);
    }
}