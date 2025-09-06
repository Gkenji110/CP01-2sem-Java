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

    private static void menuProdutos(){
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

            switch (opcao){
                case 1: cadastrarProdutoFisico();
                    break;
        }

        }
    }//main
}//class
