package br.escola.bibliohaydee.model;

public class Usuario {

    private String nome;
    private String matricula;
    private String tipo;
    private Integer limiteEmprestimos;
    private Integer emprestimosAtivos;

    public Usuario(String nomeInicial, String matriculaInicial,
                   String tipoInicial, Integer limiteInicial) {

        emprestimosAtivos = 0;

        if (nomeInicial != null && !nomeInicial.isEmpty()
                && matriculaInicial != null
                && !matriculaInicial.isEmpty()
                && tipoValido(tipoInicial)
                && limiteInicial != null
                && limiteInicial >= 0) {

            nome = nomeInicial;
            matricula = matriculaInicial;
            tipo = tipoInicial;
            limiteEmprestimos = limiteInicial;
        } else {
            System.out.println(
                    "Erro: dados invalidos para cadastrar usuario."
            );
        }
    }

    private boolean tipoValido(String novoTipo) {
        return novoTipo != null
                && (novoTipo.equals("ALUNO")
                || novoTipo.equals("PROFESSOR"));
    }

    public String getNome() {
        return nome;
    }

    public boolean setNome(String novoNome) {
        if (novoNome != null && !novoNome.isEmpty()) {
            nome = novoNome;
            return true;
        }
        return false;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getTipo() {
        return tipo;
    }

    public boolean setTipo(String novoTipo) {
        if (tipoValido(novoTipo)) {
            tipo = novoTipo;
            return true;
        }
        return false;
    }

    public Integer getLimiteEmprestimos() {
        return limiteEmprestimos;
    }

    public Integer getEmprestimosAtivos() {
        return emprestimosAtivos;
    }

    public boolean isValido() {
        return nome != null && !nome.isEmpty()
                && matricula != null && !matricula.isEmpty()
                && tipoValido(tipo)
                && limiteEmprestimos != null
                && limiteEmprestimos >= 0;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nome='" + nome + '\'' +
                ", matricula='" + matricula + '\'' +
                ", tipo='" + tipo + '\'' +
                ", limiteEmprestimos=" + limiteEmprestimos +
                ", emprestimosAtivos=" + emprestimosAtivos +
                '}';
    }
}