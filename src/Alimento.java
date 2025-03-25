class Alimento {
    private String nome;
    private double quantidade;
    private double calorias;

    public Alimento(String nome, double quantidade, double calorias) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.calorias = calorias;
    }

    @Override
    public String toString() {
        return nome + " - " + quantidade + "g, " + calorias + " calorias";
    }
}