package br.com.ddd;

import br.com.ddd.dominio.produto.Produto;

public class Main {
    public static void main(String[] args) {
        Produto produto = new Produto("Pimentão", 3.0, "Verdura");

        System.out.println(produto.getNome());

        
    }
}