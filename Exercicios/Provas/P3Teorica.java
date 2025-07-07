package Provas;

class No {
    boolean cor;
    int elemento;
    No esq, dir;

    No(int x) {
        this.elemento = x;
        this.dir = this.esq = null;
    }
}

class Alvinegra {
    No raiz;

    Alvinegra() {
        raiz = null;
    }

    public int contaNo4() {
        return contaNo4(raiz);

    }

    private int contaNo4(No i) {
        if (i == null)
            return 0;

        if (i.esq != null && i.esq.cor == true && i.dir != null && i.dir.cor == true) {
            return 1 + contaNo4(i.esq) + contaNo4(i.dir);
        } else if (i.esq != null && i.dir == null) {
            return 0 + contaNo4(i.esq);
        } else if (i.dir != null && i.esq == null) {
            return 0 + contaNo4(i.dir);
        } else {
            return contaNo4(i.esq) + contaNo4(i.dir);
        }

    }

}

public class P3Teorica {

}
