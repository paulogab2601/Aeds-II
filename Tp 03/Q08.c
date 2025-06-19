#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <locale.h>


typedef struct {
    char show_id[256];
    char type[256];
    char title[256];
    char director[256];
    char cast[256];
    char country[256];
    char date_added[256];
    int release_year;
    char rating[256];
    char duration[256];
    char listed_in[256];
} Show;

typedef struct Node {
    Show show;
    struct Node *ant, *prox;
} Node;

Node *inicio = NULL, *fim = NULL;

void inserirFim(Show s) {
    Node *novo = malloc(sizeof(Node));
    novo->show = s;
    novo->prox = NULL;
    novo->ant = fim;

    if (fim) fim->prox = novo;
    else inicio = novo;

    fim = novo;
}

void lerShow(Show *s, char *linha) {
    char *campos[12];
    int campoIndex = 0;
    int entreAspas = 0;
    char *ptr = linha;
    char buffer[256];
    int bufIndex = 0;

    while (*ptr != '\0' && campoIndex < 12) {
        if (*ptr == '"') entreAspas = !entreAspas;
        else if (*ptr == ',' && !entreAspas) {
            buffer[bufIndex] = '\0';
            campos[campoIndex++] = strdup(buffer);
            bufIndex = 0;
        } else {
            buffer[bufIndex++] = *ptr;
        }
        ptr++;
    }
    buffer[bufIndex] = '\0';
    campos[campoIndex] = strdup(buffer);

    strcpy(s->show_id, strlen(campos[0]) ? campos[0] : "NaN");
    strcpy(s->type, strlen(campos[1]) ? campos[1] : "NaN");
    strcpy(s->title, strlen(campos[2]) ? campos[2] : "NaN");
    strcpy(s->director, strlen(campos[3]) ? campos[3] : "NaN");
    strcpy(s->cast, strlen(campos[4]) ? campos[4] : "NaN");
    strcpy(s->country, strlen(campos[5]) ? campos[5] : "NaN");
    strcpy(s->date_added, strlen(campos[6]) ? campos[6] : "NaN");
    s->release_year = strlen(campos[7]) ? atoi(campos[7]) : -1;
    strcpy(s->rating, strlen(campos[8]) ? campos[8] : "NaN");
    strcpy(s->duration, strlen(campos[9]) ? campos[9] : "NaN");
    strcpy(s->listed_in, strlen(campos[10]) ? campos[10] : "NaN");

    for (int j = 0; j <= campoIndex; j++) {
        free(campos[j]);
    }
}

void ordenarCast(char *cast) {
    if (strcmp(cast, "NaN") == 0) return;

    char nomes[1500][256];
    int qtd = 0;
    char *token = strtok(cast, ",");

    while (token != NULL && qtd < 1500) {
        while (*token == ' ') token++;
        strncpy(nomes[qtd++], token, 256);
        token = strtok(NULL, ",");
    }

    for (int i = 0; i < qtd - 1; i++) {
        for (int j = i + 1; j < qtd; j++) {
            if (strcmp(nomes[i], nomes[j]) > 0) {
                char temp[256];
                strcpy(temp, nomes[i]);
                strcpy(nomes[i], nomes[j]);
                strcpy(nomes[j], temp);
            }
        }
    }

    cast[0] = '\0';
    for (int i = 0; i < qtd; i++) {
        strcat(cast, nomes[i]);
        if (i < qtd - 1) strcat(cast, ", ");
    }
}

void imprimirShow(Show *s) {
    char castOrdenado[256];
    strcpy(castOrdenado, s->cast);
    ordenarCast(castOrdenado);

    printf("=> %s ## %s ## %s ## %s ## [%s] ## %s ## %s ## %d ## %s ## %s ## [%s] ##\n",
           s->show_id, s->title, s->type, s->director, castOrdenado, s->country,
           s->date_added, s->release_year, s->rating, s->duration, s->listed_in);
}

time_t parseDate(const char *data) {
    if (strcmp(data, "NaN") == 0) return 0;

    struct tm tm = {0};
    setlocale(LC_TIME, "C");
    if (strptime(data, "%B %d, %Y", &tm) == NULL) return 0;
    return mktime(&tm);
}

int compararShows(const Show *a, const Show *b) {
    time_t da = parseDate(a->date_added);
    time_t db = parseDate(b->date_added);

    if (da != db)
        return (da < db) ? -1 : 1;

    return strcasecmp(a->title, b->title);
}

void trocar(Show *a, Show *b) {
    Show tmp = *a;
    *a = *b;
    *b = tmp;
}

void quickSort(Show arr[], int esq, int dir, int *comparacoes, int *movimentacoes) {
    if (esq < dir) {
        Show pivo = arr[dir];
        int i = esq - 1;

        for (int j = esq; j < dir; j++) {
            (*comparacoes)++;
            if (compararShows(&arr[j], &pivo) <= 0) {
                i++;
                trocar(&arr[i], &arr[j]);
                (*movimentacoes) += 3;
            }
        }

        trocar(&arr[i + 1], &arr[dir]);
        (*movimentacoes) += 3;

        int p = i + 1;
        quickSort(arr, esq, p - 1, comparacoes, movimentacoes);
        quickSort(arr, p + 1, dir, comparacoes, movimentacoes);
    }
}

int pesquisaBinaria(Show arr[], int n, char *titulo, int *comparacoes) {
    int esq = 0, dir = n - 1;
    while (esq <= dir) {
        int meio = (esq + dir) / 2;
        (*comparacoes)++;
        int cmp = strcmp(arr[meio].title, titulo);
        if (cmp == 0) return meio;
        else if (cmp < 0) esq = meio + 1;
        else dir = meio - 1;
    }
    return -1;
}

int listaParaArray(Show arr[]) {
    int i = 0;
    for (Node *p = inicio; p != NULL; p = p->prox) {
        arr[i++] = p->show;
    }
    return i;
}

int main() {
    FILE *arquivo = fopen("/tmp/disneyplus.csv", "r");
    if (!arquivo) {
        perror("Erro ao abrir o arquivo CSV");
        return 1;
    }

    Show shows[256];
    int totalShows = 0;
    char linha[4096];

    fgets(linha, sizeof(linha), arquivo);
    while (fgets(linha, sizeof(linha), arquivo) && totalShows < 256) {
        linha[strcspn(linha, "\n")] = 0;
        lerShow(&shows[totalShows], linha);
        totalShows++;
    }
    fclose(arquivo);

    char entrada[256];
    while (fgets(entrada, sizeof(entrada), stdin)) {
        entrada[strcspn(entrada, "\n")] = 0;
        if (strcmp(entrada, "FIM") == 0) break;

        for (int i = 0; i < totalShows; i++) {
            if (strcmp(shows[i].show_id, entrada) == 0) {
                inserirFim(shows[i]);
                break;
            }
        }
    }

    Show lista[256];
    int tamLista = listaParaArray(lista);

    int comparacoes = 0, movimentacoes = 0;
    clock_t inicioTempo = clock();

    quickSort(lista, 0, tamLista - 1, &comparacoes, &movimentacoes);

    clock_t fimTempo = clock();
    double tempoExecucao = (double)(fimTempo - inicioTempo) / CLOCKS_PER_SEC * 1000.0;

    for (int i = 0; i < tamLista; i++) {
        imprimirShow(&lista[i]);
    }

    FILE *log = fopen("860144_quicksort2.txt", "w");
    if (log) {
        fprintf(log, "860144\t%d\t%d\t%.2lf\n", comparacoes, movimentacoes, tempoExecucao);
        fclose(log);
    }

    if (fgets(entrada, sizeof(entrada), stdin)) {
        entrada[strcspn(entrada, "\n")] = 0;
        int compBin = 0;
        pesquisaBinaria(lista, tamLista, entrada, &compBin);
    }

    return 0;
}
