package Provas;

import java.util.*;
import java.util.ArrayList;
import java.util.Scanner;

public class P3Lab01 {

    public static void main(String[] args) {

        ArrayList<String> protocolos = new ArrayList<>(); // Array list para colocar os protocolos

        Scanner sc = new Scanner(System.in); // Escaneia as entradas

        while (sc.hasNextLine()) {
            int transferencia = sc.nextInt(); // Controla a abertura da transferencia

            if (!(protocolos.isEmpty()))// Limpa os elementos da lista
            {
                protocolos.clear();
            }
            if (transferencia == 1) {
                while (transferencia != 0) {
                    protocolos.add(sc.nextLine());

                    if (sc.hasNextInt()) {
                        transferencia = sc.nextInt();
                    }
                }
                Collections.sort(protocolos);

                protocolos.add("\n"); // Adiciona uma quebra de linha ao final do ArrayList de string

                for (String s : protocolos) // Imprime o ArrayList
                {
                    System.out.println(s);
                }
            }

        }
        sc.close();
    }

}
