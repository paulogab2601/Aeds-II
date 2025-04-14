
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

#define MAX_LINE 1000
#define MAX_FIELDS 12
#define MAX_SHOWS 1500

// Função para remover aspas e espaços extras
typedef struct {
    char show_id[20];
    char type[50];
    char title[200];
    char director[200];
    char cast[500];
    char country[100];
    char date_added[100];
    int release_year;
    char rating[20];
    char duration[50];
    char listed_in[300];
} Show;

void trim(char *str) {
    char *end;
    while (isspace((unsigned char)*str)) str++;
    end = str + strlen(str) - 1;
    while (end > str && isspace((unsigned char)*end)) end--;
    *(end + 1) = '\0';
}

void replace_empty(char *str, const char *default_value) {
    if (strlen(str) == 0 || strcmp(str, "\n") == 0) {
        strcpy(str, default_value);
    }
}

void ordenar_lista(char *lista) {
    char *tokens[100];
    int count = 0;
    char *token = strtok(lista, ",");

    while (token) {
        trim(token);
        tokens[count++] = token;
        token = strtok(NULL, ",");
    }

    for (int i = 0; i < count - 1; i++) {
        for (int j = i + 1; j < count; j++) {
            if (strcmp(tokens[i], tokens[j]) > 0) {
                char *temp = tokens[i];
                tokens[i] = tokens[j];
                tokens[j] = temp;
            }
        }
    }

    strcpy(lista, "");
    for (int i = 0; i < count; i++) {
        strcat(lista, tokens[i]);
        if (i < count - 1) strcat(lista, ", ");
    }
}

void parse_line(char *line, Show *s) {
    char *fields[MAX_FIELDS];
    int i = 0;

    char *token = strtok(line, ",");
    while (token && i < MAX_FIELDS) {
        fields[i++] = token;
        token = strtok(NULL, ",");
    }

    strcpy(s->show_id, fields[0] ? fields[0] : "NaN");
    strcpy(s->type, fields[1] ? fields[1] : "NaN");
    strcpy(s->title, fields[2] ? fields[2] : "NaN");
    strcpy(s->director, fields[3] ? fields[3] : "NaN");
    strcpy(s->cast, fields[4] ? fields[4] : "NaN");
    strcpy(s->country, fields[5] ? fields[5] : "NaN");
    strcpy(s->date_added, fields[6] ? fields[6] : "March 1, 1900");
    s->release_year = fields[7] ? atoi(fields[7]) : -1;
    strcpy(s->rating, fields[8] ? fields[8] : "NaN");
    strcpy(s->duration, fields[9] ? fields[9] : "NaN");
    strcpy(s->listed_in, fields[10] ? fields[10] : "NaN");

    // Tratar valores faltantes
    replace_empty(s->director, "NaN");
    replace_empty(s->cast, "NaN");
    replace_empty(s->country, "NaN");
    replace_empty(s->date_added, "March 1, 1900");
    replace_empty(s->rating, "NaN");
    replace_empty(s->duration, "NaN");
    replace_empty(s->listed_in, "NaN");

    if (strcmp(s->cast, "NaN") != 0) ordenar_lista(s->cast);
    if (strcmp(s->listed_in, "NaN") != 0) ordenar_lista(s->listed_in);
}

void imprimir_show(Show *s) {
    printf("=> %s ## %s ## %s ## %s ## [%s] ## %s ## %s ## %d ## %s ## %s ## [%s]\n",
        s->show_id,
        s->title,
        s->type,
        s->director,
        strcmp(s->cast, "NaN") == 0 ? "NaN" : s->cast,
        s->country,
        s->date_added,
        s->release_year,
        s->rating,
        s->duration,
        strcmp(s->listed_in, "NaN") == 0 ? "NaN" : s->listed_in);
}

int main() {
    FILE *fp = fopen("tmp/disneyplus.csv", "r");
    if (!fp) {
        perror("Erro ao abrir o arquivo");
        return 1;
    }

    Show shows[MAX_SHOWS];
    int total = 0;
    char line[MAX_LINE];

    // Pula a primeira linha (cabeçalho)
    fgets(line, MAX_LINE, fp);

    while (fgets(line, MAX_LINE, fp)) {
        line[strcspn(line, "\n")] = 0;
        parse_line(line, &shows[total]);
        total++;
    }

    char entrada[100];
    while (1) {
        fgets(entrada, sizeof(entrada), stdin);
        entrada[strcspn(entrada, "\n")] = 0;
        if (strcmp(entrada, "FIM") == 0) break;

        for (int i = 0; i < total; i++) {
            if (strcmp(shows[i].show_id + 1, entrada + 1) == 0) { // ignora o 's' do id
                imprimir_show(&shows[i]);
                break;
            }
        }
    }

    fclose(fp);
    return 0;
}
