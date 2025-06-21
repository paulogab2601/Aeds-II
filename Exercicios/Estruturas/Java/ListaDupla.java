class Celula {
    Celula ant, prox;
    int valor;

    public Celula(int x) {
        this.valor = x;
    }

    public Celula() {
        this(0);
    }
}

public class ListaDupla {
    Celula primeiro, ultimo;

    public ListaDupla() {
        primeiro = new Celula();
        ultimo = primeiro;
    }

    /* Métodos: */

    public int numElementos() {
        int count = 0;

        for (Celula i = primeiro.prox; i != null; i = i.prox) {
            count++;
        }

        return count;
    }

    public void inverterElementos() {
        if (primeiro == ultimo)
            return;
        // Deve inverter somente os elementos, sem mexer nos Nós

        int tam = numElementos();

        if (tam == 2) {
            int tmp = primeiro.prox.valor;
            primeiro.prox.valor = ultimo.valor;
            ultimo.valor = tmp;

            return;
        }

        Celula a = primeiro.prox;
        Celula b = ultimo;
        int i = 0, j = tam - 1;

        while (i < j) {
            int tmp = a.valor;
            a.valor = b.valor;
            b.valor = tmp;

            a = a.prox;
            b = b.ant;

            i++;
            j--;
        }

    }

    public void mostrar() {
        if (primeiro == ultimo) {
            System.err.println("Lista vazia!");
            return;
        }
        Celula exposed = primeiro.prox;

        for (int i = 0; i < numElementos(); i++) {
            System.out.print(exposed.valor);
            exposed = exposed.prox;
        }
        return;
    }

    public void inserirInicio(int x) {
        Celula tmp = new Celula(x);

        tmp.ant = primeiro;
        tmp.prox = primeiro.prox;
        primeiro.prox = tmp;
        if (primeiro == ultimo) {
            ultimo = tmp;
        } else {
            tmp.prox.ant = tmp;

        }

    }

    public void inserirFim(int x) {
        ultimo.prox = new Celula(x);
        ultimo.prox.ant = ultimo;
        ultimo = ultimo.prox;
    }

    public void inserir(int pos, int x) throws Exception {
        if (pos < 0 || pos > numElementos())
            throw new Exception("Erro!");

        if (pos == 0) {
            inserirInicio(x);
        } else if (pos == numElementos()) {
            inserirFim(x);
        } else {
            Celula i = primeiro;
            for (int j = 0; j < pos; j++)// Navega até a celula anterior a posicao
            {
                i = i.prox;
            }

            Celula tmp = new Celula(x);

            tmp.ant = i; // TMP aponta para trás (i)
            tmp.prox = i.prox; // TMP aponta para frente (antigo i.prox)
            i.prox = tmp; // i aponta para TMP
            tmp.prox.ant = tmp; // A célula seguinte aponta para trás (TMP)
        }

    }

    public int removerIncicio() throws Exception {
        if (primeiro == ultimo)
            throw new Exception("Erro!");

        Celula tmp = primeiro.prox;
        int valor = tmp.valor;

        primeiro.prox = tmp.prox;

        if (tmp == ultimo) {
            ultimo = primeiro; // Lista ficou vazia
        } else {
            tmp.prox.ant = primeiro; // Atualiza ponteiro 'ant' do novo primeiro real
        }

        tmp.prox = tmp.ant = null;

        return valor;
    }

    public int removerFinal() throws Exception {
        if (primeiro == ultimo)
            throw new Exception("Erro");

        Celula tmp = ultimo; // copia ultimo

        int valor = tmp.valor; // armazena o valor da ultima celula
        ultimo = tmp.ant; // atualiza a ultima celula da lista

        ultimo.prox = null;// atualiza o ponteiro da ultima celula
        tmp.ant = tmp.prox = null;// descarta tmp

        return valor;
    }

    public int remover(int pos) throws Exception {
        if (primeiro == ultimo)
            throw new Exception("Erro!");
        if (pos < 0 || pos >= numElementos())
            throw new Exception("Erro!");

        int valor = 0;
        if (pos == 0) {
            return removerIncicio();
        } else if (pos == numElementos() - 1) {
            return removerFinal();
        } else {

            Celula i = primeiro;
            for (int j = 0; j < pos; j++) {
                i = i.prox;
            }
            Celula tmp = i.prox;

            valor = tmp.valor;
            tmp.prox.ant = i;
            i.prox = tmp.prox;

            tmp.prox = tmp.ant = null;
        }
        return valor;
    }

}
