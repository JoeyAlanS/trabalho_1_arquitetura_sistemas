abstract class Usuario {
    private int id;
    private String nome;
    private String cpf;
    private String email;
    private String senha;

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

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public void login() {
            System.out.println("Bem-vindo(a), " + getNome() + "!");
    }

    public void cadastrar() {
        System.out.println("Usuário cadastrado com sucesso!");
    }
}

