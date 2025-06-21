class Celula {
    int valor;
    Celula prox;

    Celula() {
        this.prox = null;
    }

    Celula(int x) {
        this.valor = x;
        this.prox = null;
    }
}

public class ListaSimples {
    // A lista sempre deve ser ordenada, do menor para o maior!

    /* Atributos + Construtor: */
    Celula primeiro, ultimo;

    ListaSimples() {
        primeiro = new Celula();
        ultimo = primeiro;
    }

    /* Algoritmo de ordenação usado: (BubbleSort) */

    public void BubbleSort() {

        if (primeiro == ultimo)
            return;
        if (numElementos() < 2)
            return;

        for (int i = 0; i < numElementos() - 1; i++) {
            boolean trocou = false;

            Celula iCelula = primeiro.prox;

            for (int j = 0; j < numElementos() - 1; j++) {
                if (iCelula.prox != null && iCelula.valor > iCelula.prox.valor) {

                    int tmp = iCelula.valor;
                    iCelula.valor = iCelula.prox.valor;
                    iCelula.prox.valor = tmp;

                    trocou = true;
                }
                iCelula = iCelula.prox;
            }
            if (!trocou)
                break;
        }

    }

    /* Metodos: */

    public void inserirInicio(int x) {
        // Deve inserir o elemento no Nó cabeça, e criar um novo Nó cabeça

        /*
         * Celula tmp = new Celula(x);
         * tmp.prox = primeiro.prox; // insere a celula na lista
         * primeiro.prox = tmp;
         * 
         * if (ultimo == primeiro)
         * ultimo = tmp;
         * tmp = null;// Descarta tmp
         */

        primeiro.valor = x;

        Celula tmp = new Celula();
        tmp.prox = primeiro;
        primeiro = tmp;

        if (ultimo == primeiro.prox) {
            ultimo = primeiro.prox;
        }

        BubbleSort();
    }

    public void inserir(int pos, int x) throws Exception {
        if (pos < 0 || pos > numElementos())
            throw new Exception("Posicao invalida!");
        Celula nova = new Celula(x);
        Celula i = primeiro;
        for (int j = 0; j < pos; j++) {
            i = i.prox;
        }
        nova.prox = i.prox;
        i.prox = nova;

        if (nova.prox == null) {
            ultimo = nova;
        }

        BubbleSort();
    }

    public int removerFim() throws Exception {
        if (primeiro == ultimo)
            throw new Exception("ERRO!");

        Celula i;
        // Percorre a lista até o penultimo elemento
        for (i = primeiro; i.prox != ultimo; i = i.prox)
            ;

        int valor = i.prox.valor;
        ultimo = i;

        i.prox = null;
        i = null; // Coletada automaticamente após o fim do método, desnecessário; (variavel
                  // local)

        return valor;
    }

    public int remover(int pos) throws Exception {
        if (primeiro == ultimo)
            throw new Exception("Lista vazia!");
        if (pos < 0 || pos >= numElementos())
            throw new Exception("Posicao invalida!");

        Celula i = primeiro;

        for (int j = 0; j < pos; j++) {
            i = i.prox;
        }
        Celula removida = i.prox;
        int valor = removida.valor;

        i.prox = removida.prox;

        if (removida == ultimo) {
            ultimo = i;
        }

        removida.prox = null;

        BubbleSort();

        return valor;
    }

    public int numElementos() {
        if (primeiro == ultimo)
            return 0;

        int num = 0;
        for (Celula i = primeiro.prox; i != null; i = i.prox) {
            num++;
        }
        return num;
    }

    /*
     * public int remove2elemento() throws Exception {
     * if (primeiro == ultimo)
     * throw new Exception("Erro!");
     * if (numElementos() < 2)
     * throw new Exception("Menos de 2 elementos");
     * 
     * Celula i = primeiro;
     * for (int j = 0; j < 1; j++) {
     * i = i.prox;
     * }
     * Celula remove = i.prox;
     * int valor = remove.valor;
     * 
     * i.prox = remove.prox;
     * 
     * if (remove == ultimo) {
     * ultimo = i;
     * }
     * return valor;
     * 
     * }
     */
}
