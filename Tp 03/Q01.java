import java.io.*;
import java.text.*;
import java.util.*;

public class Q01 {

    static class Programa implements Cloneable {
        private String id;
        private String categoria;
        private String nome;
        private String diretor;
        private String[] elenco;
        private String pais;
        private Date dataAdicao;
        private int anoLancamento;
        private String classificacao;
        private String tempo;
        private String[] generos;

        public Programa() {
            id = "NaN";
            categoria = "NaN";
            nome = "NaN";
            diretor = "NaN";
            elenco = new String[]{"NaN"};
            pais = "NaN";
            dataAdicao = null;
            anoLancamento = -1;
            classificacao = "NaN";
            tempo = "NaN";
            generos = new String[]{"NaN"};
        }

        public Programa(String id, String categoria, String nome, String diretor, String[] elenco, String pais, Date dataAdicao, int anoLancamento, String classificacao, String tempo, String[] generos) {
            this.id = id;
            this.categoria = categoria;
            this.nome = nome;
            this.diretor = diretor;
            this.elenco = elenco;
            this.pais = pais;
            this.dataAdicao = dataAdicao;
            this.anoLancamento = anoLancamento;
            this.classificacao = classificacao;
            this.tempo = tempo;
            this.generos = generos;
        }

        public String obterNome() { return nome; }

        public Programa clone() {
            return new Programa(id, categoria, nome, diretor, elenco.clone(), pais, dataAdicao, anoLancamento, classificacao, tempo, generos.clone());
        }

        public void exibir() {
            SimpleDateFormat formato = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
            String data = (dataAdicao != null) ? formato.format(dataAdicao) : "NaN";
            String[] elencoOrdenado = elenco.clone();
            Arrays.sort(elencoOrdenado, String.CASE_INSENSITIVE_ORDER);
            String nomeLimpo = nome.replaceAll("\"", "");
            for (int i = 0; i < elencoOrdenado.length; i++) {
                elencoOrdenado[i] = elencoOrdenado[i].replaceAll("\"", "");
            }
            System.out.println("=> " + id + " ## " + nomeLimpo + " ## " + categoria + " ## " + diretor +
                    " ## " + Arrays.toString(elencoOrdenado) + " ## " + pais + " ## " + data + " ## " +
                    anoLancamento + " ## " + classificacao + " ## " + tempo + " ## " + Arrays.toString(generos) + " ##");
        }

        public void carregar(String linha) {
            SimpleDateFormat formato = new SimpleDateFormat("MMM dd, yyyy");
            String[] campos = new String[12];
            String[] partes = linha.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
            for (int i = 0; i < campos.length; i++) {
                campos[i] = (i < partes.length && !partes[i].trim().isEmpty()) ? partes[i].trim().replaceAll("^\"|\"$", "") : "NaN";
            }
            id = campos[0];
            categoria = campos[1];
            nome = campos[2];
            diretor = campos[3];
            elenco = campos[4].equals("NaN") ? new String[]{"NaN"} : campos[4].split(", ");
            pais = campos[5];
            try {
                dataAdicao = campos[6].equals("NaN") ? null : formato.parse(campos[6]);
            } catch (ParseException e) {
                dataAdicao = null;
            }
            anoLancamento = campos[7].equals("NaN") ? -1 : Integer.parseInt(campos[7]);
            classificacao = campos[8];
            tempo = campos[9];
            generos = campos[10].equals("NaN") ? new String[]{"NaN"} : campos[10].split(", ");
        }
    }

    static class VetorProgramas {
        private Programa[] elementos;
        private int tamanho;

        public VetorProgramas(int capacidade) {
            elementos = new Programa[capacidade];
            tamanho = 0;
        }

        public void adicionarInicio(Programa prog) throws Exception {
            if (tamanho >= elementos.length) throw new Exception("Lista cheia.");
            for (int i = tamanho; i > 0; i--) elementos[i] = elementos[i - 1];
            elementos[0] = prog.clone();
            tamanho++;
        }

        public void adicionarFim(Programa prog) throws Exception {
            if (tamanho >= elementos.length) throw new Exception("Lista cheia.");
            elementos[tamanho++] = prog.clone();
        }

        public void adicionar(Programa prog, int pos) throws Exception {
            if (tamanho >= elementos.length || pos < 0 || pos > tamanho) throw new Exception("Posição inválida.");
            for (int i = tamanho; i > pos; i--) elementos[i] = elementos[i - 1];
            elementos[pos] = prog.clone();
            tamanho++;
        }

        public Programa removerInicio() throws Exception {
            if (tamanho == 0) throw new Exception("Lista vazia.");
            Programa resp = elementos[0];
            for (int i = 0; i < tamanho - 1; i++) elementos[i] = elementos[i + 1];
            tamanho--;
            return resp;
        }

        public Programa removerFim() throws Exception {
            if (tamanho == 0) throw new Exception("Lista vazia.");
            return elementos[--tamanho];
        }

        public Programa remover(int pos) throws Exception {
            if (tamanho == 0 || pos < 0 || pos >= tamanho) throw new Exception("Posição inválida.");
            Programa resp = elementos[pos];
            for (int i = pos; i < tamanho - 1; i++) elementos[i] = elementos[i + 1];
            tamanho--;
            return resp;
        }

        public void listar() {
            for (int i = 0; i < tamanho; i++) {
                elementos[i].exibir();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Map<String, String> registros = new HashMap<>();
        BufferedReader leitor = new BufferedReader(new FileReader("/tmp/disneyplus.csv"));
        leitor.readLine();
        String linha;
        while ((linha = leitor.readLine()) != null) {
            String chave = linha.split(",")[0];
            registros.put(chave, linha);
        }
        leitor.close();

        Scanner entrada = new Scanner(System.in);
        VetorProgramas lista = new VetorProgramas(1000);

        String valor;
        while (!(valor = entrada.nextLine()).equals("FIM")) {
            if (registros.containsKey(valor)) {
                Programa prog = new Programa();
                prog.carregar(registros.get(valor));
                try {
                    lista.adicionarFim(prog);
                } catch (Exception e) {
                    System.out.println("Erro ao inserir: " + e.getMessage());
                }
            } else {
                System.out.println("=> NaN ## NaN ## NaN ## NaN ## [NaN] ## NaN ## NaN ## -1 ## NaN ## NaN ## [NaN] ##");
            }
        }

        int quantidade = Integer.parseInt(entrada.nextLine());
        for (int i = 0; i < quantidade; i++) {
            String comando = entrada.nextLine();
            String[] partes = comando.split(" ");

            try {
                switch (partes[0]) {
                    case "II": {
                        Programa p = new Programa();
                        p.carregar(registros.get(partes[1]));
                        lista.adicionarInicio(p);
                        break;
                    }
                    case "IF": {
                        Programa p = new Programa();
                        p.carregar(registros.get(partes[1]));
                        lista.adicionarFim(p);
                        break;
                    }
                    case "I*": {
                        int pos = Integer.parseInt(partes[1]);
                        Programa p = new Programa();
                        p.carregar(registros.get(partes[2]));
                        lista.adicionar(p, pos);
                        break;
                    }
                    case "RI": {
                        Programa removido = lista.removerInicio();
                        System.out.println("(R) " + removido.obterNome());
                        break;
                    }
                    case "RF": {
                        Programa removido = lista.removerFim();
                        System.out.println("(R) " + removido.obterNome());
                        break;
                    }
                    case "R*": {
                        int pos = Integer.parseInt(partes[1]);
                        Programa removido = lista.remover(pos);
                        System.out.println("(R) " + removido.obterNome());
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }

        lista.listar();
        entrada.close();
    }
}