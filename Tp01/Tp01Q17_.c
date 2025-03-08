#include <stdio.h>
#include <string.h>
#include <ctype.h>

// OBS: Assim como na questao 2 uso os ponteiros para verificaçoã.
int palindromoRecursivo(char *, int, int);

int palindromoRecursivo(char *frase, int esq, int dir)
{
    while (esq < dir)
    {
        esq++;
    }
    while (esq < dir)
    {
        dir--;
    }

    if (esq >= dir)
    {
        return 1; // Retorna ture
    }

    if (frase[esq] != frase[dir])
    {
        return 0; // rerorna false
    }

    return palindromoRecursivo(frase, esq + 1, dir - 1);
}

int palindromo(char *frase)
{
    return palindromoRecursivo(frase, 0, strlen(frase) - 1);
}

int main()
{
    char linha[200];

    while (fgets(linha, sizeof(linha), stdin))
    {
        linha[strcspn(linha, "\n")] = 0;

        if (palindromo(linha)) // chama a função para fazer verificação
        {
            printf("SIM\n");
        }
        else
        {
            printf("NÃO\n");
        }
    }

    return 0;
}