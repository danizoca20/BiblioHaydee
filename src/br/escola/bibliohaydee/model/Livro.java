
package br.escola.bibliohaydee.model;

public class Livro {

    private String titulo;
    private String isbn;
    private Autor autor;
    private Integer anoDePublicacao;
    private String genero;
    private boolean disponivel;

    public Livro(String titulo, String isbn, Autor autor,
                 Integer anoDePublicacao, String genero) {
        this.titulo = titulo;
        this.isbn = isbn;
        this.autor = autor;
        this.anoDePublicacao = anoDePublicacao;
        this.genero = genero;
        this.disponivel = true;
    }

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

    public boolean emprestar() {
        if (!disponivel) {
            return false;
        }

        disponivel = false;
        return true;
    }

    public boolean estaDisponivel() {
        return disponivel;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "titulo='" + titulo + '\'' +
                ", isbn='" + isbn + '\'' +
                ", autor=" + autor.getNome() +
                ", anoDePublicacao=" + anoDePublicacao +
                ", genero='" + genero + '\'' +
                ", disponivel=" + disponivel +
                '}';
    }
}
