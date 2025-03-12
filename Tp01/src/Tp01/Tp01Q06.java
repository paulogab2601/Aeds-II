package Tp01; //INCOMPLETO

public class Tp01Q06 {

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
					|| tempC == 'E' || tempC == 'I' || tempC == 'O' || tempC == 'U')) {
				return false; // Se achar uma letra que é vogal retorna false

			}
		}

		return true; // retorna true por padrao, a menos que a condiçao acima seja cumprida
	}

	public static boolean ehInteiro(String str) {

		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) < 48 || str.charAt(i) > 57) {
				return false;
			}
		}

		return true;
	}

	public static void main(String str) {

	}

}
