package br.com.loja.view;

import java.util.List;
import java.util.Scanner;
import br.com.loja.dao.ProdutoDao;
import br.com.loja.dao.ClienteDao;
import br.com.loja.model.*;

public class Main {

    private static Scanner leitor = new Scanner(System.in);
    private static ProdutoDao produtoDao = new ProdutoDao();
    private static ClienteDao clienteDao = new ClienteDao();
    public static void main(String[] args) {

        int opcao;

        do {
            System.out.println("\n==== MENU PRINCIPAL ====");
            System.out.println("1 - Gerenciar produtos");
            System.out.println("2 - Gerenciar clientes");
            System.out.println("0 - Sair");
            System.out.println("Escolha: ");
            opcao = lerInt("");

            switch (opcao) {
                case 1:
                    menuProdutos();
                    break;
                case 2:
                    menuClientes();
                    break;
                case 0:
                    System.out.println("Saindo");
                    break;
                default:
                    System.out.println("\nOpção inválida");
            }
        } while (opcao != 0);
    }

    private static void menuProdutos() {
        int opcao;
        do {
            System.out.println("\n==== Produtos ====");
            System.out.println("1 - Cadastrar produto físicos");
            System.out.println("2 - Cadastrar produto digital");
            System.out.println("3 - Listar produtos");
            System.out.println("4 - Pesquisar por Id");
            System.out.println("5 - Editar produto");
            System.out.println("6 - Remover produto");
            System.out.println("7 - Pesquisar por nome do produto");
            System.out.println("0 - Voltar para menu anterior");
            System.out.println("\nEscolha uma das opções: ");
            opcao = lerInt("");

            switch (opcao) {
                case 1:
                    cadastrarProdutoFisico();
                    break;
                case 2:
                    cadastrarProdutoDigital();
                    break;
                case 3:
                    listarProdutos();
                    break;
                case 4:
                    pesquisarProdutoPorId();
                    break;
                case 5:
                    editarProduto();
                    break;
                case 6:
                    removerProduto();
                    break;
                case 7:
                    pesquisarProdutoPorNome();
                    break;
                case 0:
                    break;
                default: System.out.println("\nOpção inválida");

            }
        } while (opcao != 0);
    }

    // métodos de produto
    private static void cadastrarProdutoFisico() {
        int id;
        while (true) {
            id = lerInt("Id do produto físico:");
            if (produtoDao.pesquisarPorId(id) != null) {
                System.out.println("Já existe um produto físico com esse Id. Escolha outro Id.");
            }else {
                break;
            }
        }
        System.out.print("Nome do produto físico: ");
        String nome = leitor.next();
        System.out.print("Preço do produto físico (R$): ");
        double preco = leitor.nextDouble();
        System.out.print("Peso do produto físico (Kg): ");
        double peso = leitor.nextDouble();
        System.out.print("Estoque do produto físico: ");
        int estoque = leitor.nextInt();
        leitor.nextLine();

        ProdutoFisico pf = new ProdutoFisico(id, nome, preco, estoque, peso);
        produtoDao.cadastrar(pf);
        System.out.println("\nProduto físico cadastrado!");
    }

    private static void cadastrarProdutoDigital() {
        int id;
        while (true) {
            id = lerInt("Id do produto digital:");
            if (produtoDao.pesquisarPorId(id) != null) {
                System.out.println("Já existe um produto digital com esse Id. Escolha outro Id.");
            }else {
                break;
            }
        }
        System.out.print("Nome produto digital: ");
        String nome = leitor.next();
        System.out.print("Preço produto digital (R$): ");
        double preco = leitor.nextDouble();
        System.out.print("Tamanho (MB) do produto digital: ");
        double tamanho = leitor.nextDouble();
        leitor.nextLine();
        System.out.print("Formato produto digital: ");
        String formato = leitor.nextLine();

        ProdutoDigital pd = new ProdutoDigital(id, nome, preco, tamanho, formato);
        produtoDao.cadastrar(pd);
        System.out.println("\nProduto cadastrado com sucesso!");
    }

    private static void listarProdutos() {
        System.out.println("\n -- Lista de produtos --");
        List<Produto> lista = produtoDao.listar();
        if (lista.isEmpty()) {
            System.out.println("\nNenhum produto cadastrado!");
        } else {
            for (Produto p : lista) {
                System.out.println(p);
            }
        }
    }
    private static void pesquisarProdutoPorId() {
        int id = lerInt("Id do produto:");
        Produto p = produtoDao.pesquisarPorId(id);
        if (p != null) {
            System.out.println(p);
        } else System.out.println("\nProduto não encontrado. Tente novamente.");
    }

    private static void editarProduto() {
        int id = lerInt("Id do produto para editar:");
        Produto p = produtoDao.pesquisarPorId(id);
        if (p == null) {
            System.out.println("\nProduto não encontrado!");
            return;
        }
        System.out.print("Novo nome: ");
        String nome = leitor.nextLine();
        System.out.print("Novo preço: ");
        double preco = leitor.nextDouble();
        leitor.nextLine();

        p.setNome(nome);
        p.setPreco(preco);

        if (p instanceof ProdutoFisico){
            ProdutoFisico pf = (ProdutoFisico) p;
            System.out.print("Novo peso: ");
            double peso = leitor.nextDouble();
            System.out.print("Novo estoque: ");
            int estoque = leitor.nextInt();
            leitor.nextLine();
            pf.setPeso(peso);
            pf.setEstoque(estoque);
        } else if (p instanceof ProdutoDigital) {
            ProdutoDigital pd = (ProdutoDigital) p;
            System.out.print("Novo tamanho do arquivo (MB): ");
            double tamanho = leitor.nextDouble();
            leitor.nextLine();
            System.out.print("Novo formato do produto digital: ");
            String formato = leitor.nextLine();
            pd.setFormato(formato);
            pd.setTamanhoArquivo(tamanho);
        }
        produtoDao.editar(p);
        System.out.println("\nProduto editado com sucesso!");
    }

    private static void removerProduto() {
        int id = lerInt("Id do produto para ser removido:");
        produtoDao.remover(id);
        System.out.println("\nProduto removido com sucesso!");
    }

    private static void pesquisarProdutoPorNome() {
        System.out.println("Nome do produto: ");
        String nome = leitor.nextLine();
        List<Produto> encontrados = produtoDao.pesquisaPorNome(nome);
        if (encontrados.isEmpty()) {
            System.out.println("\nNenhum produto encontrado");
        } else {
            for (Produto p : encontrados) {
                System.out.println(p);
            }
        }

    }
    private static int lerInt(String mensagem) {
        int id;
        while (true) {
            try {
                System.out.print(mensagem);
                id = Integer.parseInt(leitor.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite apenas números.");
            }
        }
        return id;

    }

    //Menu de clientes
    private static void menuClientes() {
        int opcao;
        do {
            System.out.println("\n==== Clientes ====");
            System.out.println("1 - Cadastrar cliente");
            System.out.println("2 - Listar clientes");
            System.out.println("3 - Pesquisar por Id");
            System.out.println("4 - Editar cliente");
            System.out.println("5 - Remover cliente");
            System.out.println("6 - Pesquisar por nome do cliente");
            System.out.println("0 - Voltar para menu anterior");
            System.out.println("\nEscolha uma das opções: ");
            opcao = lerInt("");

            switch (opcao) {
                case 1:
                    cadastrarCliente();
                    break;
                case 2:
                    listarClientes();
                    break;
                case 3:
                    pesquisarClientePorId();
                    break;
                case 4:
                    editarCliente();
                    break;
                case 5:
                    removerCliente();
                    break;
                case 6:
                    pesquisarClientePorNome();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida");

            }
        } while (opcao != 0);
    }

    private static void cadastrarCliente() {
        int id;
        while (true) {
            id = lerInt("Id do cliente:");
            if (clienteDao.pesquisarPorId(id) != null) {
                System.out.println("Já existe um cliente com esse Id. Escolha outro Id.");
            }else {
                break;
            }
        }
        System.out.print("Nome do cliente: ");
        String nome = leitor.nextLine();
        System.out.print("Email do cliente: ");
        String email = leitor.nextLine();


        Cliente cl = new Cliente(id, nome, email);
        clienteDao.cadastrar(cl);
        System.out.println("\nCliente cadastrado com sucesso!");
    }

    private static void listarClientes() {
        System.out.println("\n -- Lista de clientes --");
        List<Cliente> lista = clienteDao.listar();
        if (lista.isEmpty()) {
            System.out.println("\nNenhum cliente cadastrado!");
        } else {
            for (Cliente cl : lista) {
                System.out.println(cl);
            }
        }
    }

    private static void pesquisarClientePorId(){
        int id = lerInt("Id do cliente:");
        Cliente cl = clienteDao.pesquisarPorId(id);
            if (cl != null) {
                System.out.println(cl);
            } else System.out.println("\nCliente não encontrado. Tente novamente.");
    }

    private static void editarCliente() {
        int id = lerInt("Id do cliente:");
        Cliente cl = clienteDao.pesquisarPorId(id);
        if (cl == null) {
            System.out.println("\nCliente não encontrado");
            return;
        }
        System.out.print("Novo nome: ");
        String nome = leitor.nextLine();
        System.out.print("Novo email: ");
        String email = leitor.next();

        cl.setNome(nome);
        cl.setEmail(email);
        clienteDao.editar(cl);
        System.out.println("\nCliente editado com sucesso");
    }

    private static void removerCliente() {
        int id = lerInt("Id do cliente:");
        clienteDao.remover(id);
        System.out.println("\nCliente removido com sucesso");
    }

    private static void pesquisarClientePorNome(){
        System.out.print("Nome do cliente: ");
        String nome = leitor.nextLine();
        List<Cliente> encontrados = clienteDao.pesquisaPorNome(nome);
        if (encontrados.isEmpty()){
            System.out.println("\nNenhum cliente encontrado!");
        } else {
            for (Cliente cl : encontrados){
                System.out.println(cl);
            }
        }

    }//main

}//class
