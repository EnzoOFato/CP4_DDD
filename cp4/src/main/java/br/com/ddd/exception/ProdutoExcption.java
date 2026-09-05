package br.com.ddd.exception;

public class ProdutoExcption extends RuntimeException{
    private String mensagem;

    public ProdutoExcption(String mensagem) {
        this.mensagem = mensagem;
    }

    @Override 
    public String getMessage() {
        return this.mensagem;
    }
}
