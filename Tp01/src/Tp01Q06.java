import java.util.Scanner;

public class Tp01Q06 {

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

	public static boolean ehVogal(String str) // OK
	{

		for (int i = 0; i < str.length(); i++) {
			char tempC = str.charAt(i);

			if (!(tempC == 'a' || tempC == 'e' || tempC == 'i' || tempC == 'o' || tempC == 'u' || tempC == 'A'
					|| tempC == 'E' || tempC == 'I' || tempC == 'O' || tempC == 'U')) {
				return false; // Se achar uma letra que nao é vogal retorna false

			}
		}

		return true; // retorna true por padrao, a menos que a condiçao acima seja cumprida
	}

	public static boolean ehConsoante(String str) // OK
	{
		// OBS: copia da função acima, só muda o if (remoção do ! antes do 1° parentese
		for (int i = 0; i < str.length(); i++) {
			char tempC = str.charAt(i);

			if ((tempC == 'a' || tempC == 'e' || tempC == 'i' || tempC == 'o' || tempC == 'u' || tempC == 'A'
					|| tempC == 'E' || tempC == 'I' || tempC == 'O' || tempC == 'U' || tempC == '1' || tempC == '2'
					|| tempC == '3' || tempC == '4' || tempC == '5' || tempC == '6' || tempC == '7' || tempC == '8'
					|| tempC == '9' || tempC == '0')) {
				return false; // Se achar uma letra que é vogal, ou alggum numero, retorna false

			}
		}

		return true; // retorna true por padrao, a menos que a condiçao acima seja cumprida
	}

	public static boolean ehInteiro(String str) {

		int n = 0;

		for (int i = 0; i < str.length(); i++) {
			char TempC = str.charAt(i);
			if (TempC == ',' || TempC == '.')// conta o numero de . e , ;Se for maior que 1 o numero nao é
												// real nem inteiro

			{
				n++;
				for (int a = i + 1; a < str.length(); a++)// Esse for percorre todos os nuemeros depois da virgula
															// procurando qualquer digito != 0, o que faria o numero ser
															// real ou invalido, e nao inteiro
				{
					char TempC2 = str.charAt(a);
					if (TempC2 != '0' || TempC2 != ' ') {
						return false;
					}
				}
			}

			if ((str.charAt(i) < 48 || str.charAt(i) > 57) && (str.charAt(i) != 44 && str.charAt(i) != 46)) {
				return false;
			}
			if (n > 1) {

				return false;
			}
		}

		return true;
	}

	public static boolean ehReal(String str) {

		int n = 0;

		for (int i = 0; i < str.length(); i++) {
			char TempC = str.charAt(i);
			if (TempC == ',' || TempC == '.')// conta o numero de . e , ;Se for maior que 1 o numero nao é
												// real nem inteiro

			{
				n++;
			}
			if ((str.charAt(i) < 48 || str.charAt(i) > 57) && (str.charAt(i) != 44 && str.charAt(i) != 46)) {
				return false;
			}
		}
		if (n > 1) {
			return false;
		}

		return true;

	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();

		while (!FIM(str)) {

			if (ehVogal(str)) {
				System.out.printf("SIM ");
			} else {
				System.out.printf("NAO ");
			}

			if (ehConsoante(str)) {
				System.out.printf("SIM ");
			} else {
				System.out.printf("NAO ");
			}

			if (ehInteiro(str)) {
				System.out.printf("SIM ");
			} else {
				System.out.printf("NAO ");
			}

			if (ehReal(str)) {
				System.out.println("SIM");
			} else {
				System.out.println("NAO");
			}

			str = sc.nextLine();

		}
		sc.close();
	}

}
