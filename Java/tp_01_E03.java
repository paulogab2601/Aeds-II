public class tp_01_E03 {

    public static String cifrar(String message) {
        String resultadoCifrado = "";
        for (int i = 0; i < message.length(); ++i) {
            resultadoCifrado += (char) (message.charAt(i) + 3);
        }
        return resultadoCifrado;
    }

    public static boolean isFIM(String s) {
        return s.equals("FIM");
    }

    @SuppressWarnings("WrongPackageStatement")

    public static void main(String[] args) {
        String[] entrada = new String[1000];
        int numEntrada = 0;

        do {
            entrada[numEntrada] = MyIO.readLine();
        } while (!isFIM(entrada[numEntrada++]));
        numEntrada--;

        for (int x = 0; x < numEntrada; x++) {
            MyIO.println(cifrar(entrada[x]));
        }
    }
}