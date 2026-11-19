package br.escola.bibliohaydee.model;

public class Usuario {

    public static final int LIMITE_PADRAO = 3;

    private String nome;
    private String matricula;
    private String tipo;
    private Integer limiteEmprestimos;
    private Integer emprestimosAtivos;

    public Usuario(String nomeInicial, String matriculaInicial,
            String tipoInicial, Integer limiteInicial) {

        if (nomeValido(nomeInicial)
                && matriculaInicial != null
                && !matriculaInicial.isEmpty()
                && tipoValido(tipoInicial)
                && limiteValido(limiteInicial)) {

            nome = nomeInicial.trim();
            matricula = matriculaInicial;
            tipo = tipoInicial;
            limiteEmprestimos = limiteInicial;
            emprestimosAtivos = 0;

        } else {
            System.out.println("Erro: dados invalidos para cadastrar usuario.");
        }
    }

    // Construtor alternativo com limite padrao
    public Usuario(String nomeInicial, String matriculaInicial,
            String tipoInicial) {
        this(nomeInicial, matriculaInicial, tipoInicial, LIMITE_PADRAO);
    }

    public static boolean nomeValido(String nome) {
        return nome != null && !nome.trim().isEmpty();
    }

    public static boolean limiteValido(int limite) {
        return limite >= 1;
    }

    private boolean tipoValido(String novoTipo) {
        return novoTipo != null
                && (novoTipo.equals("ALUNO")
                || novoTipo.equals("PROFESSOR"));
    }

    public String getNome() {
        return nome;
    }

    public boolean setNome(String nome) {
        if (!nomeValido(nome)) {
            return false;
        }

        this.nome = nome.trim();
        return true;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getTipo() {
        return tipo;
    }

    public Integer getLimiteEmprestimos() {
        return limiteEmprestimos;
    }

    public boolean setLimiteEmprestimos(int limiteEmprestimos) {
        if (!limiteValido(limiteEmprestimos)) {
            return false;
        }

        this.limiteEmprestimos = limiteEmprestimos;
        return true;
    }

    public Integer getEmprestimosAtivos() {
        return emprestimosAtivos;
    }

    public boolean isValido() {
        return nomeValido(nome)
                && matricula != null
                && !matricula.isEmpty()
                && tipoValido(tipo)
                && limiteEmprestimos != null
                && limiteValido(limiteEmprestimos)
                && emprestimosAtivos != null;
    }

    @Override
    public String toString() {
        return "Usuario{"
                + "nome='" + nome + '\''
                + ", matricula='" + matricula + '\''
                + ", tipo='" + tipo + '\''
                + ", limiteEmprestimos=" + limiteEmprestimos
                + ", emprestimosAtivos=" + emprestimosAtivos
                + '}';
    }

    public boolean podeEmprestar() {
        return emprestimosAtivos < limiteEmprestimos;
    }

    public void registrarEmprestimo() {
        emprestimosAtivos++;
    }

}
