#include <stdio.h>
#include <string.h>
#include <ctype.h>

// Função recursiva
int ehPalindromo(char s[], int i, int j)
{
    if (i >= j)
        return 1;

    if (!isalpha((unsigned char)s[i]))
        return ehPalindromo(s, i + 1, j);
    if (!isalpha((unsigned char)s[j]))
        return ehPalindromo(s, i, j - 1);

    if ((unsigned char)s[i] != (unsigned char)s[j])
        return 0;

    return ehPalindromo(s, i + 1, j - 1);
}

int main()
{
    char linha[1000];

    scanf("%[^\n]", linha);
    while (strcmp(linha, "FIM") != 0)
    {
        scanf("%[^\n]", linha);
        linha[strcspn(linha, "\n")] = '\0';

        if (ehPalindromo(linha, 0, strlen(linha) - 1))
            printf("SIM\n");
        else
            printf("NAO\n");
    }

    return 0;
}
