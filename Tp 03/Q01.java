import java.io.*;
import java.text.*;
import java.util.*;

public class Q01 {

    static class Programa implements Cloneable {
        private String identificador;
        private String categoria;
        private String nome;
        private String diretor;
        private String[] elenco;
        private String pais;
        private Date dataAdicao;
        private int anoLancamento;
        private String classificacao;
        private String duracao;
        private String[] generos;

        public Programa() {
            identificador = "NaN";
            categoria = "NaN";
            nome = "NaN";
            diretor = "NaN";
            elenco = new String[] { "NaN" };
            pais = "NaN";
            dataAdicao = null;
            anoLancamento = -1;
            classificacao = "NaN";
            duracao = "NaN";
            generos = new String[] { "NaN" };
        }

        public Programa(String identificador, String categoria, String nome, String diretor, String[] elenco,
                String pais, Date dataAdicao, int anoLancamento, String classificacao, String duracao,
                String[] generos) {
            this.identificador = identificador;
            this.categoria = categoria;
            this.nome = nome;
            this.diretor = diretor;
            this.elenco = elenco;
            this.pais = pais;
            this.dataAdicao = dataAdicao;
            this.anoLancamento = anoLancamento;
            this.classificacao = classificacao;
            this.duracao = duracao;
            this.generos = generos;
        }

        public String getNome() {
            return nome;
        }

        public Programa clone() {
            return new Programa(identificador, categoria, nome, diretor, elenco.clone(), pais, dataAdicao,
                    anoLancamento, classificacao, duracao, generos.clone());
        }

        public void exibir() {
            SimpleDateFormat formatoData = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
            String dataFormatada = (dataAdicao != null) ? formatoData.format(dataAdicao) : "NaN";
            String[] elencoOrdenado = elenco.clone();
            Arrays.sort(elencoOrdenado, String.CASE_INSENSITIVE_ORDER);
            String nomeLimpo = nome.replaceAll("\"", "");
            for (int i = 0; i < elencoOrdenado.length; i++) {
                elencoOrdenado[i] = elencoOrdenado[i].replaceAll("\"", "");
            }
            System.out.println("=> " + identificador + " ## " + nomeLimpo + " ## " + categoria + " ## " + diretor +
                    " ## " + Arrays.toString(elencoOrdenado) + " ## " + pais + " ## " + dataFormatada + " ## " +
                    anoLancamento + " ## " + classificacao + " ## " + duracao + " ## " + Arrays.toString(generos)
                    + " ##");
        }

        public void carregar(String linha) {
            SimpleDateFormat formatoData = new SimpleDateFormat("MMM dd, yyyy");
            String[] campos = new String[12];
            String[] partes = linha.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
            for (int i = 0; i < campos.length; i++) {
                campos[i] = (i < partes.length && !partes[i].trim().isEmpty())
                        ? partes[i].trim().replaceAll("^\"|\"$", "")
                        : "NaN";
            }
            identificador = campos[0];
            categoria = campos[1];
            nome = campos[2];
            diretor = campos[3];
            elenco = campos[4].equals("NaN") ? new String[] { "NaN" } : campos[4].split(", ");
            pais = campos[5];
            try {
                dataAdicao = campos[6].equals("NaN") ? null : formatoData.parse(campos[6]);
            } catch (ParseException e) {
                dataAdicao = null;
            }
            anoLancamento = campos[7].equals("NaN") ? -1 : Integer.parseInt(campos[7]);
            classificacao = campos[8];
            duracao = campos[9];
            generos = campos[10].equals("NaN") ? new String[] { "NaN" } : campos[10].split(", ");
        }
    }

    static class ListaProgramas {
        private Programa[] vetor;
        private int tamanho;

        public ListaProgramas(int capacidade) {
            vetor = new Programa[capacidade];
            tamanho = 0;
        }

        public void inserirInicio(Programa programa) throws Exception {
            if (tamanho >= vetor.length)
                throw new Exception("Lista cheia.");
            for (int i = tamanho; i > 0; i--)
                vetor[i] = vetor[i - 1];
            vetor[0] = programa.clone();
            tamanho++;
        }

        public void inserirFim(Programa programa) throws Exception {
            if (tamanho >= vetor.length)
                throw new Exception("Lista cheia.");
            vetor[tamanho++] = programa.clone();
        }

        public void inserir(Programa programa, int posicao) throws Exception {
            if (tamanho >= vetor.length || posicao < 0 || posicao > tamanho)
                throw new Exception("Posição inválida.");
            for (int i = tamanho; i > posicao; i--)
                vetor[i] = vetor[i - 1];
            vetor[posicao] = programa.clone();
            tamanho++;
        }

        public Programa removerInicio() throws Exception {
            if (tamanho == 0)
                throw new Exception("Lista vazia.");
            Programa removido = vetor[0];
            for (int i = 0; i < tamanho - 1; i++)
                vetor[i] = vetor[i + 1];
            tamanho--;
            return removido;
        }

        public Programa removerFim() throws Exception {
            if (tamanho == 0)
                throw new Exception("Lista vazia.");
            return vetor[--tamanho];
        }

        public Programa remover(int posicao) throws Exception {
            if (tamanho == 0 || posicao < 0 || posicao >= tamanho)
                throw new Exception("Posição inválida.");
            Programa removido = vetor[posicao];
            for (int i = posicao; i < tamanho - 1; i++)
                vetor[i] = vetor[i + 1];
            tamanho--;
            return removido;
        }

        public void listar() {
            for (int i = 0; i < tamanho; i++) {
                vetor[i].exibir();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Map<String, String> bancoDados = new HashMap<>();
        BufferedReader leitorCSV = new BufferedReader(new FileReader("/tmp/disneyplus.csv"));
        leitorCSV.readLine();
        String linhaCSV;
        while ((linhaCSV = leitorCSV.readLine()) != null) {
            String chave = linhaCSV.split(",")[0];
            bancoDados.put(chave, linhaCSV);
        }
        leitorCSV.close();

        Scanner leitorEntrada = new Scanner(System.in);
        ListaProgramas lista = new ListaProgramas(1000);

        String entrada;
        while (!(entrada = leitorEntrada.nextLine()).equals("FIM")) {
            if (bancoDados.containsKey(entrada)) {
                Programa programa = new Programa();
                programa.carregar(bancoDados.get(entrada));
                try {
                    lista.inserirFim(programa);
                } catch (Exception e) {
                    System.out.println("Erro ao inserir: " + e.getMessage());
                }
            } else {
                System.out
                        .println("=> NaN ## NaN ## NaN ## NaN ## [NaN] ## NaN ## NaN ## -1 ## NaN ## NaN ## [NaN] ##");
            }
        }

        int quantidadeComandos = Integer.parseInt(leitorEntrada.nextLine());
        for (int i = 0; i < quantidadeComandos; i++) {
            String comando = leitorEntrada.nextLine();
            String[] partes = comando.split(" ");

            try {
                switch (partes[0]) {
                    case "II": {
                        Programa programa = new Programa();
                        programa.carregar(bancoDados.get(partes[1]));
                        lista.inserirInicio(programa);
                        break;
                    }
                    case "IF": {
                        Programa programa = new Programa();
                        programa.carregar(bancoDados.get(partes[1]));
                        lista.inserirFim(programa);
                        break;
                    }
                    case "I*": {
                        int posicao = Integer.parseInt(partes[1]);
                        Programa programa = new Programa();
                        programa.carregar(bancoDados.get(partes[2]));
                        lista.inserir(programa, posicao);
                        break;
                    }
                    case "RI": {
                        Programa removido = lista.removerInicio();
                        System.out.println("(R) " + removido.getNome());
                        break;
                    }
                    case "RF": {
                        Programa removido = lista.removerFim();
                        System.out.println("(R) " + removido.getNome());
                        break;
                    }
                    case "R*": {
                        int posicao = Integer.parseInt(partes[1]);
                        Programa removido = lista.remover(posicao);
                        System.out.println("(R) " + removido.getNome());
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }

        lista.listar();
        leitorEntrada.close();
    }
}
