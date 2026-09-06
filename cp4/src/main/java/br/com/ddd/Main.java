package br.com.ddd;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

import br.com.ddd.dominio.produto.Produto;
import br.com.ddd.dominio.produto.ProdutoRepository;
import br.com.ddd.exception.ProdutoExcption;
import br.com.ddd.infra.ProdutoRepositorioMemoria;

public class Main {

    private static final ProdutoRepository produtoRepository = new ProdutoRepositorioMemoria();

    private static final Scanner entrada = new Scanner(System.in);

    public static void main(String[] args) {
        mockingData();

        System.out.println("""
                --------------SEJA BEM VINDO AO SISTEMA DE GERENCIAMENTO DE PRODUTOS------------------
                """);

        menu();
    }

    private static void mockingData() {
        List<Produto> produtosCadastrados = Arrays.asList(
            new Produto("Pimentão", 3.0, "Verdura"),
            new Produto("Fadidangos", 7.5, "Salgadinho"),
            new Produto("Dortitos", 9.0, "Salgadinho"),
            new Produto("Cadeira de Praia", 99.99, "Lazer"),
            new Produto("Abobrinha", 1.4, "Verdura")
        );

        produtosCadastrados.stream().forEach(p -> produtoRepository.salvar(p));
    }

    private static void menu() {
        int escolha = 0;

        String txtMenu = """
                Por favor, selecione uma das seguintes opções:
                1. Cadastrar produto
                2. Buscar Produto por Id
                3. Todos produtos
                4. Todas Categorias
                5. Sair
                Resposta: """;

        do {
            try {
                System.out.println(txtMenu);
                escolha = entrada.nextInt();
                entrada.nextLine();

                switch (escolha) {
                    case 1 -> cadastraProduto();
                    case 2 -> buscaId();
                    case 3 -> listarTodos();
                    case 4 -> listarCategorias();
                    case 5 -> System.out.println("Até Mais!!!");
                    default -> System.out.println("Escolha uma opção existente");
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Digite números inteiros válidos");
                entrada.nextLine();
            }
        } while (escolha != 5);
    }

    private static void cadastraProduto() {
        try {
            String nome = pergunta("Digite o nome do produto: ");
            Double preco = Double.parseDouble(pergunta("Digite o preço do produto: "));
            String categoria = pergunta("Digite a categoria do produto: ");

            Produto produto = new Produto(nome, preco, categoria);

            produtoRepository.salvar(produto);

            System.out.println("\nProduto cadastrado com sucesso!\n");
        }
        catch (NumberFormatException e) {
            System.out.println("Digite um preço válido");
        }
        catch (ProdutoExcption e) {
            System.out.println(e.getMessage());
        }
    }

    private static String pergunta(String txt) {
        System.out.println(txt);
        return entrada.nextLine();
    }

    private static void buscaId() {

        try {
            int id = Integer.parseInt(pergunta("Qual id deseja encontrar: "));
            Optional<Produto> p = produtoRepository.buscarPorId(id);

            if (p.isEmpty()) {
                System.out.println("Nenhum produto encontrado com o id " + id);
            }
            else {
                System.out.println("Produto encontrado!");
                System.out.println(p.get());
            }
        }
        catch (NumberFormatException e) {
            System.out.println("Digite um número inteiro válido");
        }
    }

    private static void listarTodos() {
        List<Produto> produtos = produtoRepository.listarTodos();

        System.out.println("Todos os produtos encontrados: ");

        produtos.stream().forEach(p -> System.out.println(p));

        pergunta("Pressione enter para voltar");
    }

    private static void listarCategorias() {
        Set<String> categorias = produtoRepository.listarCategorias();

        System.out.println("Todos as categorias encontradas: ");

        categorias.stream().forEach(c -> System.out.println(c));

        pergunta("Pressione enter para voltar");
    }
}