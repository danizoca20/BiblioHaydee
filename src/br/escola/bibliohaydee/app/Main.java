
package br.escola.bibliohaydee.app;

import br.escola.bibliohaydee.model.Autor;
import br.escola.bibliohaydee.model.Livro;
import br.escola.bibliohaydee.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Autor> autores = new ArrayList<>();
        List<Livro> livros = new ArrayList<>();
        List<Usuario> usuarios = new ArrayList<>();

        Autor autor1 = new Autor();
        autor1.setNome("Machado de Assis");
        autor1.setNacionalidade("Brasileira");
        autor1.setAnoNascimento(1839);
        autores.add(autor1);

        Livro livro1 = new Livro(
            "Dom Casmurro",
            "978-85-359-0277-5",
            autor1,
            1899,
            "Romance"
        );
        livros.add(livro1);

        System.out.println("=== AUTORES CADASTRADOS ===");
        for (Autor autor : autores) {
            System.out.println(autor);
        }

        System.out.println("\n=== ACERVO DE LIVROS ===");
        for (Livro livro : livros) {
            System.out.println(livro);
        }

        System.out.println("\n=== CADASTRO DE USUARIO ===");

        String nome;
        do {
            System.out.print("Nome: ");
            nome = scanner.nextLine().trim();

            if (nome.isEmpty()) {
                System.out.println("Nome obrigatorio. Tente novamente.");
            }
        } while (nome.isEmpty());

        String matricula;
        do {
            System.out.print("Matricula: ");
            matricula = scanner.nextLine().trim();

            if (matricula.isEmpty()) {
                System.out.println("Matricula obrigatoria. Tente novamente.");
            }
        } while (matricula.isEmpty());

        String tipo;
        do {
            System.out.print("Tipo (ALUNO ou PROFESSOR): ");
            tipo = scanner.nextLine().trim().toUpperCase();

            if (!tipo.equals("ALUNO") && !tipo.equals("PROFESSOR")) {
                System.out.println("Tipo invalido. Digite ALUNO ou PROFESSOR.");
            }
        } while (!tipo.equals("ALUNO") && !tipo.equals("PROFESSOR"));

        int limite;
        while (true) {
            System.out.print("Limite de emprestimos: ");
            String entrada = scanner.nextLine().trim();

            try {
                limite = Integer.parseInt(entrada);
                if (limite >= 0) {
                    break;
                }
            } catch (NumberFormatException e) {
                // Solicita novamente se a entrada nao for um inteiro.
            }

            System.out.println("Digite um numero inteiro igual ou maior que zero.");
        }

        Usuario usuario1 = new Usuario(nome, matricula, tipo, limite);

        if (usuario1.isValido()) {
            usuarios.add(usuario1);
            System.out.println("\n=== USUARIO CADASTRADO ===");
            System.out.println(usuario1);
            System.out.println(
                "Emprestimos ativos: " + usuario1.getEmprestimosAtivos()
            );
        } else {
            System.out.println("Nao foi possivel cadastrar o usuario.");
        }

        scanner.close();
    }
}
