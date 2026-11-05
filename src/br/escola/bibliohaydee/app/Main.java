
package br.escola.bibliohaydee.app;

import br.escola.bibliohaydee.model.Autor;
import br.escola.bibliohaydee.model.Livro;
import br.escola.bibliohaydee.model.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Usuario> usuarios = new ArrayList<>();
    private static final List<Autor> autores = new ArrayList<>();
    private static final List<Livro> livros = new ArrayList<>();

    public static void main(String[] args) {
        carregarAcervo();

        int opcao;

        do {
            System.out.println("\n===== BIBLIOTECA HAYDEE =====");
            System.out.println("1 - Cadastrar usuário");
            System.out.println("2 - Listar usuários");
            System.out.println("3 - Atualizar usuário");
            System.out.println("4 - Listar livros");
            System.out.println("5 - Pesquisar livro por título");
            System.out.println("6 - Pesquisar livro por autor");
            System.out.println("7 - Gerar relatório do acervo");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            opcao = lerInteiro();

            switch (opcao) {
                case 1 -> cadastrarUsuario();
                case 2 -> listarUsuarios();
                case 3 -> atualizarUsuario();
                case 4 -> listarLivros();
                case 5 -> pesquisarPorTitulo();
                case 6 -> pesquisarPorAutor();
                case 7 -> gerarRelatorio();
                case 0 -> System.out.println("Programa encerrado.");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);

        scanner.close();
    }

    private static void carregarAcervo() {
        adicionarLivro("Dom Casmurro", "111111", "Machado de Assis", 1899);
        adicionarLivro("Memórias Póstumas de Brás Cubas", "222222", "Machado de Assis", 1881);
        adicionarLivro("Quincas Borba", "333333", "Machado de Assis", 1891);
        adicionarLivro("Vidas Secas", "444444", "Graciliano Ramos", 1938);
        adicionarLivro("O Cortiço", "555555", "Aluísio Azevedo", 1890);
        adicionarLivro("Capitães da Areia", "666666", "Jorge Amado", 1937);
    }

    private static void adicionarLivro(String titulo, String isbn, String nomeAutor, int ano) {
        Autor autor = new Autor();
        autor.setNome(nomeAutor);
        autor.setNacionalidade("Brasileira");
        autor.setAnoNascimento(0);
        autores.add(autor);

        Livro livro = new Livro(titulo, isbn, autor, ano);
        livros.add(livro);
    }

    private static void cadastrarUsuario() {
        System.out.println("\n--- CADASTRAR USUÁRIO ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine().trim();

        System.out.print("Matrícula: ");
        String matricula = scanner.nextLine().trim();

        System.out.print("Tipo (ALUNO ou PROFESSOR): ");
        String tipo = scanner.nextLine().trim().toUpperCase();

        if (!Usuario.nomeValido(nome) || matricula.isEmpty()
                || (!tipo.equals("ALUNO") && !tipo.equals("PROFESSOR"))) {
            System.out.println("Dados inválidos. Usuário não cadastrado.");
            return;
        }

        System.out.print("Definir o limite? (S/N): ");
        String resposta = scanner.nextLine().trim();

        Usuario usuario;

        if (resposta.equalsIgnoreCase("S")) {
            System.out.print("Informe o limite de empréstimos: ");
            int limite = lerInteiro();

            if (!Usuario.limiteValido(limite)) {
                System.out.println("Limite inválido.");
                return;
            }

            usuario = new Usuario(nome, matricula, tipo, limite);
        } else if (resposta.equalsIgnoreCase("N")) {
            usuario = new Usuario(nome, matricula, tipo);
        } else {
            System.out.println("Resposta inválida. Use S ou N.");
            return;
        }

        if (usuario.isValido()) {
            usuarios.add(usuario);
            System.out.println("Usuário cadastrado com sucesso!");
            System.out.println(usuario);
        } else {
            System.out.println("Não foi possível cadastrar o usuário.");
        }
    }

    private static void listarUsuarios() {
        System.out.println("\n--- USUÁRIOS CADASTRADOS ---");

        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }

        for (Usuario usuario : usuarios) {
            System.out.println(usuario);
        }
    }

    private static Usuario buscarUsuario(String matricula) {
        for (Usuario usuario : usuarios) {
            if (usuario.getMatricula().equalsIgnoreCase(matricula)) {
                return usuario;
            }
        }
        return null;
    }

    private static void atualizarUsuario() {
        System.out.println("\n--- ATUALIZAR USUÁRIO ---");
        System.out.print("Digite a matrícula do usuário: ");
        String matricula = scanner.nextLine().trim();

        Usuario usuario = buscarUsuario(matricula);

        if (usuario == null) {
            System.out.println("Usuário não localizado. Nenhuma alteração feita.");
            return;
        }

        System.out.println("Usuário encontrado: " + usuario);
        System.out.println("1 - Alterar nome");
        System.out.println("2 - Alterar limite de empréstimos");
        System.out.print("Escolha: ");

        int opcao = lerInteiro();

        switch (opcao) {
            case 1 -> {
                System.out.print("Novo nome: ");
                String novoNome = scanner.nextLine();

                if (usuario.setNome(novoNome)) {
                    System.out.println("Nome atualizado com sucesso!");
                } else {
                    System.out.println("Nome inválido. Nenhuma alteração feita.");
                }
            }
            case 2 -> {
                System.out.print("Novo limite: ");
                int novoLimite = lerInteiro();

                if (usuario.setLimiteEmprestimos(novoLimite)) {
                    System.out.println("Limite atualizado com sucesso!");
                } else {
                    System.out.println("Limite inválido. Nenhuma alteração feita.");
                }
            }
            default -> System.out.println("Opção inválida. Nenhuma alteração feita.");
        }

        System.out.println("Estado atual: " + usuario);
    }

    private static void listarLivros() {
        System.out.println("\n--- LIVROS CADASTRADOS ---");

        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
            return;
        }

        for (Livro livro : livros) {
            System.out.println(livro);
        }
    }

    private static void pesquisarPorTitulo() {
        System.out.print("Digite parte do título: ");
        String termo = scanner.nextLine().trim().toLowerCase(Locale.ROOT);

        if (termo.isEmpty()) {
            System.out.println("Termo de pesquisa vazio.");
            return;
        }

        boolean encontrou = false;

        for (Livro livro : livros) {
            String titulo = livro.getTitulo().trim().toLowerCase(Locale.ROOT);

            if (titulo.contains(termo)) {
                System.out.println(livro);
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhum livro encontrado.");
        }
    }

    private static void pesquisarPorAutor() {
        System.out.print("Digite o nome do autor: ");
        String termo = scanner.nextLine().trim().toLowerCase(Locale.ROOT);

        if (termo.isEmpty()) {
            System.out.println("Nome do autor vazio.");
            return;
        }

        boolean encontrou = false;

        for (Livro livro : livros) {
            String nomeAutor = livro.getAutor().getNome()
                    .trim().toLowerCase(Locale.ROOT);

            if (nomeAutor.equals(termo)) {
                System.out.println(livro);
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Autor não encontrado.");
        }
    }

    private static void gerarRelatorio() {
        StringBuilder relatorio = new StringBuilder();

        relatorio.append("\n===== RELATÓRIO DO ACERVO =====\n");

        if (livros.isEmpty()) {
            relatorio.append("Não há livros no acervo.\n");
        } else {
            int numero = 1;

            for (Livro livro : livros) {
                relatorio.append(numero)
                        .append(". Título: ")
                        .append(livro.getTitulo())
                        .append(" | Autor: ")
                        .append(livro.getAutor().getNome())
                        .append(" | Disponibilidade: Disponível\n");

                numero++;
            }

            relatorio.append("Total de livros: ")
                    .append(livros.size())
                    .append("\n");
        }

        System.out.println(relatorio.toString());
    }

    private static int lerInteiro() {
        while (true) {
            String entrada = scanner.nextLine().trim();

            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.print("Digite um número inteiro válido: ");
            }
        }
    }
}
