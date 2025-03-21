#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int maiusculaRec(int n, int t, char *str)
{

    if (n == t)
        return 0;

    int count = (str[n] >= 65 && str[n] <= 90) ? 1 : 0;

    return count + maiusculaRec(n + 1, t, str);
}

int main()
{
    char str[101];

    while (fgets(str, sizeof(str), stdin))
    {
        str[strcspn(str, "\n")] = '\0'; // tira o \n
        
        if (strcmp(str, "FIM") == 0)
        {
            break;
        }
        
        int n = maiusculaRec(0, strlen(str), str);

        printf("%d\n", n);
    }

    return 0;
}