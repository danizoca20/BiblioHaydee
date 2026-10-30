
package br.escola.bibliohaydee.model;

public class Livro {
    private String titulo;
    private String isbn;
    private Autor autor;
    private Integer anoDePublicacao;
    private String genero;

    public Livro(String titulo, String isbn, Autor autor,
                 Integer anoDePublicacao, String genero) {
        this.titulo = titulo;
        this.isbn = isbn;
        this.autor = autor;
        this.anoDePublicacao = anoDePublicacao;
        this.genero = genero;
    }

    // Construtor sobrecarregado: sem informar o gênero
    public Livro(String titulo, String isbn, Autor autor,
                 Integer anoDePublicacao) {
        this(titulo, isbn, autor, anoDePublicacao, "Não informado");
    }

    public String getTitulo() {
        return titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public Autor getAutor() {
        return autor;
    }

    public Integer getAnoDePublicacao() {
        return anoDePublicacao;
    }

    public String getGenero() {
        return genero;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "titulo='" + titulo + '\'' +
                ", isbn='" + isbn + '\'' +
                ", autor=" + autor +
                ", anoDePublicacao=" + anoDePublicacao +
                ", genero='" + genero + '\'' +
                '}';
    }
}
