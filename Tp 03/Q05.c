#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

typedef struct {
    char id_entrada[256];
    char categoria[256];
    char titulo_programa[256];
    char nome_diretor[256];
    char elenco_principal[256];
    char pais_origem[256];
    char data_adicao[256];
    int ano_producao;
    char classificacao_maturidade[256];
    char duracao_conteudo[256];
    char lista_generos[256];
} EntradaEntretenimento;

typedef struct Elemento {
    EntradaEntretenimento* dados_entrada;
    struct Elemento* proximo_no;
} Elemento;

Elemento* cabeca_lista = NULL;

void atribuir_na_se_vazio(char *campo_texto) {
    if (campo_texto[0] == '\0') {
        strcpy(campo_texto, "NaN");
    }
}

void organizar_nomes_elenco(char *campo_elenco) {
    char *nomes_individuais[30];
    int contagem_nomes = 0;
    char *ponteiro_token = strtok(campo_elenco, ",");

    while (ponteiro_token != NULL && contagem_nomes < 30) {
        while (*ponteiro_token == ' ') ponteiro_token++;
        nomes_individuais[contagem_nomes++] = strdup(ponteiro_token);
        ponteiro_token = strtok(NULL, ",");
    }

    for (int i = 0; i < contagem_nomes - 1; i++) {
        for (int j = i + 1; j < contagem_nomes; j++) {
            if (strcmp(nomes_individuais[i], nomes_individuais[j]) > 0) {
                char *temp_nome = nomes_individuais[i];
                nomes_individuais[i] = nomes_individuais[j];
                nomes_individuais[j] = temp_nome;
            }
        }
    }

    campo_elenco[0] = '\0';
    for (int i = 0; i < contagem_nomes; i++) {
        strcat(campo_elenco, nomes_individuais[i]);
        if (i < contagem_nomes - 1) strcat(campo_elenco, ", ");
        free(nomes_individuais[i]);
    }
}

void ordenar_categorias_genero(char *campo_genero) {
    char *generos_individuais[30];
    int contagem_generos = 0;
    char *ponteiro_token = strtok(campo_genero, ",");

    while (ponteiro_token != NULL && contagem_generos < 30) {
        while (*ponteiro_token == ' ') ponteiro_token++;
        generos_individuais[contagem_generos++] = strdup(ponteiro_token);
        ponteiro_token = strtok(NULL, ",");
    }

    for (int i = 0; i < contagem_generos - 1; i++) {
        for (int j = i + 1; j < contagem_generos; j++) {
            if (strcmp(generos_individuais[i], generos_individuais[j]) > 0) {
                char *temp_genero = generos_individuais[i];
                generos_individuais[i] = generos_individuais[j];
                generos_individuais[j] = temp_genero;
            }
        }
    }

    campo_genero[0] = '\0';
    for (int i = 0; i < contagem_generos; i++) {
        strcat(campo_genero, generos_individuais[i]);
        if (i < contagem_generos - 1) strcat(campo_genero, ", ");
        free(generos_individuais[i]);
    }
}

void analisar_entrada_da_linha(EntradaEntretenimento *entrada_a_preencher, char *linha_csv) {
    char *ponteiros_campos[11];
    int indice_campo_atual = 0;
    int dentro_de_aspas = 0;
    char buffer_temp[512];
    int pos_buffer = 0;
    char *iterador_linha = linha_csv;

    while (*iterador_linha != '\0' && indice_campo_atual < 11) {
        if (*iterador_linha == '"') {
            dentro_de_aspas = !dentro_de_aspas;
        } else if (*iterador_linha == ',' && !dentro_de_aspas) {
            buffer_temp[pos_buffer] = '\0';
            ponteiros_campos[indice_campo_atual++] = strdup(buffer_temp);
            pos_buffer = 0;
        } else {
            buffer_temp[pos_buffer++] = *iterador_linha;
        }
        iterador_linha++;
    }

    buffer_temp[pos_buffer] = '\0';
    if (indice_campo_atual < 11) {
        ponteiros_campos[indice_campo_atual++] = strdup(buffer_temp);
    }

    if (indice_campo_atual != 11) {
        memset(entrada_a_preencher, 0, sizeof(EntradaEntretenimento));
        for (int i = 0; i < indice_campo_atual; i++) free(ponteiros_campos[i]);
        return;
    }

    strcpy(entrada_a_preencher->id_entrada, ponteiros_campos[0]);           atribuir_na_se_vazio(entrada_a_preencher->id_entrada);
    strcpy(entrada_a_preencher->categoria, ponteiros_campos[1]);           atribuir_na_se_vazio(entrada_a_preencher->categoria);
    strcpy(entrada_a_preencher->titulo_programa, ponteiros_campos[2]);     atribuir_na_se_vazio(entrada_a_preencher->titulo_programa);
    strcpy(entrada_a_preencher->nome_diretor, ponteiros_campos[3]);        atribuir_na_se_vazio(entrada_a_preencher->nome_diretor);
    strcpy(entrada_a_preencher->elenco_principal, ponteiros_campos[4]);    atribuir_na_se_vazio(entrada_a_preencher->elenco_principal); organizar_nomes_elenco(entrada_a_preencher->elenco_principal);
    strcpy(entrada_a_preencher->pais_origem, ponteiros_campos[5]);         atribuir_na_se_vazio(entrada_a_preencher->pais_origem);
    strcpy(entrada_a_preencher->data_adicao, ponteiros_campos[6]);         atribuir_na_se_vazio(entrada_a_preencher->data_adicao);
    entrada_a_preencher->ano_producao = atoi(ponteiros_campos[7]);          if (ponteiros_campos[7][0] == '\0') entrada_a_preencher->ano_producao = 0;
    strcpy(entrada_a_preencher->classificacao_maturidade, ponteiros_campos[8]); atribuir_na_se_vazio(entrada_a_preencher->classificacao_maturidade);
    strcpy(entrada_a_preencher->duracao_conteudo, ponteiros_campos[9]);     atribuir_na_se_vazio(entrada_a_preencher->duracao_conteudo);
    strcpy(entrada_a_preencher->lista_generos, ponteiros_campos[10]);      atribuir_na_se_vazio(entrada_a_preencher->lista_generos); ordenar_categorias_genero(entrada_a_preencher->lista_generos);

    for (int i = 0; i < 11; i++) {
        free(ponteiros_campos[i]);
    }
}

void adicionar_entrada_no_inicio(EntradaEntretenimento* nova_entrada) {
    Elemento* novo_no = (Elemento*)malloc(sizeof(Elemento));
    novo_no->dados_entrada = nova_entrada;
    novo_no->proximo_no = cabeca_lista;
    cabeca_lista = novo_no;
}

void adicionar_entrada_no_fim(EntradaEntretenimento* nova_entrada) {
    Elemento* novo_no = (Elemento*)malloc(sizeof(Elemento));
    novo_no->dados_entrada = nova_entrada;
    novo_no->proximo_no = NULL;

    if (cabeca_lista == NULL) {
        cabeca_lista = novo_no;
        return;
    }

    Elemento* no_atual = cabeca_lista;
    while (no_atual->proximo_no != NULL) {
        no_atual = no_atual->proximo_no;
    }
    no_atual->proximo_no = novo_no;
}

void inserir_na_posicao(EntradaEntretenimento* nova_entrada, int posicao_desejada) {
    if (posicao_desejada == 0) {
        return adicionar_entrada_no_inicio(nova_entrada);
    }

    Elemento* no_atual = cabeca_lista;
    for (int i = 0; i < posicao_desejada - 1 && no_atual != NULL; i++) {
        no_atual = no_atual->proximo_no;
    }

    if (no_atual == NULL) {
        return;
    }

    Elemento* novo_no = (Elemento*)malloc(sizeof(Elemento));
    novo_no->dados_entrada = nova_entrada;
    novo_no->proximo_no = no_atual->proximo_no;
    no_atual->proximo_no = novo_no;
}

EntradaEntretenimento* remover_do_inicio() {
    if (cabeca_lista == NULL) return NULL;

    Elemento* no_a_remover = cabeca_lista;
    cabeca_lista = cabeca_lista->proximo_no;
    EntradaEntretenimento* entrada_removida = no_a_remover->dados_entrada;
    free(no_a_remover);
    return entrada_removida;
}

EntradaEntretenimento* remover_do_fim() {
    if (cabeca_lista == NULL) return NULL;

    if (cabeca_lista->proximo_no == NULL) {
        EntradaEntretenimento* entrada_removida = cabeca_lista->dados_entrada;
        free(cabeca_lista);
        cabeca_lista = NULL;
        return entrada_removida;
    }

    Elemento* no_atual = cabeca_lista;
    while (no_atual->proximo_no->proximo_no != NULL) {
        no_atual = no_atual->proximo_no;
    }
    EntradaEntretenimento* entrada_removida = no_atual->proximo_no->dados_entrada;
    free(no_atual->proximo_no);
    no_atual->proximo_no = NULL;
    return entrada_removida;
}

EntradaEntretenimento* remover_na_posicao(int posicao_alvo) {
    if (posicao_alvo == 0) return remover_do_inicio();

    Elemento* no_atual = cabeca_lista;
    for (int i = 0; i < posicao_alvo - 1 && no_atual != NULL; i++) {
        no_atual = no_atual->proximo_no;
    }

    if (no_atual == NULL || no_atual->proximo_no == NULL) return NULL;

    Elemento* no_a_remover = no_atual->proximo_no;
    no_atual->proximo_no = no_a_remover->proximo_no;
    EntradaEntretenimento* entrada_removida = no_a_remover->dados_entrada;
    free(no_a_remover);
    return entrada_removida;
}

void exibir_detalhes_entrada(EntradaEntretenimento *entrada_atual) {
    char ano_como_string[10];
    if (entrada_atual->ano_producao == 0)
        strcpy(ano_como_string, "N/A");
    else
        sprintf(ano_como_string, "%d", entrada_atual->ano_producao);

    printf("=> %s ## %s ## %s ## %s ## [%s] ## %s ## %s ## %s ## %s ## %s ## [%s] ##\n",
        entrada_atual->id_entrada,
        entrada_atual->titulo_programa,
        entrada_atual->categoria,
        entrada_atual->nome_diretor,
        entrada_atual->elenco_principal,
        entrada_atual->pais_origem,
        entrada_atual->data_adicao,
        ano_como_string,
        entrada_atual->classificacao_maturidade,
        entrada_atual->duracao_conteudo,
        entrada_atual->lista_generos
    );
}

void imprimir_todos_elementos_lista() {
    Elemento* no_atual = cabeca_lista;
    while (no_atual != NULL) {
        exibir_detalhes_entrada(no_atual->dados_entrada);
        no_atual = no_atual->proximo_no;
    }
}

int main() {
    FILE *arquivo_dados = fopen("/tmp/disneyplus.csv", "r");
    if (arquivo_dados == NULL) {
        perror("Erro ao abrir o arquivo CSV de dados");
        return 1;
    }

    EntradaEntretenimento *todas_as_entradas = (EntradaEntretenimento*)malloc(sizeof(EntradaEntretenimento) * 1500);
    int contagem_entradas = 0;
    char buffer_linha[1024];

    fgets(buffer_linha, sizeof(buffer_linha), arquivo_dados);

    while (fgets(buffer_linha, sizeof(buffer_linha), arquivo_dados) != NULL && contagem_entradas < 1500) {
        buffer_linha[strcspn(buffer_linha, "\n")] = 0;
        analisar_entrada_da_linha(&todas_as_entradas[contagem_entradas], buffer_linha);
        if (todas_as_entradas[contagem_entradas].id_entrada[0] != '\0') {
            contagem_entradas++;
        }
    }

    fclose(arquivo_dados);

    char comando_entrada_usuario[256];
    while (1) {
        fgets(comando_entrada_usuario, sizeof(comando_entrada_usuario), stdin);
        comando_entrada_usuario[strcspn(comando_entrada_usuario, "\n")] = 0;

        if (strcmp(comando_entrada_usuario, "FIM") == 0) {
            break;
        }

        for (int i = 0; i < contagem_entradas; i++) {
            if (strcmp(todas_as_entradas[i].id_entrada, comando_entrada_usuario) == 0) {
                adicionar_entrada_no_fim(&todas_as_entradas[i]);
                break;
            }
        }
    }

    int total_comandos;
    scanf("%d\n", &total_comandos);

    for (int k = 0; k < total_comandos; k++) {
        char linha_comando[100];
        fgets(linha_comando, sizeof(linha_comando), stdin);
        linha_comando[strcspn(linha_comando, "\n")] = 0;

        if (strncmp(linha_comando, "II ", 3) == 0) {
            char id_busca[256];
            sscanf(linha_comando + 3, "%s", id_busca);
            for (int j = 0; j < contagem_entradas; j++) {
                if (strcmp(todas_as_entradas[j].id_entrada, id_busca) == 0) {
                    adicionar_entrada_no_inicio(&todas_as_entradas[j]);
                    break;
                }
            }
        } else if (strncmp(linha_comando, "IF ", 3) == 0) {
            char id_busca[256];
            sscanf(linha_comando + 3, "%s", id_busca);
            for (int j = 0; j < contagem_entradas; j++) {
                if (strcmp(todas_as_entradas[j].id_entrada, id_busca) == 0) {
                    adicionar_entrada_no_fim(&todas_as_entradas[j]);
                    break;
                }
            }
        } else if (strncmp(linha_comando, "I*", 2) == 0) {
            int posicao;
            char id_busca[256];
            sscanf(linha_comando + 2, "%d %s", &posicao, id_busca);
            for (int j = 0; j < contagem_entradas; j++) {
                if (strcmp(todas_as_entradas[j].id_entrada, id_busca) == 0) {
                    inserir_na_posicao(&todas_as_entradas[j], posicao);
                    break;
                }
            }
        } else if (strcmp(linha_comando, "RI") == 0) {
            EntradaEntretenimento* item_removido = remover_do_inicio();
            if (item_removido != NULL) printf("(R) %s\n", item_removido->titulo_programa);
        } else if (strcmp(linha_comando, "RF") == 0) {
            EntradaEntretenimento* item_removido = remover_do_fim();
            if (item_removido != NULL) printf("(R) %s\n", item_removido->titulo_programa);
        } else if (strncmp(linha_comando, "R*", 2) == 0) {
            int posicao;
            sscanf(linha_comando + 2, "%d", &posicao);
            EntradaEntretenimento* item_removido = remover_na_posicao(posicao);
            if (item_removido != NULL) printf("(R) %s\n", item_removido->titulo_programa);
        }
    }

    imprimir_todos_elementos_lista();

    free(todas_as_entradas);

    Elemento* no_atual = cabeca_lista;
    while (no_atual != NULL) {
        Elemento* proximo_no = no_atual->proximo_no;
        free(no_atual);
        no_atual = proximo_no;
    }
    cabeca_lista = NULL;

    return 0;
}