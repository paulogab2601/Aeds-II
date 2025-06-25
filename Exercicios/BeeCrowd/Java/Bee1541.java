package BeeCrowd.Java;

import java.util.Scanner;

public class Bee1541 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            int a = sc.nextInt();
            if (a == 0)
                break;

            int b = sc.nextInt();

            int c = sc.nextInt();

            /* Processamento: */

            /* Formula para calcular o tamanho que o terreno deve ter: (a*b*100)/c = x */
            /* Formula para calcular o lado do terreno (Sempre é quadrado) = sqrt(x) */

            double tamTerreno = (a * b * 100) / c;
            double tamLado = Math.sqrt(tamTerreno);
            tamLado = (int) tamLado;

            System.out.printf("%d \n", tamLado);
        }
        sc.close();
    }

}
