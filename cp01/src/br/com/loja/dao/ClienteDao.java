package br.com.loja.dao;

import br.com.loja.model.Cliente;
import com.sun.source.tree.BreakTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClienteDao {
    Map<Integer, Cliente> clientes = new HashMap<>();

    public void cadastrar(Cliente cliente){
        clientes.put(cliente.getIdCliente(), cliente);
    }

    public List<Cliente> listar() {
        return new ArrayList<>(clientes.values());
    }

    public Cliente pesquisarPorId(int id){
        return clientes.get(id);
    }

    public void editar(Cliente cliente){
        clientes.put(cliente.getIdCliente(), cliente);
    }

    public void remover(int id){
        clientes.remove(id);
    }

    public List<Cliente> pesquisaPorNome(String nome) {
        List<Cliente> resultado = new ArrayList<>();
        for (Cliente c: clientes.values()){
            if (c.getNome().toLowerCase().contains(nome.toLowerCase())){
                resultado.add(c);
            }
        }
        return resultado;
    }

}
