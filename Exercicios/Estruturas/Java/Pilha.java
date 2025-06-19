
class celula {
    int valor;
    celula prox;

    celula() {
        this.prox = null;
        this.valor = 0;
    }

    celula(int x) {
        this.prox = null;
        this.valor = x;
    }
}

public class Pilha {
    celula topo = new celula();

    // Construtor
    public Pilha() {
        topo = null;
    }

    // Metodos padrão:
    public void inserir(int x) {
        celula tmp = new celula(x);
        tmp.prox = topo;
        topo = tmp;
        tmp = null;
    }

    public int remover() throws Exception {
        if (topo == null)
            throw new Exception("ERRO!");
        int valor = topo.valor;

        celula tmp = topo;
        topo = topo.prox; // Novo topo
        tmp.prox = null;
        tmp = null;

        return valor;
    }

    public void mostrar() {
        System.out.print("[ ");
        for (celula i = topo; i != null; i = i.prox) {
            System.out.print(i.valor + " ");
        }
        System.out.println("]");
    }

    public int somaPilha(celula pCelula) {
        // Para esse metodo, o topo da pilha deve ser passado como parametro na 1°
        // chamada
        if (pCelula == null) {
            return 0;
        }
        return pCelula.valor + somaPilha(pCelula.prox);
    }

    public int maiorValor() throws Exception {
        if (topo == null)
            throw new Exception("ERRO!");
        int maior = topo.valor;

        for (celula i = topo; i != null; i = i.prox) {
            if (i.valor > maior) {
                maior = i.valor;
            }
        }
        return maior;
    }

    public int maiorElemento(celula Celula, int maior) {

        // Caso base: celula null
        if (Celula == null) {
            return maior;
        } else {
            if (maior < Celula.valor) {
                maior = Celula.valor;
            }
            return maiorElemento(Celula.prox, maior);

        }
    }

    public void mostrarRecursivo(celula Celula) {

        if (Celula == null) {
            return;
        }
        System.out.print(Celula.valor);
        mostrarRecursivo(Celula.prox);
    }

    public void baseTopo(celula Celula) {
        if (Celula == null)
            return;
        baseTopo(Celula.prox);
        System.out.println(Celula.valor);
    }

    public int numElemetos() {
        int count = 0;
        for (celula i = topo; i != null; i = i.prox) {
            count++;
        }
        return count;
    }

    public void baseTopoIT(celula Celula) throws Exception {
        if (topo == null)
            throw new Exception("ERRO!");
        int count = 0;
        int[] aux = new int[numElemetos()];

        for (celula i = topo; i != null; i = i.prox) {
            aux[count] = i.valor;
            count++;
        }
        for (int i = numElemetos(); i > 0; i--) {
            System.out.println(aux[i-1]);
        }
    }
}
