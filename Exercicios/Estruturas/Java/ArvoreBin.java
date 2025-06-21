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

        } // De acordo com o GPT a logica esta correta

        double log2 = Math.log(numElementos) / Math.log(2);

        System.out.println("Altura da arvore: " + arvore.alturaArvore());
        System.out.println("Numero de elementos da arvore: " + numElementos);
        System.out.println("Log de 2: " + log2);

        arvore.caminharCentral();

    }

}
