import java.io.*;
import java.util.*;

class Conteudo {
    String id, tipo, nome, diretor, elenco, pais, data, classificacao, duracao, generos;
    int ano;
    Conteudo(String[] campos) {
        id = campo(campos[0]);
        tipo = campo(campos[1]);
        nome = campo(campos[2]);
        diretor = campo(campos[3]);
        elenco = ordenarTokens(campo(campos[4]));
        pais = campo(campos[5]);
        data = campo(campos[6]);
        ano = campos[7].isEmpty() ? -1 : Integer.parseInt(campos[7]);
        classificacao = campo(campos[8]);
        duracao = campo(campos[9]);
        generos = ordenarTokens(campo(campos[10]));
    }
    private String campo(String v) { return v == null || v.isEmpty() ? "NaN" : v; }
    private String ordenarTokens(String campo) {
        if (campo.equals("NaN")) return campo;
        String[] tokens = campo.split(",");
        List<String> lista = new ArrayList<>();
        for (String t : tokens) lista.add(t.strip());
        Collections.sort(lista);
        return String.join(", ", lista);
    }
    void exibir() {
        String a = (ano == -1) ? "NaN" : String.valueOf(ano);
        System.out.printf("=> %s ## %s ## %s ## %s ## [%s] ## %s ## %s ## %s ## %s ## %s ## [%s] ##\n",
            id, nome, tipo, diretor, elenco, pais, data, a, classificacao, duracao, generos);
    }
}

class Elo {
    Conteudo dado;
    Elo ant, prox;
    Elo(Conteudo dado) { this.dado = dado; }
}

public class Q10 {
    static final int LIMITE = 1500;
    static List<Conteudo> acervo = new ArrayList<>();
    static Elo ini = null, ult = null;
    public static void main(String[] args) throws Exception {
        carregar("/tmp/disneyplus.csv");
        Scanner in = new Scanner(System.in);
        String ent;
        while (!(ent = in.nextLine().trim()).equals("FIM")) {
            for (Conteudo c : acervo) {
                if (c.id.equals(ent)) {
                    inserirFim(c);
                    break;
                }
            }
        }
        Conteudo[] arr = paraArray();
        int[] comp = {0}, mov = {0};
        long iniT = System.currentTimeMillis();
        quick(arr, 0, arr.length - 1, comp, mov);
        long fimT = System.currentTimeMillis();
        for (Conteudo c : arr) c.exibir();
        double tempo = fimT - iniT;
        salvar("860144_quicksort3.txt", compGlobal, movGlobal, tempo);
        if (in.hasNextLine()) {
            String busca = in.nextLine().trim();
            int[] compBin = {0};
            buscaBin(arr, busca, compBin);
        }
        in.close();
    }
    static void carregar(String arq) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(arq));
        String l; br.readLine();
        while ((l = br.readLine()) != null && acervo.size() < LIMITE) {
            String[] campos = splitCSV(l);
            if (campos.length >= 11) acervo.add(new Conteudo(campos));
        }
        br.close();
    }
    static String[] splitCSV(String l) {
        List<String> campos = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean aspas = false;
        for (char c : l.toCharArray()) {
            if (c == '"') aspas = !aspas;
            else if (c == ',' && !aspas) {
                campos.add(sb.toString());
                sb.setLength(0);
            } else sb.append(c);
        }
        campos.add(sb.toString());
        return campos.toArray(new String[0]);
    }
    static void inserirFim(Conteudo c) {
        Elo novo = new Elo(c);
        if (ult == null) ini = ult = novo;
        else {
            ult.prox = novo;
            novo.ant = ult;
            ult = novo;
        }
    }
    static Conteudo[] paraArray() {
        List<Conteudo> lista = new ArrayList<>();
        for (Elo p = ini; p != null; p = p.prox) lista.add(p.dado);
        return lista.toArray(new Conteudo[0]);
    }
    static int compGlobal = 0, movGlobal = 0;
    static void quick(Conteudo[] arr, int esq, int dir, int[] comp, int[] mov) {
        if (esq < dir) {
            Conteudo pivo = arr[dir];
            int i = esq - 1;
            for (int j = esq; j < dir; j++) {
                comp[0]++; compGlobal++;
                if (cmp(arr[j], pivo) <= 0) {
                    i++;
                    troca(arr, i, j);
                    mov[0] += 3; movGlobal += 3;
                }
            }
            troca(arr, i + 1, dir);
            mov[0] += 3; movGlobal += 3;
            quick(arr, esq, i, comp, mov);
            quick(arr, i + 2, dir, comp, mov);
        }
    }
    static void troca(Conteudo[] arr, int i, int j) {
        Conteudo tmp = arr[i]; arr[i] = arr[j]; arr[j] = tmp;
    }
    static int cmp(Conteudo a, Conteudo b) {
        long da = dataLong(a.data), db = dataLong(b.data);
        if (da != db) return Long.compare(da, db);
        return a.nome.compareToIgnoreCase(b.nome);
    }
    static long dataLong(String data) {
        if (data.equals("NaN")) return 0;
        try {
            return new java.text.SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).parse(data).getTime();
        } catch (Exception e) { return 0; }
    }
    static int buscaBin(Conteudo[] arr, String nome, int[] comp) {
        int esq = 0, dir = arr.length - 1;
        while (esq <= dir) {
            int meio = (esq + dir) / 2;
            comp[0]++;
            int cmp = arr[meio].nome.compareTo(nome);
            if (cmp == 0) return meio;
            else if (cmp < 0) esq = meio + 1;
            else dir = meio - 1;
        }
        return -1;
    }
    static void salvar(String nome, int comp, int mov, double tempo) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(nome))) {
            pw.printf("860144\t%d\t%d\t%.2f\n", comp, mov, tempo);
        } catch (IOException e) { e.printStackTrace(); }
    }
}