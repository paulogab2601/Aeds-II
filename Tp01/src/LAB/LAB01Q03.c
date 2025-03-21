#include <stdlib.h>
#include <stdio.h>
#include <string.h>

int numMaiuscula(char *str)
{

    int n = 0;
    int tam = (int)strlen(str);

    for (int i = 0; i < tam; i++)
    {
        if (str[i] >= 65 && str[i] <= 90)
        {
            n++;
        }
    }
    return n;
}

int main()
{
    char str[100];

    while (fgets(str, 100, stdin))
    {
        str[strcspn(str, "\n")] = '\0'; // tira o \n

        if (strcmp(str, "FIM") == 0)
        {
            break;
        }
        int n = numMaiuscula(str);

        printf("%d\n", n);
    }
    return 0;
}