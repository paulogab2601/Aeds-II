package fundamentos;

import java.util.Scanner;

public class console {
	public static void main(String[] args) {
		System.out.println("TESTE");
		System.out.print("Mesma linha");
		System.out.print(" aqui ó");
		System.out.print("\n");
		
		System.out.printf("Mega Sena %d\n", 1);
		
		int a = 3;
		Scanner entrada = new Scanner(System.in);
		
		System.out.print("Digite o seu nome: ");
		String nome = entrada.nextLine();
		
		System.out.println(nome);
		
		System.out.print("Digite seu sobrenome: ");
		String sobrenome = entrada.nextLine();
		
		System.out.println(sobrenome);
		
		System.out.print("Digite sua idade: ");
		int idade  = entrada.nextInt();
		System.out.printf("Sua idade é %d", idade);
		
		entrada.close();
	}
	
}
