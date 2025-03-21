import java.util.Random;
import java.util.Scanner;

public class Tp01Q04 {

    public static String Randomizador(String str, char TempC, char substituto){
        String newStr = new String(); //cria uma nova string
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i)!= TempC) {
                newStr = newStr + str.charAt(i); //se o caracter for igual, mantem
            }else{
                newStr = newStr + substituto;//se for diferente troca
            }
        }
        return newStr;
    }

	public static boolean FIM(String str) // Encerra o programa
    {

        String FIM = "FIM";

        if (str.length() != FIM.length()) {
            return false;
        }

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != FIM.charAt(i)) {
                return false;
            }
        }

        return true;
    }
    
    public static void main(String[] args) {
        
        char TempC;
        char substituto;

        Random gerador = new Random();
        gerador.setSeed(4);

        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String newStr= "";

        while (!FIM(str)) {
            TempC  =  ( char ) ( 'a' + ( Math.abs( gerador.nextInt( ) ) % 26 ) );
            substituto = ( char ) ( 'a' + ( Math.abs( gerador.nextInt( ) ) % 26 ) );
            newStr = Randomizador(str, TempC, substituto);
            System.out.println(newStr);
			str = sc.nextLine();

        }
        sc.close();
    }
    
}