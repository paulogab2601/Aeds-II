class Contato {
    String nome;
    String telefone;
    String email;
    int CPF;
}

class Celula {
    Contato contato;
    Celula prox;

    Celula(Contato contato) {
        this.prox = null;
        this.contato = contato;
    }

    Celula() {
        this.prox = null;
    }
}

class No {
    char letra;
    No esq, dir;
    Celula primeiro, ultimo;

    No(char letra) {
        this.letra = letra;
        this.esq = this.dir = null;
    }
}

public class Agenda {
    No raiz;

    Agenda() {
        preencherArvoreCompleta();
    }

    // Metodos publicos

    public void inserir(Contato contato) {
        raiz = inserirContato(contato, raiz);
    }

    public Contato remover(String nome) {
        return remover(nome, raiz);
    }

    public boolean pesquisarNome(String nome) {
        return pesquisarNome(raiz, nome);
    }

    public boolean pesquisarCPF(int CPF) {
        return pesquisarCPF(raiz, CPF);
    }

    // Metodos privados
    private No inserirContato(Contato contato, No i) {
        if (i == null) {
            return i;
        } /*
           * i nunca é NULL, porque o todas as letras do alfabeto já sao inseridas na
           * Arvore Bin da Agenda, assim que a Agenda é instanciada
           */
        if (i.letra < contato.nome.charAt(0)) {
            i.dir = inserirContato(contato, i.dir);
        } else if (i.letra > contato.nome.charAt(0)) {
            i.esq = inserirContato(contato, i.esq);
        } else {
            // Insere na lista
            Celula nova = new Celula(contato);

            if (i.primeiro == null) {
                // Cria o nó cabeça e inicia a lista
                i.primeiro = new Celula(); // nó cabeça vazio
                i.primeiro.prox = nova;
                i.ultimo = nova;
            } else {
                i.ultimo.prox = nova;
                i.ultimo = nova;
            }
        }
        return i;
    }

    private Contato remover(String nome, No i) { // Implementar
        return i.primeiro.contato;
    }

    /* Pesquina na Arvore Binaria: NOME */
    private boolean pesquisarNome(No i, String nome) {

        if (i == null) {
            return false;
        } else if (i.letra < nome.charAt(0)) {
            return pesquisarNome(i.dir, nome);
        } else if (i.letra > nome.charAt(0)) {
            return pesquisarNome(i.esq, nome);
        } else {
            /* Achou a letra! | Vamos pesquisar na lista agora */
            return pesquisarLista(i.primeiro, i.ultimo, nome);
        }

    }
    /* */

    /* Pesquisa na Lista: NOME */
    private boolean pesquisarLista(Celula i, Celula j, String nome) {
        boolean resultado = false;

        if (i == null || i.prox == null) { // Já começa verificando na 1° celula válida
            return resultado;
        } else {
            for (Celula k = i.prox; k != j.prox; k = k.prox) {
                if (k.contato.nome.equals(nome)) {
                    resultado = true;
                    return resultado;
                }
            }
        }
        return resultado;
    }

    /* Pesquisa na Lista CPF: */
    private boolean pesquisarCPF(No i, int CPF) {
        if (i == null) {
            return false;
        }
        if (pesquisarLista(i.primeiro, i.ultimo, CPF)) {
            return true;
        }

        return pesquisarCPF(i.dir, CPF) || pesquisarCPF(i.esq, CPF);
    }

    /* Pesquina na Lista: CPF */
    private boolean pesquisarLista(Celula i, Celula j, int CPF) {
        boolean resultado = false;

        if (i == null || i.prox == null) {
            return resultado;
        } else {
            for (Celula k = i.prox; k != j.prox; k = k.prox) {
                if (k.contato.CPF == CPF) {
                    resultado = true;
                    return resultado;
                }
            }
        }
        return resultado;
    }

    /* Preenche a Arvore com as letras */
    private void preencherArvoreCompleta() {
        for (char letra = 'A'; letra <= 'Z'; letra++) {
            raiz = inserirLetra(raiz, letra);
        }
    }

    private No inserirLetra(No i, char letra) {
        if (i == null) {
            return new No(letra);
        }

        if (letra < i.letra) {
            i.esq = inserirLetra(i.esq, letra);
        } else if (letra > i.letra) {
            i.dir = inserirLetra(i.dir, letra);
        }
        return i;
    }
    /* */

    /* Main: */
    public static void main(String[] args) {

    }
}
