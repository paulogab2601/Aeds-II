package Tp01; //COMPLETO

import java.util.Scanner;

public class Tp01Q01 {
	public static boolean Palindromo(String str) {

		int esq = 0, dir = str.length() - 1;

		while (esq < dir) {

			if (str.charAt(esq) != str.charAt(dir)) {
				return false;
			}

			esq++;
			dir--;
		}
		return true;

	}
	
	public static boolean FIM(String str) {
			
		String FIM = "FIM";
		
		if(str.length() != FIM.length()) {
			return false;
		}
		
		for(int i = 0; i < str.length(); i++) {
			if(str.charAt(i) != FIM.charAt(i)) {
				return false;
			}
		}
		
		return true;
	}

	public static void main(String[] args) {

		Scanner verde = new Scanner(System.in);

		String frase = verde.nextLine();

		while (FIM(frase) != true) {
			
			if (Palindromo(frase) == true) {
				System.out.println("SIM");
			} else {
				System.out.println("NAO");
			}

			frase = verde.nextLine();

		}
		
		verde.close();
	}
}
