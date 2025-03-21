import java.io.*;
import java.util.*;

public class Tp01Q14 {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in);
             RandomAccessFile raf = new RandomAccessFile("nums.txt", "rw")) {

            int n = sc.nextInt(); //num de entradas

            List<Long> positions = new ArrayList<>(); // lista pra armazenar os numeros

            for (int i = 0; i < n; i++) {
                positions.add(raf.getFilePointer()); // Guarda a posicao do numero
                double num = sc.nextDouble();
                raf.writeBytes(num + "\n");
            }

            for (int i = positions.size() - 1; i >= 0; i--) {
                raf.seek(positions.get(i)); // Move para a posicao do nuero
                System.out.println(raf.readLine()); // le e imprime o numero
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}