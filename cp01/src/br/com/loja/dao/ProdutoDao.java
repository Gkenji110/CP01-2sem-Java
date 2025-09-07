package br.com.loja.dao;

import br.com.loja.model.Produto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProdutoDao {

    private Map<Integer, Produto> produtos = new HashMap<>();

    public void cadastrar(Produto produto) {
        produtos.put(produto.getIdProduto(), produto);
    }

    public List<Produto> listar() {
        return new ArrayList<>(produtos.values());
    }

    public Produto pesquisarPorId(int id) {
        return produtos.get(id);
    }

    public void editar(Produto produto) {
        produtos.put(produto.getIdProduto(), produto);
    }

    public void remover(int id){
        produtos.remove(id);
    }

    public List<Produto> pesquisaPorNome(String nome){
        List<Produto> resultado = new ArrayList<>();
        for (Produto p: produtos.values()){
            if (p.getNome().toLowerCase().contains(nome.toLowerCase())){
                resultado.add(p);
            }
        }
        return resultado;
    }

}
