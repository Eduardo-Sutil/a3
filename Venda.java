public class Venda {
    private int id;
    private String produto;
    private double preco;

    public Venda(int id, String produto, double preco) {
        // Construtor da classe Venda que recebe o ID, nome do produto e preço
        this.id = id;
        this.produto = produto;
        this.preco = preco;
    }

    public int getId() {
        // Método para obter o ID da venda
        return id;
    }

    public String getProduto() {
        // Método para obter o nome do produto da venda
        return produto;
    }

    public void setProduto(String produto) {
        // Método para definir o nome do produto da venda
        this.produto = produto;
    }

    public double getPreco() {
        // Método para obter o preço da venda
        return preco;
    }

    public void setPreco(double preco) {
        // Método para definir o preço da venda
        this.preco = preco;
    }
}
