#include <stdlib.h>
#include <stdio.h>

int main()
{

    FILE *file;

    int n = 0;
    double num = 0;

    scanf("%d", &n);

    file = fopen("Tp01Q14.txt", "w"); // abre o arquivo

    for (int i = 0; i < n; i++)
    {
        scanf("%lf", &num);
        fprintf(file, "%lf\n", num); // escreve os numeros lidos no arquivo
    }

    fclose(file); // fecha

    // Até aqui esta OK

    file = fopen("Tp01Q14.txt", "r"); // abre dnv

    for (int i = n - 1; i >= 0; i--) // lê na ordem inversa
    {
        fseek(file, 0, SEEK_SET); // move o ponteiro do arquivo
        for (int j = 0; j <= i; j++)
        {
            fscanf(file, "%lf", &num);
        }
        printf("%g\n", num); //G imprime sem os 0 do ponto flutuante
    }

    fclose(file);

    return 0;
}