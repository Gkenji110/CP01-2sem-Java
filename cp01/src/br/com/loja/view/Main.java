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
                case 0:
                    System.out.println("Saindo");
                    break;
                default:
                    System.out.println("Opção inválida");
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
            System.out.println("4 - Pesquisar por ID");
            System.out.println("5 - Editar produto");
            System.out.println("6 - Remover produto");
            System.out.println("7 - Pesquisar por nome do produto");
            System.out.println("0 - Voltar");
            System.out.println("Escolha: ");
            opcao = leitor.nextInt();
            leitor.nextLine();

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
                default: System.out.println("Opção inválida");

            }
        } while (opcao != 0);
    }

    // métodos de produto
    private static void cadastrarProdutoFisico() {
        int id = lerInt("Id do produto físico:");
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
        System.out.println("Produto físico cadastrado!");
    }

    private static void cadastrarProdutoDigital() {
        int id = lerInt("Id do produto digital:");
        System.out.print("Nome produto digital: ");
        String nome = leitor.next();
        System.out.print("Preço produto digital: ");
        double preco = leitor.nextDouble();
        System.out.print("Tamanho (MB) do produto digital: ");
        double tamanho = leitor.nextDouble();
        System.out.print("Formato produto digital: ");
        String formato = leitor.nextLine();

        ProdutoDigital pd = new ProdutoDigital(id, nome, preco, tamanho, formato);
        produtoDao.cadastrar(pd);
        System.out.println("Produto cadastrado com sucesso!");
    }

    private static void listarProdutos() {
        System.out.println("\n -- Lista de produtos --");
        List<Produto> lista = produtoDao.listar();
        if (lista.isEmpty()) {
            System.out.println("Nenhum produto cadastrado!");
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
        } else System.out.println("Produto não encontrado. Tente novamente.");
    }

    private static void editarProduto() {
        int id = lerInt("Id do produto para editar:");
        Produto p = produtoDao.pesquisarPorId(id);
        if (p == null) {
            System.out.println("Produto não encontrado!");
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
        System.out.println("Produto editado com sucesso!");
    }

    private static void removerProduto() {
        int id = lerInt("Id do produto para ser removido:");
        produtoDao.remover(id);
        System.out.println("Produto removido com sucesso!");
    }

    private static void pesquisarProdutoPorNome() {
        System.out.println("Nome do produto: ");
        String nome = leitor.nextLine();
        List<Produto> encontrados = produtoDao.pesquisaPorNome(nome);
        if (encontrados.isEmpty()) {
            System.out.println("Nenhum produto encontrado");
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

    }//main

}//class
