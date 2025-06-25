import java.util.Random;

class No {
    int valor;
    No esq, dir;

    public No(int valor) {
        this.valor = valor;
        this.esq = null;
        this.dir = null;
    }

    public No(int valor, No esq, No dir) {
        this.valor = valor;
        this.esq = esq;
        this.dir = dir;
    }

}

public class ArvoreBin {
    No raiz;

    public ArvoreBin() {
        raiz = null;
    }

    /* Metodos públicos: */

    public void inserir(int x) throws Exception {
        raiz = inserir(x, raiz); // O METODO INSERIR RETORNA O NÓ RAIZ - NAO PODE ESQUECER DE ATRIBUIR
    }

    public boolean pesquisar(int x) {
        return pesquisar(x, raiz);
    }

    public void inserirPai(int x) throws Exception {
        if (raiz == null) {
            raiz = new No(x);
        } else if (x < raiz.valor) {
            inserirPai(x, raiz.esq, raiz);
        } else if (x > raiz.valor) {
            inserirPai(x, raiz.dir, raiz);
        } else {
            throw new Exception("Erro!");
        }
    }

    public void balancear() {
        if (raiz.esq != null && raiz.dir != null) {
            System.out.println("Arvore balanceada!");

        } else if (raiz.dir != null) {
            if (raiz.dir.dir != null) {
                rotEsq();
            } else {
                rotDirEsq();
            }
        } else {
            if (raiz.esq.dir != null) {
                rotEsqDir();
            } else {
                rotDir();
            }
        }
    }

    public void caminharPre() {
        caminharPre(raiz);
    }

    public void caminharCentral() {
        caminharCentral(raiz);
    }

    public void caminharPos() {
        caminharPos(raiz);
    }

    public int alturaArvore() {
        return alturaArvore(raiz);
    }

    public int numElementos() {
        return numElementos(raiz);
    }

    public int getMaior() throws Exception {
        return getMaior(raiz);
    }

    public int getMenor() throws Exception {
        return getMenor(raiz);
    }

    public void remover(int x) throws Exception {
        raiz = remover(x, raiz);
    }

    public void rotEsq() {
        raiz = rotEsq(raiz);
    }

    public void rotDir() {
        raiz = rotDir(raiz);
    }

    public void rotDirEsq() {
        raiz = rotDirEsq(raiz);
    }

    public void rotEsqDir() {
        raiz = rotEsqDir(raiz);
    }

    /* Metodos privados: */

    private No inserir(int x, No i) throws Exception {
        if (i == null) {
            return new No(x);
        } else if (x < i.valor) {
            i.esq = inserir(x, i.esq);
        } else if (x > i.valor) {
            i.dir = inserir(x, i.dir);
        } else {
            throw new Exception("Erro!");
        }

        return i;
    }

    private void inserirPai(int x, No i, No pai) throws Exception {
        if (i == null) {
            if (pai == null) {
                raiz = new No(x); // caso especial: inserindo a raiz
            } else if (x < pai.valor) {
                pai.esq = new No(x);
            } else {
                pai.dir = new No(x);
            }
        } else if (x < i.valor) {
            inserirPai(x, i.esq, i);
        } else if (x > i.valor) {
            inserirPai(x, i.dir, i);
        } else {
            throw new Exception("Erro!");
        }
    }

    private boolean pesquisar(int x, No i) {
        boolean resp = false;
        if (i == null) {
            return resp;

        } else if (x < i.valor)// Se x é menor que o valor do nó atual, pesquisa no Nó esquerdo
        {
            return pesquisar(x, i.esq);

        } else if (x > i.valor)// Se x é maior que o valor do nó atual. pesquisa no Nó direito
        {
            return pesquisar(x, i.dir);
        } else {
            resp = true;
        }
        return resp;
    }

    private void caminharPre(No i) {
        if (i == null) {
            return;
        } else {
            System.out.println("Elemento:" + i.valor);

            caminharPre(i.esq);
            caminharPre(i.dir);
        }
    }

    private void caminharCentral(No i) {
        if (i == null) {
            return;
        } else {
            caminharCentral(i.esq);
            System.out.println("Elemento:" + i.valor);
            caminharCentral(i.dir);
        }
    }

    private void caminharPos(No i) {
        if (i == null) {
            return;
        } else {
            caminharPos(i.esq);
            caminharPos(i.dir);
            System.out.println(i.valor);
        }
    }

    private int alturaArvore(No i) // Para achar a altura da arvore deve-se procurar o nó mais distante da raiz
    {
        if (i == null)
            return -1;
        int hEsq = alturaArvore(i.esq);
        int hDir = alturaArvore(i.dir);

        if (hEsq > hDir) {
            return 1 + hEsq;
        }
        return 1 + hDir;
    }

    private int numElementos(No i) {

        if (i == null) {
            return 0;
        }

        return 1 + numElementos(i.esq) + numElementos(i.dir);
    }

    private int getMaior(No i) throws Exception {
        // Na primeira chamada, i = raiz
        if (i == null)
            throw new Exception("Erro! Arvore vazia!");
        if (i.dir == null)
            return i.valor;
        return getMaior(i.dir);
    }

    private int getMenor(No i) throws Exception {
        if (i == null)
            throw new Exception("Erro!");
        if (i.esq == null)
            return i.valor;

        return getMenor(i.esq);
    }

    private No remover(int x, No i) throws Exception {
        if (i == null) {
            throw new Exception("Erro!");
        } else if (x < i.valor) {
            i.esq = remover(x, i.esq);
        } else if (x > i.valor) {
            i.dir = remover(x, i.dir);
        } else if (i.dir == null) {
            i = i.esq;
        } else if (i.esq == null) {
            i = i.dir;
        } else {
            i.esq = maiorEsq(i, i.esq);
        }
        return i;
    }

    private No maiorEsq(No i, No j) {
        if (j.dir == null) {
            i.valor = j.valor;
            j = j.esq;
        } else {
            j.dir = maiorEsq(i, j.dir);
        }

        return j;
    }

    private No rotEsq(No i) {
        No noDir = i.dir;
        No noDirEsq = noDir.esq;
        noDir.esq = i;
        i.dir = noDirEsq;

        return noDir;
    }

    private No rotDir(No i) {
        No noEsq = i.esq;
        No noEsqDir = noEsq.dir;

        noEsq.dir = i;
        i.esq = noEsqDir;

        return noEsq;
    }

    private No rotDirEsq(No i) {
        i.dir = rotDir(i.dir);
        return rotEsq(i);
    }

    private No rotEsqDir(No i) {
        i.esq = rotEsq(i.esq);
        return rotDir(i);
    }

    public static void main(String[] args) {

        Random rand = new Random();

        ArvoreBin arvore = new ArvoreBin();

        int numElementos = 0;

        for (int i = 0; i < 100000; i++) {
            int trueRand = rand.nextInt(100000);
            try {
                arvore.inserir(trueRand);
                numElementos++;
            } catch (Exception e) {
                // Sifoda esse erro :)
            }

        }

        double log2 = Math.log(numElementos) / Math.log(2);

        System.out.println("Altura da arvore: " + arvore.alturaArvore());
        System.out.println("Numero de elementos da arvore: " + numElementos);
        System.out.printf("Log de 2: %.2f \n", log2);

        try {
            System.out.println("Maior Elemento: " + arvore.getMaior());
            System.out.println("Menor Elemento: " + arvore.getMenor());

        } catch (Exception e) {
            // Sifoda esse erro :)
        }

    }

}
