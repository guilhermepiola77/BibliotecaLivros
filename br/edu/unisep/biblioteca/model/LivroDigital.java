package br.edu.unisep.biblioteca.model;

public class LivroDigital extends Livro {
    private String urlDownload;

    public LivroDigital(String titulo, String autor, String genero, String isbn, int anoPublicacao, String editora, String urlDownload) {
        super(titulo, autor, genero, isbn, anoPublicacao, editora);
        this.urlDownload = urlDownload;
    }

    public String getUrlDownload() {
        return urlDownload;
    }

    @Override
    public String toString() {
        return super.toString() + " [Digital]";
    }
}
