#include <stdio.h>

int calculaHash(int x, int tamTabela)
{
    return x % tamTabela;
}

int main()
{
    int n; // Número de casos de teste
    scanf("%d", &n);

    for (int t = 0; t < n; t++)
    {
        int m, c; // Tamanho da tabela e quantidade de chaves
        scanf("%d %d", &m, &c);

        // Criando estrutura para encadeamento externo
        int tabelaHash[m][200]; // até 200 chaves por posição
        int tamanhos[m];        // armazena o número de elementos por posição

        // Inicializando
        for (int i = 0; i < m; i++)
        {
            tamanhos[i] = 0;
        }

        // Inserção das chaves
        for (int i = 0; i < c; i++)
        {
            int valor;
            scanf("%d", &valor);
            int hash = calculaHash(valor, m);
            int pos = tamanhos[hash];
            tabelaHash[hash][pos] = valor;
            tamanhos[hash]++;
        }

        // Impressão do resultado
        for (int i = 0; i < m; i++)
        {
            printf("%d ->", i);
            for (int j = 0; j < tamanhos[i]; j++)
            {
                printf(" %d ->", tabelaHash[i][j]);
            }
            printf(" \\\n"); // barra invertida indica fim da linha
        }

        // Linha em branco entre casos de teste, exceto no último
        if (t < n - 1)
        {
            printf("\n");
        }
    }

    return 0;
}
