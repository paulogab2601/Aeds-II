package BeeCrowd.Java;

import java.util.Scanner;

/*
 * Dada uma expressão com parênteses, imprima uma mensagem informando se a
 * quantidade de parênteses está correta ou incorreta, sem considerar o restante
 * da expressão.
 * 
 * Todo parêntese de fechamento deve ter um parêntese de abertura correspondente
 * antes dele;
 * 
 * Não é permitido haver um parêntese de fechamento sem um de abertura anterior;
 * 
 * E o número total de parênteses de abertura e fechamento deve ser igual.
 */

class Celula {
    char parentese;
    Celula prox;

    Celula(char parentese) {
        this.parentese = parentese;
        this.prox = null;
    }

    Celula() {
        this.prox = null;
    }
}

class Pilha {
    Celula topo = new Celula();

    Pilha() {
        topo = null;
    }

    public void inserir(char x) {
        // Somente os parenteses sao inseridos na pilha
        Celula tmp = new Celula(x);
        tmp.prox = topo;
        topo = tmp;

        tmp = null;
    }

    public boolean remover() {
        if (topo == null) {
            return false;
        }
        topo = topo.prox;
        return true;
    }

    public boolean isEmpty() {
        return topo == null;
    }

    public void limparPilha() {
        while (!isEmpty()) {
            remover();
        }
    }

    public int numElemetos() {
        int count = 0;
        for (Celula i = topo; i != null; i = i.prox) {
            count++;
        }
        return count;
    }
}

public class Bee1068 {

    public static boolean verificaExpressao(String expressao) {

        Pilha pilha = new Pilha();

        for (int i = 0; i < expressao.length(); i++) {
            char c = expressao.charAt(i);

            if (c == '(') {
                pilha.inserir(c);
            } else if (c == ')') {
                if (!pilha.remover()) {
                    return false;
                }
            }
        }

        return pilha.isEmpty();

    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String linha = sc.nextLine();

            if (verificaExpressao(linha)) {
                System.out.println("correta");
            } else {
                System.out.println("incorreta");
            }
        }
        sc.close();
    }
}
