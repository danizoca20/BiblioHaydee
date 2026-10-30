
package br.escola.bibliohaydee.app;

import br.escola.bibliohaydee.model.Autor;
import br.escola.bibliohaydee.model.Livro;
import br.escola.bibliohaydee.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Usuario> usuarios = new ArrayList<>();
    private static final List<Autor> autores = new ArrayList<>();
    private static final List<Livro> livros = new ArrayList<>();

    public static void main(String[] args) {
       Autor autor = new Autor();
autor.setNome("Machado de Assis");
autor.setNacionalidade("Brasileira");
autor.setAnoNascimento(1839);

autores.add(autor);

        Livro livro = new Livro(
                "Dom Casmurro", "123456", autor, 1899
        );
        livros.add(livro);

        int opcao;

        do {
            System.out.println("\n===== BIBLIOTECA HAYDEE =====");
            System.out.println("1 - Cadastrar usuário");
            System.out.println("2 - Listar usuários");
            System.out.println("3 - Atualizar usuário");
            System.out.println("4 - Listar livros");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            opcao = lerInteiro();

            switch (opcao) {
                case 1 -> cadastrarUsuario();
                case 2 -> listarUsuarios();
                case 3 -> atualizarUsuario();
                case 4 -> listarLivros();
                case 0 -> System.out.println("Programa encerrado.");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);

        scanner.close();
    }

    private static void cadastrarUsuario() {
        System.out.println("\n--- CADASTRAR USUÁRIO ---");

        System.out.print("Nome: ");
        String nome = scanner.nextLine().trim();

        System.out.print("Matrícula: ");
        String matricula = scanner.nextLine().trim();

        System.out.print("Tipo (ALUNO ou PROFESSOR): ");
        String tipo = scanner.nextLine().trim().toUpperCase();

        if (!Usuario.nomeValido(nome)
                || matricula.isEmpty()
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
                System.out.println("Limite inválido. Use um número maior que zero.");
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
                    System.out.println("Limite inválido. Use um número maior que zero.");
                }
            }
            default -> System.out.println("Opção inválida. Nenhuma alteração feita.");
        }

        System.out.println("Estado atual: " + usuario);
    }

    private static void listarLivros() {
        System.out.println("\n--- LIVROS CADASTRADOS ---");

        for (Livro livro : livros) {
            System.out.println(livro);
        }
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
