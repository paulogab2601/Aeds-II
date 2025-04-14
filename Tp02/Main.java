import java.io.*;
import java.util.*;
import java.text.*;

class Show {
    // Atributos da classe
    private String showId;
    private String type;
    private String title;
    private String director;
    private String[] cast;
    private String country;
    private Date dateAdded;
    private int releaseYear;
    private String rating;
    private String duration;
    private String[] listedIn;

    // Construtor padrão, ja inicia tudo com NaN pro caso de n ter preenchimento das
    // partes
    public Show() {
        this.showId = "NaN";
        this.type = "NaN";
        this.title = "NaN";
        this.director = "NaN";
        this.cast = new String[] { "NaN" };
        this.country = "NaN";
        this.dateAdded = parseDate("March 1, 1900");
        this.releaseYear = -1;
        this.rating = "NaN";
        this.duration = "NaN";
        this.listedIn = new String[] { "NaN" };
    }

    // Construtor com parâmetros
    public Show(String showId, String type, String title, String director, String[] cast, String country,
            String dateAdded, int releaseYear, String rating, String duration, String[] listedIn) {
        this.showId = showId != null ? showId : "NaN";
        this.type = type != null ? type : "NaN";
        this.title = title != null ? title : "NaN";
        this.director = director != null ? director : "NaN";
        this.cast = cast != null ? cast : new String[] { "NaN" };
        this.country = country != null ? country : "NaN";
        this.dateAdded = dateAdded != null ? parseDate(dateAdded) : parseDate("March 1, 1900");
        this.releaseYear = releaseYear;
        this.rating = rating != null ? rating : "NaN";
        this.duration = duration != null ? duration : "NaN";
        this.listedIn = listedIn != null ? listedIn : new String[] { "NaN" };

        // Ordenar os arrays
        Arrays.sort(this.cast);
        Arrays.sort(this.listedIn);
    }

    // Métodos geters e seters
    public String getShowId() {
        return showId;
    }

    public void setShowId(String showId) {
        this.showId = showId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String[] getCast() {
        return cast;
    }

    public void setCast(String[] cast) {
        this.cast = cast;
        Arrays.sort(this.cast);
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = parseDate(dateAdded);
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String[] getListedIn() {
        return listedIn;
    }

    public void setListedIn(String[] listedIn) {
        this.listedIn = listedIn;
        Arrays.sort(this.listedIn);
    }

    @Override
    public Show clone() {
        Show cloned = new Show();
        cloned.setShowId(this.showId);
        cloned.setType(this.type);
        cloned.setTitle(this.title);
        cloned.setDirector(this.director);

        cloned.setCast(this.cast.clone());
        cloned.setCountry(this.country);
        cloned.setDateAdded(new SimpleDateFormat("MMMM d, yyyy").format(this.dateAdded));
        cloned.setReleaseYear(this.releaseYear);
        cloned.setRating(this.rating);
        cloned.setDuration(this.duration);
        cloned.setListedIn(this.listedIn.clone());

        return cloned;
    }

    // Método de impressão
    public void imprimir() {
        String formattedDate = (dateAdded != null)
                ? new SimpleDateFormat("MMMM d, yyyy").format(dateAdded)
                : "NaN";

        String castString = (cast != null && cast.length > 0 && !(cast.length == 1 && cast[0].equals("NaN")))
                ? Arrays.toString(cast)
                : "[NaN]";

        String listedInString = (listedIn != null && listedIn.length > 0 && !listedIn[0].equals("NaN"))
                ? Arrays.toString(listedIn)
                : "[NaN]";

        System.out.println(" => "
                + (showId != null && !showId.isEmpty() ? showId : "NaN") + " ## "
                + (type != null && !type.isEmpty() ? type : "NaN") + " ## "
                + (title != null && !title.isEmpty() ? title : "NaN")
                + " ## "
                + (director != null && !director.isEmpty() ? director : "NaN") + " ## "
                + castString + " ## "
                + (country != null && !country.isEmpty() ? country : "NaN") + " ## "
                + formattedDate + " ## "
                + (releaseYear != -1 ? releaseYear : "NaN") + " ## "
                + (rating != null && !rating.isEmpty() ? rating : "NaN") + " ## "
                + (duration != null && !duration.isEmpty() ? duration : "NaN") + " ## "
                + listedInString + " ## ");
    }

    // Método leitura do CSV (Funcionou dps horas quebrando a cara)
    public void ler(String input) {
        String[] fields = input.split("##");
        this.showId = fields[0].trim();
        this.title = fields[1].trim();
        this.type = fields[2].trim();
        this.director = fields[3].trim();
        this.cast = fields[4].trim().replaceAll("[\\[\\]]", "").split(", ");
        this.country = fields[5].trim();

        // Verifica se a data é "NaN" e atribui a data padrão
        String dateStr = fields[6].trim();
        this.dateAdded = dateStr.equals("NaN") ? parseDate("March 1, 1900") : parseDate(dateStr);

        this.releaseYear = Integer.parseInt(fields[7].trim());
        this.rating = fields[8].trim();
        this.duration = fields[9].trim();
        this.listedIn = fields[10].trim().replaceAll("[\\[\\]]", "").split(", ");

        // Ordenar os arrays
        Arrays.sort(this.cast);
        Arrays.sort(this.listedIn);
    }

    // Método auxiliar para converter string em data
    private Date parseDate(String dateString) {
        try {
            return new SimpleDateFormat("MMMM d, yyyy").parse(dateString);
        } catch (ParseException e) {
            return null; // Retorna null em caso de erro
        }
    }
}

public class Main {
    // Metodo pra ajudar a cortar a string da forma correta
    public static String[] parseCSVLine(String line) {
        List<String> tokens = new ArrayList<>();
        boolean inQuotes = false;
        StringBuilder sb = new StringBuilder();

        for (char c : line.toCharArray()) {
            if (c == '\"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                tokens.add(sb.toString().trim());
                sb.setLength(0);
            } else {
                sb.append(c);
            }
        }
        tokens.add(sb.toString().trim()); // Adiciona o último token
        return tokens.toArray(new String[0]);
    }

    public static void main(String[] args) {
        String csvFilePath = "/tmp/disneyplus.csv";
        Scanner scanner = new Scanner(System.in);

        // Carrega todos os dados em memória
        List<String[]> allRows = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            br.readLine(); // pular cabeçalho
            String line;
            while ((line = br.readLine()) != null) {
                allRows.add(parseCSVLine(line));
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            scanner.close();
            return;
        }

        // Loop de entrada de IDs
        while (scanner.hasNextLine()) {
            String inputId = scanner.nextLine().trim();

            if (inputId.equalsIgnoreCase("FIM")) {
                break;
            }

            boolean found = false;
            Show show = new Show();

            for (String[] fields : allRows) {
                if (fields.length >= 11 && fields[0].trim().equals(inputId)) {
                    String input = String.join("##", fields);
                    show.ler(input);
                    show.imprimir();
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("Show com ID " + inputId + " não encontrado.");
            }
        }

        scanner.close();
    }
}