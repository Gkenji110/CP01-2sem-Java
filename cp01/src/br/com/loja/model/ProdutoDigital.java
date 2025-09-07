package br.com.loja.model;

public class ProdutoDigital extends Produto{
    private double tamanhoArquivo;
    private String formato;

    public ProdutoDigital(int idProduto, String nome, double preco, double tamanhoArquivo, String formato) {
        super(idProduto, nome, preco);
        this.tamanhoArquivo = tamanhoArquivo;
        this.formato = formato;
    }

    public double getTamanhoArquivo() {
        return tamanhoArquivo;
    }
    public void setTamanhoArquivo(double tamanhoArquivo) {
        this.tamanhoArquivo = tamanhoArquivo;
    }

    public String getFormato() {
        return formato;
    }
    public void setFormato(String formato) {
        this.formato = formato;
    }

    @Override
    public String toString() {
        return "Id: " + getIdProduto() + " | Nome: " + getNome() + " | Preço (R$): " + getPreco() + " | Tamanho (Mb): " + getTamanhoArquivo() + " | Formato: " + getFormato();
    }
}
