abstract class Usuario {
    int id;
    String nome;
    String cpf;
    String email;
    String senha;

    public Usuario(int id, String nome, String cpf, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public abstract void login();
    public void cadastrar() {
        System.out.println("Usuário cadastrado com sucesso!");
    }
}

