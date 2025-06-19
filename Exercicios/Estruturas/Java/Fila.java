class Celula {
    int valor;
    Celula prox;

    public Celula() {
        this.valor = 0;
        this.prox = null;
    }

    public Celula(int x) {
        this.valor = x;
        this.prox = null;
    }
}

public class Fila {
    Celula primeiro, ultimo;

    public Fila() {
        primeiro = new Celula();
        ultimo = primeiro;
    }

    public void inserir(int x) {
        ultimo.prox = new Celula(x);
        ultimo = ultimo.prox;
    }

    public int remover() throws Exception {
        if (primeiro == ultimo)
            throw new Exception("ERRO!");

        Celula tmp = primeiro.prox;
        int elemento = primeiro.prox.valor; // Pula o sentinela e pega o valor do !° elemento da fila
        primeiro.prox = primeiro.prox.prox;// Agora o segundo elemento é o 1° da fila (mantendo o sentinela)

        tmp.prox = null;
        tmp = null;

        return elemento;
    }

    public void mostrar() {
        if (primeiro == ultimo) {
            System.out.println("Fila vazia!");
            return;
        }
        System.out.print("[ ");
        for (Celula i = primeiro.prox; i != null; i = i.prox) {
            System.out.print(i.valor + " ");
        }
        System.out.println(" ]");
    }

    public int maiorValor() throws Exception {
        if (primeiro == ultimo)
            throw new Exception("ERRO!");

        int maior = primeiro.prox.valor;

        for (Celula i = primeiro.prox; i != null; i = i.prox) {
            if (maior < i.valor) {
                maior = i.valor;
            }
        }
        return maior;
    }

    public int somaElementos() throws Exception {
        if (primeiro == ultimo)
            throw new Exception("ERRO!");
        int soma = 0;

        for (Celula i = primeiro.prox; i != null; i = i.prox) {
            soma += i.valor;
        }

        return soma;
    }

    public int numElemetos() {
        if (primeiro == ultimo)
            return 0;
        int count = 0;

        for (Celula i = primeiro.prox; i != null; i = i.prox) {
            count++;
        }
        return count;
    }

    public void inverteElementos() throws Exception {
        if (primeiro == ultimo)
            throw new Exception("ERRO!");

        Celula fim = ultimo;
        while (fim != primeiro) {
            Celula nova = new Celula(primeiro.prox.valor);
            nova.prox = fim.prox;
            fim.prox = nova;
            Celula tmp = primeiro.prox;
            primeiro.prox = tmp.prox;
            nova = tmp = tmp.prox = null;
            if (ultimo == fim) {
                ultimo = ultimo.prox;
            }
            fim = null;
        }
    }

    public int contaParesMultiplosCinco(Celula celula, int num) {
        // Na primeira chamada deve ser passado o primeiro nó e o numero 0

        if (celula == null) {
            return num;
        }

        if (celula.valor % 2 == 0 && celula.valor % 5 == 0) {
            num += 1;
        }

        return contaParesMultiplosCinco(celula.prox, num);
    }
}
