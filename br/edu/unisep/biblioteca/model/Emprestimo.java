package br.edu.unisep.biblioteca.model;

public class Emprestimo {
    private Livro livro;
    private Usuario usuario;
    private String status;

    public Emprestimo(Livro livro, Usuario usuario, String status) {
        this.livro = livro;
        this.usuario = usuario;
        this.status = status;
    }

    public Livro getLivro() {
        return livro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getStatus() {
        return status;
    }
}
