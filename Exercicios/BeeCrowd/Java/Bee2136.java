package BeeCrowd.Java;

import java.util.*;


/*Todo final de ano acontece uma festa na Instituição de Ensino Fantástico (IEF). No início de julho, as inscrições são abertas para participar da festa. No momento da inscrição, o usuário pode escolher se deseja ser "O Amigo do Habay" na festa ou não. A escolha mais lógica seria Sim, pois é um privilégio ser O Amigo do Habay, já que ele é a pessoa mais legal da IEF. No entanto, há algumas pessoas que definitivamente não querem ser O Amigo do Habay, por motivos desconhecidos.

Apenas um será escolhido. Como resultado, muitos alunos que escolheram a opção Sim se inscreveram várias vezes para aumentar suas chances de serem escolhidos como O Amigo do Habay. O organizador da festa contratou você para organizar o sistema de inscrições do site, já que está havendo um spam de inscrições. O critério para ser o escolhido é a quantidade de letras do primeiro nome, e, se houver mais de um nome com a mesma quantidade de letras, ganha quem se inscreveu primeiro. A organização final dos inscritos deve seguir a ordem da escolha (YES ou NO), respeitando a ordem alfabética.

Nota: Ninguém que escolheu a opção NO se inscreveu mais de uma vez.

Entrada
A entrada consiste em um único caso de teste. Cada linha consiste no primeiro nome do usuário (sem espaços), seguido da opção YES (caso o usuário queira ser O Amigo do Habay) ou NO (caso o usuário não queira). Leia as entradas até o usuário digitar "FIM" (sem as aspas).

Saída
Imprima os usuários inscritos na ordem da escolha, respeitando a ordem alfabética. Em seguida, imprima o nome do vencedor. Imprima uma linha em branco entre a lista de inscritos e o nome do vencedor. */

public class Bee2136 {

    static class Participante {
        String nome;
        String escolha;
        int ordem; // para manter a ordem de chegada

        Participante(String nome, String escolha, int ordem) {
            this.nome = nome;
            this.escolha = escolha;
            this.ordem = ordem;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Participante> sim = new ArrayList<>();
        List<Participante> nao = new ArrayList<>();
        Set<String> registrados = new HashSet<>();

        int ordemChegada = 0;

        while (true) {
            String linha = sc.nextLine();
            if (linha.equals("FIM")) break;

            String[] partes = linha.split("\\s+");
            String nome = partes[0];
            String escolha = partes[1];

            if (escolha.equals("YES")) {
                // Pode haver nomes repetidos aqui, então só adiciona se ainda não foi registrado esse nome como YES
                if (!registrados.contains(nome)) {
                    sim.add(new Participante(nome, escolha, ordemChegada++));
                    registrados.add(nome);
                }
            } else {
                nao.add(new Participante(nome, escolha, ordemChegada++));
            }
        }

        // Ordenar os dois grupos em ordem alfabética
        Comparator<Participante> ordemAlfabetica = Comparator.comparing(p -> p.nome);
        sim.sort(ordemAlfabetica);
        nao.sort(ordemAlfabetica);

        for (Participante p : sim) {
            System.out.println(p.nome);
        }
        for (Participante p : nao) {
            System.out.println(p.nome);
        }

        System.out.println();

        // Determinar o vencedor entre os que disseram "YES"
        Participante vencedor = null;
        for (Participante p : sim) {
            if (vencedor == null || p.nome.length() > vencedor.nome.length() ||
                (p.nome.length() == vencedor.nome.length() && p.ordem < vencedor.ordem)) {
                vencedor = p;
            }
        }

        if (vencedor != null) {
            System.out.println("Amigo do Habay:");
            System.out.println(vencedor.nome);
        }

        sc.close();
    }
}

