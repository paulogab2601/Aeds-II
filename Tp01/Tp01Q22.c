#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int somaDigitos(int num)
{

    if (num < 10)
    {
        return num;
    }

    return somaDigitos(num / 10) + num % 10;
}

int main()
{
    int num;
    char entrada[10] = ""; // Sempre inicializar a entrada antes do strcmp, para não dar erros

    scanf("%s", entrada);

    while (strcmp(entrada, "FIM") != 0)
    {
        num = atoi(entrada); //converte para int

        printf("%d\n", somaDigitos(num));
        scanf("%s", entrada);
    }

    return 0;
}