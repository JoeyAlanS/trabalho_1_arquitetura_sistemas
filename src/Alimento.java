public class Alimento {
    private String nome;
    private double quantidade;
    private double calorias;

    public Alimento(String nome, double quantidade, double calorias) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.calorias = calorias;
    }

    public String getNome() {
        return nome;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public double getCalorias() {
        return calorias;
    }

    @Override
    public String toString() {
        return nome + " - " + quantidade + "g, " + calorias + " calorias";
    }
}
