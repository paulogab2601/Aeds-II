#include <stdlib.h>
#include <stdio.h>


typedef struct Pilha
{
    int valor;
    struct Pilha *prox;
            
}Pilha;

void push(Pilha **topo, int valor);
int pop(Pilha **topo);



void push(Pilha **topo, int valor){

    Pilha *novo = (Pilha*)malloc(sizeof(Pilha));
    novo->valor = valor;
    novo->prox = *topo;
    *topo = novo;
}

int pop(Pilha **topo){
    if(*topo == NULL)return -1;

    Pilha *remover = *topo;
    int valor = remover->valor;
    *topo = remover->prox;
    free(remover);

    return valor;
}

int buscar(Pilha *topo, int chave) {
    while (topo != NULL) {
        if (topo->valor == chave) return 1;
        topo = topo->prox;
    }
    return 0;
}

