package Tp01; //COMPLETO

import java.util.Scanner;

public class Tp01Q03 {

	public static String cifra(String mensagem) {

		String mensagemMod = "";

		for (int i = 0; i < mensagem.length(); i++)// percorre a string
		{
			char carc = mensagem.charAt(i);

			carc = (char) (carc + 3); // Muda o caractere usando a tabela ask como referencia

			mensagemMod += carc; //Adiciona os caracteres na nova variavel
		}

		return mensagemMod;
	}

	public static boolean FIM(String str)// Função criada para parar o programa
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
		String linha = sc.nextLine();

		while (!FIM(linha)) {
			System.out.printf("%s \n", cifra(linha));
			linha = sc.nextLine();
		}
		
		sc.close();
	}
}
