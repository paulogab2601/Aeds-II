#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

#define MAX_LINE 1024 //limita o tamanho da linha
#define MAX_STRING 200
#define MAX_LIST 20

typedef struct { //criação da struct Show
    char show_id[MAX_STRING];
    char type[MAX_STRING];
    char title[MAX_STRING];
    char director[MAX_STRING];
    char cast[MAX_LIST][MAX_STRING];
    int cast_size;
    char country[MAX_STRING];
    char date_added[MAX_STRING];
    int release_year;
    char rating[MAX_STRING];
    char duration[MAX_STRING];
    char listed_in[MAX_LIST][MAX_STRING];
    int listed_in_size;
} Show;

void handle_empty(char *field) {
    if (field == NULL || strlen(field) == 0)
        strcpy(field, "NaN");
}

void sort_list(char list[][MAX_STRING], int size) {
    for (int i = 0; i < size-1; i++) {
        for (int j = i+1; j < size; j++) {
            if (strcmp(list[i], list[j]) > 0) {
                char temp[MAX_STRING];
                strcpy(temp, list[i]);
                strcpy(list[i], list[j]);
                strcpy(list[j], temp);
            }
        }
    }
}

int split(char *field, char list[][MAX_STRING]) { 
    int count = 0;
    char *token = strtok(field, ",");
    while (token != NULL && count < MAX_LIST) {
        while (*token == ' ') token++;
        strcpy(list[count++], token);
        token = strtok(NULL, ",");
    }
    sort_list(list, count);
    return count;
}

// Função para remover aspas duplas internas
void remove_aspas(char *str) {
    char *src = str, *dst = str;
    while (*src) {
        if (*src == '"' && *(src + 1) == '"') {
            src += 2; // pula as aspas duplas
            *dst++ = '"'; // mantém uma aspas, ou remova se quiser ignorar
        } else {
            *dst++ = *src++;
        }
    }
    *dst = '\0';
}

// Função para extrair os primeiros 12 campos respeitando aspas
void categorias(char *line, char *fields[12]) {
    int i = 0;
    char *start = line;
    while (*start && i < 12) {
        if (*start == '"') {
            start++;
            fields[i++] = start;
            while (*start && !(*start == '"' && (*(start+1) == ',' || *(start+1) == '\0'))) start++;
            *start = '\0';
            start += 2;
        } else {
            fields[i++] = start;
            while (*start && *start != ',') start++;
            if (*start) {
                *start = '\0';
                start++;
            }
        }
    }
}

void parse_show(char *line, Show *s) { 
    char *fields[12];
    categorias(line, fields);

    strcpy(s->show_id, fields[0]);
    strcpy(s->type, fields[1]);

    remove_aspas(fields[2]); 
    strcpy(s->title, fields[2]);

    remove_aspas(fields[3]);
    strcpy(s->director, fields[3]); 
    handle_empty(s->director);

    remove_aspas(fields[4]);
    if (strlen(fields[4]) == 0) {
        strcpy(s->cast[0], "NaN");
        s->cast_size = 1;
    } else {
        s->cast_size = split(fields[4], s->cast);
    }

    strcpy(s->country, fields[5]); 
    handle_empty(s->country);
    strcpy(s->date_added, fields[6]); 
    handle_empty(s->date_added);
    s->release_year = atoi(fields[7]);
    strcpy(s->rating, fields[8]); 
    handle_empty(s->rating);
    strcpy(s->duration, fields[9]); 
    handle_empty(s->duration);

    remove_aspas(fields[10]);
    if (strlen(fields[10]) == 0) {
        strcpy(s->listed_in[0], "NaN");
        s->listed_in_size = 1;
    } else {
        s->listed_in_size = split(fields[10], s->listed_in);
    }
}

void imprimir(Show *s) {
    printf("=> %s ## %s ## %s ## %s ## [", s->show_id, s->title, s->type, s->director);
    for (int i = 0; i < s->cast_size; i++) {
        printf("%s", s->cast[i]);
        if (i < s->cast_size - 1) printf(", ");
    }
    printf("] ## %s ## %s ## %d ## %s ## %s ## [", s->country, s->date_added, s->release_year, s->rating, s->duration);
    for (int i = 0; i < s->listed_in_size; i++) {
        printf("%s", s->listed_in[i]);
        if (i < s->listed_in_size - 1) printf(", ");
    }
    printf("] ##\n");
}

int main() {
    char input[20];
    //FILE *fp = fopen("../disneyplus.csv", "r");
    FILE *fp = fopen("/tmp/disneyplus.csv", "r"); 
    if (!fp) {
        printf("Erro ao abrir o arquivo.\n");
        return 1;
    }

    char line[MAX_LINE];
    fgets(line, MAX_LINE, fp); // pula cabeçalho

    while (scanf("%s", input) && strcmp(input, "FIM") != 0) {
        rewind(fp);
        fgets(line, MAX_LINE, fp); // pula cabeçalho de novo
        int found = 0;

        while (fgets(line, MAX_LINE, fp)) {
            if (strncmp(line, input, strlen(input)) == 0) {
                Show s;
                parse_show(line, &s);
                imprimir(&s);
                found = 1;
                break;
            }
        }

        if (!found) {
            printf("Show ID %s não encontrado.\n", input);
        }
    }

    fclose(fp);
    return 0;
}