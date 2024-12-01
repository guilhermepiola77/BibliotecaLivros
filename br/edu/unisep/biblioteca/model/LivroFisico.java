package br.edu.unisep.biblioteca.model;

public class LivroFisico extends Livro {
    private String codigoDeBarras;

    public LivroFisico(String titulo, String autor, String genero, String isbn, int anoPublicacao, String editora, String codigoDeBarras) {
        super(titulo, autor, genero, isbn, anoPublicacao, editora);
        this.codigoDeBarras = codigoDeBarras;
    }

    public String getCodigoDeBarras() {
        return codigoDeBarras;
    }

    @Override
    public String toString() {
        return super.toString() + " [Físico]";
    }
}
