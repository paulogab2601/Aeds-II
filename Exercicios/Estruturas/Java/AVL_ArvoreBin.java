class No {
    int fator;
    int elemento;
    No dir, esq;

    public No(int x) {
        this.fator = 1;
        this.elemento = x;
        this.dir = this.esq = null;
    }
}

public class AVL_ArvoreBin {
    No raiz;

    public AVL_ArvoreBin() {
        this.raiz = null;
    }

    /* Metodos publicos: */

    public void caminharCentral() {
        caminharCentral(raiz);
    }

    public void caminharPre() {
        caminharPre(raiz);

    }

    public void caminharPos() {
        caminharPos(raiz);
    }

    public void inserir(int x) throws Exception {
        raiz = inserir(x, raiz);
    }
    /*
     * public void remover(int x) throws Exception {
     * raiz = remover(x, raiz);
     * }
     */

    /* Metodos privados: */

    private No inserir(int x, No i) throws Exception {

        if (i == null) {
            return new No(x);
        } else if (x < i.elemento) {
            inserir(x, i.esq);
        } else if (x > i.elemento) {
            inserir(x, i.dir);
        } else {
            throw new Exception("Erro!");

        }

        return i;
    }

    private void caminharCentral(No i) {
        if (i == null) {
            return;
        } else {
            caminharCentral(i.esq);
            System.out.println("Elemento: " + i.elemento);
            caminharCentral(i.dir);
        }

    }

    private void caminharPre(No i) {
        if (i == null) {
            return;
        } else {
            System.out.println("Elemento: " + i.elemento);
            caminharPre(i.esq);
            caminharPre(i.dir);
        }

    }

    private void caminharPos(No i) {
        if (i == null) {
            return;
        } else {
            caminharPos(i.esq);
            caminharPos(i.dir);
            System.out.println("Elemento: " + i.elemento);
        }
    }

    /*
     * Metodo pra remoção na binaria simples, nao funciona na AVL!
     * 
     * private No remover(int x, No i) throws Exception {
     * if (i == null) {
     * throw new Exception("Erro!");
     * } else if (x < i.elemento) {
     * i.esq = remover(x, i.esq);
     * } else if (x > i.elemento) {
     * i.dir = remover(x, i.dir);
     * } else if (i.dir == null) {
     * i = i.esq;
     * } else if (i.esq == null) {
     * i = i.dir;
     * } else {
     * i.esq = maiorEsq(i, i.esq);
     * }
     * return i;
     * 
     * }
     * 
     * private No maiorEsq(No i, No j) {
     * if (j.dir == null) {
     * i.elemento = j.elemento;
     * j = j.esq;
     * } else {
     * j.dir = maiorEsq(i, j.dir);
     * }
     * 
     * return j;
     * }
     */

}
