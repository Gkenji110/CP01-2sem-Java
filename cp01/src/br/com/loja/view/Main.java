package br.com.loja.view;

import java.util.Scanner;
import br.com.loja.dao.*;
import br.com.loja.model.*;

public class Main {

    private static Scanner leitor = new Scanner(System.in);
    private static ProdutoDao produtoDao = new ProdutoDao();
    private static ClienteDao clienteDao = new ClienteDao();
    private static void main(String[] args) {

        int opcao;

        do {
            System.out.println("\n==== MENU PRINCIPAL ====");
            System.out.println("1 - Gerenciar produtos");
            System.out.println("2 - Gerenciar clientes");
            System.out.println("0 - Sair");
            System.out.println("Escolha: ");
            opcao = leitor.nextInt();
            leitor.nextLine();

            switch (opcao) {
                case 1:
                    menuProdutos();
                    break;
                case 2:
                    menuClientes();
                    break;

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
                    PesquisarProdutoPorNome();
                    break;
                case 0:
                    break;
                default: System.out.println("Opção inválida");

            }
        } while (opcao != 0);
    }
    // métodos de produto
    private static void cadastrarProdutoFisico() {
        System.out.print("ID produto físico: ");
        int id = leitor.nextInt();
        leitor.nextLine();
        System.out.print("Nome do produto físico: ");
        String nome = leitor.next();
        System.out.print("Preço do produto físico: ");
        double preco = leitor.nextDouble();
        System.out.print("Peso do produto físico: ");
        double peso = leitor.nextDouble();
        System.out.print("Estoque do produto físico: ");
        int estoque = leitor.nextInt();
        leitor.nextLine();

        ProdutoFisico pf = new ProdutoFisico(id, nome, preco, estoque, peso);
        produtoDao.cadastrar(pf);
        System.out.println("Produto físico cadastrado!");
    }

    private static void cadastrarProdutoDigital() {
        System.out.print("ID produto digital: ");
        int id = leitor.nextInt();
        leitor.nextLine();
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
        System.out.println("Produto cadastrado com sucesso");
    }
    }//main
}//class
