package br.com.ddd.dominio.produto;

import java.util.Objects;

import br.com.ddd.exception.ProdutoExcption;

public class Produto {
    private Long id;
    private String nome;
    private Double preco;
    private String categoria;

    private static long seq = 1;

    public Produto(String nome, double preco, String categoria) {
        validar(nome, preco);

        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria.toUpperCase();

        this.id = seq++;
    }

    private void validar(String nome, double preco) {
        if (Objects.isNull(nome) || nome.strip().equals("")) throw new ProdutoExcption("Nome do produto não pode ser vazio");

        if (preco <= 0) throw new ProdutoExcption("Preço não pode ser menor ou igual a zero");
    }

    public Long getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public String getCategoria() {
        return this.categoria;
    }

    public Double getPreco() {
        return this.preco;
    }

    @Override 
    public String toString() {
        if (Objects.isNull(this)) return "Esse objeto não existe";
        return """
                Produto Id: %d
                Produto: %s
                Preço: R$ %.2f
                Categoria: %s
                """.formatted(this.id, this.nome, this.preco, this.categoria);
    }

}
