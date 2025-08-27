public class Usuario {
    // Atributos da classe
    private String nome;
    private String email;
    private int idade;

    // Construtor
    public Usuario(String nome, String email, int idade) {
        this.nome = nome;
        this.email = email;
        this.idade = idade;
    }

    // Métodos getters
    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public int getIdade() {
        return idade;
    }

    // Método para exibir informações do usuário
    public void exibirInformacoes() {
        System.out.println("Nome: " + nome);
        System.out.println("Email: " + email);
        System.out.println("Idade: " + idade);
    }

    // Método para atualizar o email
    public void atualizarEmail(String novoEmail) {
        this.email = novoEmail;
        System.out.println("Email atualizado para: " + novoEmail);
    }

    // Método para verificar se o usuário é maior de idade
    public boolean isMaiorDeIdade() {
        return idade >= 18;
    }

    // Método principal para testar a classe
    public static void main(String[] args) {
        Usuario usuario = new Usuario("João", "joao@example.com", 25);
        usuario.exibirInformacoes();
        usuario.atualizarEmail("joao.novo@example.com");
        System.out.println("Maior de idade: " + usuario.isMaiorDeIdade());
    }
}
