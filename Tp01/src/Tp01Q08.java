//Incompleto

//import java.util.Scanner;

import java.util.Scanner;

public class Tp01Q08 {

	public static int recursivo(int n) // OK
	{

		if (n < 10) {
			return n;
		}

		return n % 10 + recursivo(n / 10);

	}

	// Nao queria usar o While(true) mas só cheguei nessa forma de resolver :(

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		while (true) {
            try {
                int n = sc.nextInt(); // Lê o número inteiro
                sc.nextLine(); // Consome o Enter após o número //Perdi 2h aqui

                int num = recursivo(n); 
                System.out.println(num);

            } catch (Exception e) {
                break; // Encerra o laço
            }
        }

		sc.close();
	}

}
