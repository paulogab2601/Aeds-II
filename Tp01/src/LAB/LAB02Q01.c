#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char* combinador(char *str) {
    char* newStr = malloc(200 * sizeof(char)); //Aloca mem

    char *str1 = strtok(str, " "); // Separa a string
    char *str2 = strtok(NULL, " ");

    if (!str1 || !str2) return NULL; 

    int t1 = (int) strlen(str1); // pega o tamanho das strings
    int t2 = (int) strlen(str2);

    int pos = 0;
    for (int i = 0; i < t1 || i < t2; i++) {
        if (i < t1) newStr[pos++] = str1[i];
        if (i < t2) newStr[pos++] = str2[i];
    }
    newStr[pos] = '\0'; // Finaliza a string

    return newStr;
}

int main() {
    char str[200];

    while (fgets(str, sizeof(str), stdin)) {
        str[strcspn(str, "\n")] = '\0'; // Remove o '\n'

        if (strcmp(str, "FIM") == 0)
        {
            break;
        }

        char* newString = combinador(str);
        if (newString) {
            printf("%s\n", newString);
            free(newString); // Libera memória alocada
        }
    }

    return 0;
}
