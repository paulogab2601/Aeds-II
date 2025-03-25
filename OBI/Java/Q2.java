package OBI.Java;

import java.util.Scanner;

/*
 *  Cláudia trabalha na OBI (Organização dos Bons Informáticos), que recentemente realizou um con
curso para contratar novos funcionários. Agora, Cláudia tem a tarefa de determinar a nota de
 corte para o concurso. Chamamos de nota de corte a nota mínima necessária para ser aprovado no
 concurso. Ou seja, se a nota de corte do concurso for C, então todos os participantes com uma nota
 maior ou igual a C serão aprovados no concurso e todos com nota menor que C serão reprovados.
 Seu chefe pediu para que Cláudia aprove no mínimo K candidatos do concurso para a próxima fase,
 mas ela também não quer que a nota de corte seja muito baixa. Por isso, Cláudia decidiu que a
 nota de corte deverá ser a maior nota C que faz com que no mínimo K candidatos sejam aprovados.
 Sua tarefa é: dados o número N de candidatos, as notas A1,A2,...,AN dos candidatos e a quantidade
 mínima de aprovados K, diga qual deve ser a maior nota de corte C para que pelo menos K
 candidatos sejam aprovados.
 Entrada
 Aprimeira linha da entrada contém dois inteiros, N e K, representando, respectivamente, o número
 de participantes e o número mínimo de candidatos que devem ser aprovados.
 A segunda linha da entrada contém N inteiros Ai, representando as notas dos participantes.
 Saída
 Seu programa deve imprimir uma linha contendo um único inteiro C, a nota de corte que deve ser
 escolhida por Cláudia.
 Restrições
 • 1≤K≤N≤500
 • 1≤Ai ≤100 para todo 1 ≤ i ≤N
 Informações sobre a pontuação
 A tarefa vale 100 pontos. Estes pontos estão distribuídos em subtarefas, cada uma com suas
 restrições adicionais às definidas acima.
 • Subtarefa 1 (0 pontos): Esta subtarefa é composta apenas pelos exemplos mostrados
 abaixo. Ela não vale pontos, serve apenas para que você verifique se o seu programa imprime
 o resultado correto para os exemplos.
 • Subtarefa 2 (20 pontos): K = 1.
 • Subtarefa 3 (20 pontos): K = 3.
 • Subtarefa 4 (20 pontos): Ai ≤ 2.
 • Subtarefa 5 (40 pontos): Sem restrições adicionais
 */

public class Q2 {

    public static int calculaNota(int k, int[] candidatos) {

        bubbleSort(candidatos);

        return candidatos[candidatos.length - k];
    }

    public static void bubbleSort(int[] arr) // Ordena o vetor
    {
        int n = arr.length;
        boolean trocou;

        do {
            trocou = false;
            for (int i = 0; i < n - 1; i++) {
                if (arr[i] > arr[i + 1]) {
                    // Troca os elementos
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    trocou = true;
                }
            }
        } while (trocou);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {

            int n = sc.nextInt();
            int k = sc.nextInt();

            int[] arr = new int[n];

            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }
            n = calculaNota(k, arr);
            System.out.println(n);
        }
        sc.close();
    }
}
