#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

typedef struct {
    char codigo[256];
    char formato[256];
    char titulo[256];
    char autor[256];
    char atores[256];
    char origem[256];
    char lancamento[256];
    int anoProducao;
    char faixaEtaria[256];
    char tempo[256];
    char generos[256];
} Titulo;

Titulo* pilha[1500];
int topo = 0;

void empilhar(Titulo* t) {
    if (topo < 1500) {
        pilha[topo++] = t;
    }
}

Titulo* desempilhar() {
    if (topo > 0) {
        return pilha[--topo];
    }
    return NULL;
}

void preencheNaN(char *str) {
    if (str[0] == '\0') strcpy(str, "NaN");
}

void ordenarAtores(char *campo) {
    char *lista[30];
    int n = 0;
    char *token = strtok(campo, ",");
    while (token && n < 30) {
        while (*token == ' ') token++;
        lista[n++] = strdup(token);
        token = strtok(NULL, ",");
    }
    for (int i = 0; i < n - 1; i++) {
        for (int j = i + 1; j < n; j++) {
            if (strcmp(lista[i], lista[j]) > 0) {
                char *aux = lista[i]; lista[i] = lista[j]; lista[j] = aux;
            }
        }
    }
    campo[0] = '\0';
    for (int i = 0; i < n; i++) {
        strcat(campo, lista[i]);
        if (i < n - 1) strcat(campo, ", ");
        free(lista[i]);
    }
}

void ordenarGeneros(char *campo) {
    char *lista[30];
    int n = 0;
    char *token = strtok(campo, ",");
    while (token && n < 30) {
        while (*token == ' ') token++;
        lista[n++] = strdup(token);
        token = strtok(NULL, ",");
    }
    for (int i = 0; i < n - 1; i++) {
        for (int j = i + 1; j < n; j++) {
            if (strcmp(lista[i], lista[j]) > 0) {
                char *aux = lista[i]; lista[i] = lista[j]; lista[j] = aux;
            }
        }
    }
    campo[0] = '\0';
    for (int i = 0; i < n; i++) {
        strcat(campo, lista[i]);
        if (i < n - 1) strcat(campo, ", ");
        free(lista[i]);
    }
}

void lerTitulo(Titulo *t, char *linha) {
    char *campos[11];
    int idx = 0, entreAspas = 0, bidx = 0;
    char buffer[256];
    char *p = linha;
    while (*p != '\0' && idx < 11) {
        if (*p == '"') entreAspas = !entreAspas;
        else if (*p == ',' && !entreAspas) {
            buffer[bidx] = '\0';
            campos[idx++] = strdup(buffer);
            bidx = 0;
        } else buffer[bidx++] = *p;
        p++;
    }
    buffer[bidx] = '\0';
    if (idx < 11) campos[idx++] = strdup(buffer);
    if (idx != 11) {
        memset(t, 0, sizeof(Titulo));
        for (int i = 0; i < idx; i++) free(campos[i]);
        return;
    }
    strcpy(t->codigo, campos[0]); preencheNaN(t->codigo);
    strcpy(t->formato, campos[1]); preencheNaN(t->formato);
    strcpy(t->titulo, campos[2]); preencheNaN(t->titulo);
    strcpy(t->autor, campos[3]); preencheNaN(t->autor);
    strcpy(t->atores, campos[4]); preencheNaN(t->atores); ordenarAtores(t->atores);
    strcpy(t->origem, campos[5]); preencheNaN(t->origem);
    strcpy(t->lancamento, campos[6]); preencheNaN(t->lancamento);
    t->anoProducao = atoi(campos[7]); if (campos[7][0] == '\0') t->anoProducao = 0;
    strcpy(t->faixaEtaria, campos[8]); preencheNaN(t->faixaEtaria);
    strcpy(t->tempo, campos[9]); preencheNaN(t->tempo);
    strcpy(t->generos, campos[10]); preencheNaN(t->generos); ordenarGeneros(t->generos);
    for (int i = 0; i < 11; i++) free(campos[i]);
}

void mostrarTitulo(Titulo *t, int idx) {
    char anoStr[10];
    if (t->anoProducao == 0) strcpy(anoStr, "NaN");
    else sprintf(anoStr, "%d", t->anoProducao);
    printf("[%d] => %s ## %s ## %s ## %s ## [%s] ## %s ## %s ## %s ## %s ## %s ## [%s] ##\n",
        idx, t->codigo, t->titulo, t->formato, t->autor, t->atores, t->origem, t->lancamento, anoStr, t->faixaEtaria, t->tempo, t->generos);
}

int main() {
    char arquivoCSV[] = "/tmp/disneyplus.csv";
    FILE *fp = fopen(arquivoCSV, "r");
    if (!fp) { perror("Erro ao abrir arquivo"); return 1; }
    Titulo titulos[1500];
    int qtdTitulos = 0;
    char linha[1024];
    fgets(linha, sizeof(linha), fp);
    while (fgets(linha, sizeof(linha), fp) && qtdTitulos < 1500) {
        linha[strcspn(linha, "\n")] = 0;
        lerTitulo(&titulos[qtdTitulos], linha);
        if (titulos[qtdTitulos].codigo[0] != '\0') qtdTitulos++;
    }
    fclose(fp);
    char entrada[256];
    while (1) {
        fgets(entrada, sizeof(entrada), stdin);
        entrada[strcspn(entrada, "\n")] = 0;
        if (!strcmp(entrada, "FIM")) break;
        for (int i = 0; i < qtdTitulos; i++) {
            if (!strcmp(titulos[i].codigo, entrada)) {
                empilhar(&titulos[i]);
                break;
            }
        }
    }
    int nComandos;
    scanf("%d\n", &nComandos);
    for (int i = 0; i < nComandos; i++) {
        char comando[100];
        fgets(comando, sizeof(comando), stdin);
        comando[strcspn(comando, "\n")] = 0;
        if (!strncmp(comando, "I ", 2)) {
            char codigo[256];
            sscanf(comando + 2, "%s", codigo);
            for (int j = 0; j < qtdTitulos; j++) {
                if (!strcmp(titulos[j].codigo, codigo)) {
                    empilhar(&titulos[j]);
                    break;
                }
            }
        } else if (!strcmp(comando, "R")) {
            Titulo* removido = desempilhar();
            if (removido) printf("(R) %s\n", removido->titulo);
        }
    }
    for (int i = topo - 1; i >= 0; i--) {
        mostrarTitulo(pilha[i], i);
    }
    return 0;
}