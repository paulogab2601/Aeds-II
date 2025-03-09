#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char *inverte_string(char *, int, int);

char *inverte_string(char *str, int a, int b) // Função recursiva, usa 2 ponteiros inverter a string, quando os ponteiros se cruzarem a string estará invertida
{

    if (a >= b)
    {
        return str;
    }

    char temp = str[a];
    str[a] = str[b];
    str[b] = temp;

    return inverte_string(str, a + 1, b - 1);
}

int main()
{

    char str[50];

    scanf("%s", str);
    inverte_string(str, 0, strlen(str) - 1);

    while (strcmp(str, "MIF") != 0) // A palavra fim deve estar invertida para parar o loop, já que a proxima comparação ocorre com a string já invertida
    {
        printf("%s\n", str);
        scanf("%s", str);
        inverte_string(str, 0, strlen(str) - 1);
    }

    return 0;
}
