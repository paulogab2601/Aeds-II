#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

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

Show pilha[1500];
int topo = 0;

void empilhar(Show s) {
    if (topo < 1500) {
        pilha[topo++] = s;
    }
}

Show* desempilhar() {
    if (topo > 0) {
        return &pilha[--topo];
    }
    return NULL;
}

void substituirPorNaN(char *str) {
    if (str[0] == '\0') {
        strcpy(str, "NaN");
    }
}

void ordenarTokens(char *campo) {
    char *itens[30];
    int total = 0;
    char *token = strtok(campo, ",");

    while (token && total < 30) {
        while (*token == ' ') token++;
        itens[total++] = strdup(token);
        token = strtok(NULL, ",");
    }

    for (int i = 0; i < total - 1; i++) {
        for (int j = i + 1; j < total; j++) {
            if (strcmp(itens[i], itens[j]) > 0) {
                char *tmp = itens[i];
                itens[i] = itens[j];
                itens[j] = tmp;
            }
        }
    }

    campo[0] = '\0';
    for (int i = 0; i < total; i++) {
        strcat(campo, itens[i]);
        if (i < total - 1) strcat(campo, ", ");
        free(itens[i]);
    }
}

void lerShow(Show *s, char *linha) {
    char *campos[11];
    int campoIndex = 0;
    int entreAspas = 0;
    char buffer[256];
    int bufIndex = 0;
    char *ptr = linha;

    while (*ptr != '\0' && campoIndex < 11) {
        if (*ptr == '"') {
            entreAspas = !entreAspas;
        } else if (*ptr == ',' && !entreAspas) {
            buffer[bufIndex] = '\0';
            campos[campoIndex++] = strdup(buffer);
            bufIndex = 0;
        } else {
            buffer[bufIndex++] = *ptr;
        }
        ptr++;
    }

    buffer[bufIndex] = '\0';
    if (campoIndex < 11) {
        campos[campoIndex++] = strdup(buffer);
    }

    if (campoIndex != 11) {
        memset(s, 0, sizeof(Show));
        for (int i = 0; i < campoIndex; i++) free(campos[i]);
        return;
    }

    strcpy(s->show_id, campos[0]);        substituirPorNaN(s->show_id);
    strcpy(s->type, campos[1]);           substituirPorNaN(s->type);
    strcpy(s->title, campos[2]);          substituirPorNaN(s->title);
    strcpy(s->director, campos[3]);       substituirPorNaN(s->director);
    strcpy(s->cast, campos[4]);           substituirPorNaN(s->cast); ordenarTokens(s->cast);
    strcpy(s->country, campos[5]);        substituirPorNaN(s->country);
    strcpy(s->date_added, campos[6]);     substituirPorNaN(s->date_added);
    s->release_year = atoi(campos[7]);    if (campos[7][0] == '\0') s->release_year = 0;
    strcpy(s->rating, campos[8]);         substituirPorNaN(s->rating);
    strcpy(s->duration, campos[9]);       substituirPorNaN(s->duration);
    strcpy(s->listed_in, campos[10]);     substituirPorNaN(s->listed_in); ordenarTokens(s->listed_in);

    for (int i = 0; i < 11; i++) free(campos[i]);
}

void imprimirShow(Show *s, int pos) {
    char ano_str[10];
    if (s->release_year == 0)
        strcpy(ano_str, "NaN");
    else
        sprintf(ano_str, "%d", s->release_year);

    printf("[%d] => %s ## %s ## %s ## %s ## [%s] ## %s ## %s ## %s ## %s ## %s ## [%s] ##\n",
        pos,
        s->show_id,
        s->title,
        s->type,
        s->director,
        s->cast,
        s->country,
        s->date_added,
        ano_str,
        s->rating,
        s->duration,
        s->listed_in
    );
}

int main() {
    FILE *arquivo = fopen("/tmp/disneyplus.csv", "r");
    if (!arquivo) {
        perror("Erro ao abrir o CSV");
        return 1;
    }

    Show shows[1500];
    int totalShows = 0;
    char linha[1024];

    fgets(linha, sizeof(linha), arquivo);

    while (fgets(linha, sizeof(linha), arquivo) != NULL && totalShows < 1500) {
        linha[strcspn(linha, "\n")] = '\0';
        lerShow(&shows[totalShows], linha);
        if (shows[totalShows].show_id[0] != '\0')
            totalShows++;
    }

    fclose(arquivo);

    char entrada[256];
    for (;;) {
        if (fgets(entrada, sizeof(entrada), stdin) == NULL) break;
        entrada[strcspn(entrada, "\n")] = '\0';
        if (strcmp(entrada, "FIM") == 0)
            break;
        for (int i = 0; i < totalShows; ++i) {
            if (strcmp(shows[i].show_id, entrada) == 0) {
                empilhar(shows[i]);
                break;
            }
        }
    }

    int qntComandos = 0;
    scanf("%d%*c", &qntComandos);
    for (int i = 0; i < qntComandos; ++i) {
        char comando[100];
        if (fgets(comando, sizeof(comando), stdin) == NULL) break;
        comando[strcspn(comando, "\n")] = '\0';

        if (strncmp(comando, "I ", 2) == 0) {
            char id[256];
            sscanf(comando + 2, "%s", id);
            for (int j = 0; j < totalShows; ++j) {
                if (strcmp(shows[j].show_id, id) == 0) {
                    empilhar(shows[j]);
                    break;
                }
            }
        } else if (strcmp(comando, "R") == 0) {
            Show* removido = desempilhar();
            if (removido != NULL) {
                printf("(R) %s\n", removido->title);
            }
        }
    }

    for (int i = topo - 1; i >= 0; --i) {
        imprimirShow(&pilha[i], i);
    }

    return 0;
}