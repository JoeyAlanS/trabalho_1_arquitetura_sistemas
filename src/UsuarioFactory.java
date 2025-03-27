class UsuarioFactory {
    public static Usuario criarUsuario(int tipo, String nome, String cpf, String email, String senha, String crn) {
        if (tipo == 1) {
            return new Paciente(0, nome, cpf, email, senha);
        } else if (tipo == 2) {
            return new Nutricionista(0, nome, cpf, email, senha, crn);
        }
        return null;
    }
}
