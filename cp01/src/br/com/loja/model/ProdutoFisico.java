package br.com.loja.model;

public class ProdutoFisico extends Produto{

    private double peso;
    private int estoque;

    public ProdutoFisico(int idProduto, String nome, double preco, int estoque, double peso) {
        super(idProduto, nome, preco, estoque);
        this.peso = peso;
        this.estoque = estoque;
    }

    public double getPeso() {
        return peso;
    }
    public void setPeso(double peso) {
        this.peso = peso;
    }

    public int getEstoque() {
        return estoque;
    }
    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    @Override
    public String toString() {
        return super.toString() + "Peso: " + peso + "kg | Estoque: " + estoque;
    }
}
