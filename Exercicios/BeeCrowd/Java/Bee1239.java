package BeeCrowd.Java;

import java.util.Scanner;

public class Bee1239 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String linha = sc.nextLine();
            StringBuilder resultado = new StringBuilder();

            boolean italic = true;
            boolean bold = true;

            for (int i = 0; i < linha.length(); i++) {
                char c = linha.charAt(i);

                if (c == '_') {
                    if (italic) {
                        resultado.append("<i>");
                    } else {
                        resultado.append("</i>");
                    }
                    italic = !italic;
                } else if (c == '*') {
                    if (bold) {
                        resultado.append("<b>");
                    } else {
                        resultado.append("</b>");
                    }
                    bold = !bold;
                } else {
                    resultado.append(c);
                }
            }

            System.out.println(resultado.toString());
        }

        sc.close();
    }
}
