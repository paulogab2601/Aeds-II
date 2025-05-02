#define _XOPEN_SOURCE
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <locale.h>

#define MAX_LINE 1024
#define MAX_COLS 12
#define MAX_TEXT 256
#define MAX_ITEMS 20

typedef struct {
    char id[MAX_TEXT];
    char type[MAX_TEXT];
    char title[MAX_TEXT];
    char director[MAX_TEXT];
    char cast[MAX_ITEMS][MAX_TEXT];
    int castCount;
    char country[MAX_TEXT];
    char date[MAX_TEXT];
    int year;
    char rating[MAX_TEXT];
    char duration[MAX_TEXT];
    char genres[MAX_ITEMS][MAX_TEXT];
    int genreCount;
} Show;

void initShow(Show *show) {
    strcpy(show->id, "NaN");
    strcpy(show->type, "NaN");
    strcpy(show->title, "NaN");
    strcpy(show->director, "NaN");
    strcpy(show->cast[0], "NaN");
    show->castCount = 1;
    strcpy(show->country, "NaN");
    strcpy(show->date, "NaN");
    show->year = -1;
    strcpy(show->rating, "NaN");
    strcpy(show->duration, "NaN");
    strcpy(show->genres[0], "NaN");
    show->genreCount = 1;
}

void cleanQuotes(char *str) {
    int index = 0;
    for (int i = 0; str[i]; i++) {
        if (str[i] != '"') {
            str[index++] = str[i];
        }
    }
    str[index] = '\0';
}

int splitList(char *field, char dest[MAX_ITEMS][MAX_TEXT]) {
    int count = 0;
    char *token = strtok(field, ",");
    while (token && count < MAX_ITEMS) {
        while (*token == ' ') token++;  // Skip leading spaces
        cleanQuotes(token);
        strncpy(dest[count++], token, MAX_TEXT);
        token = strtok(NULL, ",");
    }
    return count;
}

void sortList(char items[MAX_ITEMS][MAX_TEXT], int size) {
    for (int i = 0; i < size - 1; i++)
        for (int j = i + 1; j < size; j++)
            if (strcasecmp(items[i], items[j]) > 0) {
                char temp[MAX_TEXT];
                strcpy(temp, items[i]);
                strcpy(items[i], items[j]);
                strcpy(items[j], temp);
            }
}

void printShow(const Show *show) {
    char formattedDate[MAX_TEXT] = "NaN";

    if (strcmp(show->date, "NaN") != 0) {
        struct tm parsedDate = {0};
        if (strptime(show->date, "%b %d, %Y", &parsedDate)) {
            setlocale(LC_TIME, "en_US.UTF-8");
            strftime(formattedDate, sizeof(formattedDate), "%B %e, %Y", &parsedDate);
        }
    }

    sortList((char (*)[MAX_TEXT])show->cast, show->castCount);

    printf("=> %s ## %s ## %s ## %s ## [", show->id, show->title, show->type, show->director);
    for (int i = 0; i < show->castCount; i++) {
        printf("%s", show->cast[i]);
        if (i < show->castCount - 1) printf(", ");
    }
    printf("] ## %s ## %s ## %d ## %s ## %s ## [", show->country, formattedDate, show->year, show->rating, show->duration);
    for (int i = 0; i < show->genreCount; i++) {
        printf("%s", show->genres[i]);
        if (i < show->genreCount - 1) printf(", ");
    }
    printf("] ##\n");
}

int parseCSV(const char *line, char fields[MAX_COLS][MAX_TEXT]) {
    int col = 0, charIndex = 0, inQuotes = 0;
    char c;

    for (int i = 0; (c = line[i]) != '\0'; i++) {
        if (c == '"') {
            inQuotes = !inQuotes;
        } else if (c == ',' && !inQuotes) {
            fields[col][charIndex] = '\0';
            col++;
            charIndex = 0;
        } else {
            if (charIndex < MAX_TEXT - 1) {
                fields[col][charIndex++] = c;
            }
        }
    }
    fields[col][charIndex] = '\0';
    return col + 1;
}

int findById(const char *searchId, const char *filename, Show *result) {
    FILE *file = fopen(filename, "r");
    if (!file) return 0;

    char line[MAX_LINE];
    fgets(line, sizeof(line), file);

    while (fgets(line, sizeof(line), file)) {
        line[strcspn(line, "\n")] = '\0';

        char columns[MAX_COLS][MAX_TEXT];
        if (parseCSV(line, columns) < 11) continue;

        if (strcmp(columns[0], searchId) != 0) continue;

        initShow(result);
        strcpy(result->id, columns[0]);
        strcpy(result->type, columns[1]);
        strcpy(result->title, columns[2]);
        strcpy(result->director, strlen(columns[3]) ? columns[3] : "NaN");
        if (strlen(columns[4])) {
            char castCopy[MAX_TEXT];
            strncpy(castCopy, columns[4], MAX_TEXT);
            result->castCount = splitList(castCopy, result->cast);
        }
        strcpy(result->country, strlen(columns[5]) ? columns[5] : "NaN");
        strcpy(result->date, strlen(columns[6]) ? columns[6] : "NaN");
        result->year = strlen(columns[7]) ? atoi(columns[7]) : -1;
        strcpy(result->rating, strlen(columns[8]) ? columns[8] : "NaN");
        strcpy(result->duration, strlen(columns[9]) ? columns[9] : "NaN");
        if (strlen(columns[10])) {
            char genreCopy[MAX_TEXT];
            strncpy(genreCopy, columns[10], MAX_TEXT);
            result->genreCount = splitList(genreCopy, result->genres);
        }

        fclose(file);
        return 1;
    }

    fclose(file);
    return 0;
}

int main() {
    setlocale(LC_TIME, "en_US.UTF-8");  

    char input[MAX_TEXT];
    const char *csvPath = "/tmp/disneyplus.csv";

    while (fgets(input, sizeof(input), stdin)) {
        input[strcspn(input, "\n")] = '\0';
        if (strcmp(input, "FIM") == 0) break;

        Show show;
        if (findById(input, csvPath, &show)) {
            printShow(&show);
        } else {
            printf("=> NaN ## NaN ## NaN ## NaN ## [NaN] ## NaN ## NaN ## -1 ## NaN ## NaN ## [NaN] ##\n");
        }
    }

    return 0;
}