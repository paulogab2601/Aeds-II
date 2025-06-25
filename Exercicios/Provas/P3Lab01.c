#include <stdio.h>
#include <string.h>

int main()
{
    int N; // número de casos de teste
    scanf("%d", &N);

    for (int t = 0; t < N; t++)
    {
        int L; // número de linhas do caso de teste
        scanf("%d", &L);

        int hash = 0;
        char linha[51]; // até 50 caracteres + '\0'

        for (int i = 0; i < L; i++)
        {
            scanf("%s", linha);
            int len = strlen(linha);

            for (int j = 0; j < len; j++)
            {
                int posAlfabeto = linha[j] - 'A'; // posição no alfabeto
                hash += posAlfabeto + i + j;      // fórmula do valor
            }
        }

        printf("%d\n", hash);
    }

    return 0;
}
