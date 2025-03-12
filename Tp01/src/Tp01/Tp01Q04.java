package Tp01; //INCOMPLETO

import java.util.Random;

public class Tp01Q04 {

	public static String randomizador(String str) {

		Random gerador = new Random();
		gerador.setSeed(4);
		

		char l1 = (char) ('a' + (Math.abs(gerador.nextInt()) % 26)); //sorteia as letras
		char l2 = (char) ('a' + (Math.abs(gerador.nextInt()) % 26));

		char[] resultado = new char[str.length()]; // Cria um array de char para manipular a string

		for (int i = 0; i < str.length(); i++) // Substitui a letra em tofas as suas incidencias
		{
			if (str.charAt(i) == l1) {
				resultado[i] = l2;
			} else {
				resultado[i] = str.charAt(i); //Se a letra for diferente de l1, coloca a letra original
			}
		}

		return new String(resultado);
	};

	public static void main(String[] args) {

		System.out.printf("%s \n", randomizador("o rato roeu a roupa do rei de roma	"));

	}

}
