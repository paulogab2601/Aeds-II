package Tp01; //INCOMPLETO

import java.util.Scanner;

public class Tp01Q08 {

	public static int recursivo(int n) // OK
	/* Funciona pegando o ultimo numero da função toda vez */
	{

		if (n < 10) {
			return n;
		}

		return n % 10 + recursivo(n / 10);

	}

	public static void main(String[] args) {

	}

}
