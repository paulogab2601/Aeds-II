package LAB;

import java.util.Scanner;

public class LAB02Q02 {

    public static String inverteNumero(String num, int i){

        if(i == num.length()) return "";        

        return inverteNumero(num, i+1) + num.charAt(i);
    
    }

    public static String espelho(int inicio, int fim) {

        String numEspelho = "";
        String parte2 = "";

        for (int i = inicio; i <= fim; i++) {
            numEspelho += i;
        }//Imprime a primeira parte do espelho

        for(int i = fim; i >= inicio; i--){
            parte2 += i;
            numEspelho += inverteNumero(parte2, 0);
            parte2 = "";
        }

        return numEspelho;
    }

    public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    while(sc.hasNext()){
        int n1 = sc.nextInt();
        int n2 = sc.nextInt();

        System.out.println(espelho(n1, n2));

    }
    sc.close();
    }

}
