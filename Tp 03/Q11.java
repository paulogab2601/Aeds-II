import java.util.Scanner;

class MatrizFlex {
    int[][] dados;
    int nLin, nCol;

    public MatrizFlex(int nLin, int nCol) {
        this.nLin = nLin;
        this.nCol = nCol;
        this.dados = new int[nLin][nCol];
    }

    public void preencher(Scanner entrada) {
        for (int i = 0; i < nLin; i++) {
            for (int j = 0; j < nCol; j++) {
                dados[i][j] = entrada.nextInt();
            }
        }
    }

    public void exibeDiagPrincipal() {
        int limite = Math.min(nLin, nCol);
        for (int i = 0; i < limite; i++) {
            System.out.print(dados[i][i]);
            if (i < limite - 1) System.out.print(" ");
        }
        System.out.println();
    }

    public void exibeDiagSecundaria() {
        int limite = Math.min(nLin, nCol);
        for (int i = 0; i < limite; i++) {
            System.out.print(dados[i][nCol - i - 1]);
            if (i < limite - 1) System.out.print(" ");
        }
        System.out.println();
    }

    public MatrizFlex somar(MatrizFlex outra) {
        if (this.nLin != outra.nLin || this.nCol != outra.nCol) {
            throw new IllegalArgumentException("Dimensões incompatíveis para soma.");
        }
        MatrizFlex res = new MatrizFlex(nLin, nCol);
        for (int i = 0; i < nLin; i++) {
            for (int j = 0; j < nCol; j++) {
                res.dados[i][j] = this.dados[i][j] + outra.dados[i][j];
            }
        }
        return res;
    }

    public MatrizFlex multiplicar(MatrizFlex outra) {
        if (this.nCol != outra.nLin) {
            throw new IllegalArgumentException("Dimensões incompatíveis para multiplicação.");
        }
        MatrizFlex res = new MatrizFlex(this.nLin, outra.nCol);
        for (int i = 0; i < this.nLin; i++) {
            for (int j = 0; j < outra.nCol; j++) {
                for (int k = 0; k < this.nCol; k++) {
                    res.dados[i][j] += this.dados[i][k] * outra.dados[k][j];
                }
            }
        }
        return res;
    }

    public void exibir() {
        for (int i = 0; i < nLin; i++) {
            for (int j = 0; j < nCol; j++) {
                System.out.print(dados[i][j]);
                if (j < nCol - 1) System.out.print(" ");
            }
            System.out.println();
        }
    }
}

public class Q11 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int testes = in.nextInt();
        for (int caso = 0; caso < testes; caso++) {
            int l1 = in.nextInt();
            int c1 = in.nextInt();
            MatrizFlex a = new MatrizFlex(l1, c1);
            a.preencher(in);
            int l2 = in.nextInt();
            int c2 = in.nextInt();
            MatrizFlex b = new MatrizFlex(l2, c2);
            b.preencher(in);
            a.exibeDiagPrincipal();
            a.exibeDiagSecundaria();
            try {
                MatrizFlex soma = a.somar(b);
                soma.exibir();
            } catch (IllegalArgumentException e) {
                System.out.println("Erro na soma: " + e.getMessage());
            }
            try {
                MatrizFlex mult = a.multiplicar(b);
                mult.exibir();
            } catch (IllegalArgumentException e) {
                System.out.println("Erro na multiplicação: " + e.getMessage());
            }
        }
        in.close();
    }
}