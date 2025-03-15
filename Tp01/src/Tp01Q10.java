import java.util.Scanner;

public class Tp01Q10 {

    public static int ContaPalavra(String str) {

        int n = 0;

        for (int i = 0; i < str.length(); i++) {
            char TempC = str.charAt(i);
            if (TempC == ' ') {
                n++;
            }
        }

        return n + 1;
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


        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
    
        int num = ContaPalavra(str);

        while (!FIM(str)) {
            System.out.println(num);
            str = sc.nextLine();
            num = ContaPalavra(str);

        }
        sc.close();
    }
}
