package br.com.ddd.dominio.produto;

import java.util.List;
import java.util.Optional;
import  java.util.Set;

public interface  ProdutoRepository {
    void salvar(Produto p);
    Optional<Produto> buscarPorId(long id);
    List<Produto> listarTodos();
    Set<String> listarCategorias();
}
