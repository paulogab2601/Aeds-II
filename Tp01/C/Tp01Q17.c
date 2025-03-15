#include <stdio.h>
#include <string.h>
#include <locale.h>


int PalindromoRecursivo(int a, int b, char *str)
{

    if (a >= b)
    {
        return 1;
    }
    if (str[a] != str[b])
    {
        return 0;
    }

    return PalindromoRecursivo(a + 1, b - 1, str);
}

int FIM(char *str)
{
    char FIM[] = "FIM";

    if (strlen(str) != strlen(FIM))
    {
        return 0; // false
    }

    for (int i = 0; i < strlen(str); i++)
    {
        if (str[i] != FIM[i])
        {
            return 0; // false
        }
    }

    return 1; // true
}

int main()
{
    setlocale(LC_ALL, "pt_BR.UTF-8");

    char str[256];

    fgets(str, 256, stdin);
    str[strcspn(str, "\n")] = '\0'; // Remove o \n no final da entrada

    PalindromoRecursivo(0, strlen(str) - 1, str);

    while (!FIM(str))
    {
        if (PalindromoRecursivo(0, strlen(str) - 1, str))
        {
            printf("SIM\n");
        }
        else
        {
            printf("NAO\n");
        }

        fgets(str, 256, stdin);
        str[strcspn(str, "\n")] = '\0'; // Remove o \n no final da entrada

        PalindromoRecursivo(0, strlen(str) - 1, str);
    }

    return 0;
}