package br.com.ddd.infra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import br.com.ddd.dominio.produto.Produto;
import br.com.ddd.dominio.produto.ProdutoRepository;

public class ProdutoRepositorioMemoria implements ProdutoRepository {
    public static final HashMap<Long, Produto> tabelaProduto = new HashMap<>();

    @Override
    public void salvar(Produto p) {
        tabelaProduto.put(p.getId(), p);
    }

    @Override
    public Optional<Produto> buscarPorId(long id) {
        Optional<Produto> op = Optional.ofNullable(tabelaProduto.get(id));

        return op;
    }

    @Override
    public List<Produto> listarTodos() {
        List<Produto> produtos = new ArrayList<>();

        tabelaProduto.keySet().stream()
        .forEach(k -> produtos.add(tabelaProduto.get(k)));

        return produtos;
    }

    @Override
    public Set<String> listarCategorias() {
        Set<String> resultado = new HashSet<>();

        listarTodos().stream().forEach(p -> resultado.add(p.getCategoria()));

        return resultado;
    }
}
