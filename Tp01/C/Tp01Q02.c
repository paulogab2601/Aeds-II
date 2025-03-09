#include <stdio.h>
#include <string.h>
#include <ctype.h>

//OBS: usei uma função chamada isapha para pegar somente os caracteres validos, � já que esse caractere estava travando meu progresso na questao

int palindromo(char *);

int palindromo(char *frase) //Usa dois ponteiros para dizer se é ou não um palindromo, se os ponteiros se cruzarem é um palindromo, se nao, nao é.
{
    int esq = 0;
    int dir = strlen(frase) - 1;

    while (esq < dir)
    {
        while (esq < dir && !isalpha(frase[esq])) // checa se e um caractere do alfabeto
        {
            esq++;
        }
        while (esq < dir && !isalpha(frase[dir]))
        {
            dir--;
        }

        // Compra os caractere
        if (frase[esq] != frase[dir])
        {
            return 0; // Retorna false se nao for palindromo
        }

        // Move os ponteiros
        esq++;
        dir--;
    }

    return 1; // Retorna true se for palindromo
}

int main()
{
    char linha[200];

    while (fgets(linha, sizeof(linha), stdin))
    {
        linha[strcspn(linha, "\n")] = 0;

        if (palindromo(linha))
        {
            printf("SIM\n");
        }
        else
        {
            printf("NAO\n");
        }
    }

    return 0;
}