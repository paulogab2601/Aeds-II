import java.util.*;

public class Questao1 {

    public static boolean ehPalindromo(List<Integer> lista) {

        boolean ehPali = true;
        int j = lista.size();

        for (int i = 0; i < lista.size()-1; i++) {
            if (lista.get(i).equals(lista.get(j - i + 1))) {
                ehPali = false;
            }
        }

        return ehPali;
    }

    public static int contracao(List<Integer> lista) {

        int contracoes = 0;
        int i = 0;

        while (!ehPalindromo(lista)) {
            contracoes++;
            if(lista.get(i) == lista.get(lista.size()-i+1) + lista.get(lista.size()-i)){
                lista.add(i+1, lista.get(lista.size()-i+1) + lista.get(lista.size()-i));
            }
            
        }

        return contracoes;
    }

    public static void main(String[] args) {
        List<Integer> lista = new ArrayList<>();

        Scanner sc = new Scanner(System.in);

        int tam = sc.nextInt();

        for (int i = 0; i < tam; i++) {
            lista.add(sc.nextInt());
        }

        System.out.println(contracao(lista));


        sc.close();
    }
}
