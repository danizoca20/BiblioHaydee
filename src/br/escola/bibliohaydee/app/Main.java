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

        // Cadastro do autor
        Autor autor1 = new Autor();
        autor1.setNome("Machado de Assis");
        autor1.setNacionalidade("Brasileira");
        autor1.setAnoNascimento(1839);
        autores.add(autor1);

        // Cadastro do livro
        Livro livro1 = new Livro(
                "Dom Casmurro",
                "978-85-359-0277-5",
                autor1,
                1899,
                "Romance"
        );
        livros.add(livro1);

        // Listagem de autores
        System.out.println("=== AUTORES CADASTRADOS ===");
        for (Autor autor : autores) {
            System.out.println(autor);
        }

        // Listagem de livros
        System.out.println("\n=== ACERVO DE LIVROS ===");
        if (livros.isEmpty()) {
            System.out.println("O acervo esta vazio.");
        } else {
            for (Livro livro : livros) {
                System.out.println(livro);
            }
        }

        // Cadastro de usuario
        System.out.println("\n=== CADASTRO DE USUARIO ===");

        String nome;
        do {
            System.out.print("Nome: ");
            nome = scanner.nextLine();

            if (nome == null || nome.isEmpty()) {
                System.out.println("Nome obrigatorio. Tente novamente.");
            }
        } while (nome == null || nome.isEmpty());

        String matricula;
        do {
            System.out.print("Matricula: ");
            matricula = scanner.nextLine();

            if (matricula.isEmpty()) {
                System.out.println("Matricula obrigatoria. Tente novamente.");
            }
        } while (matricula.isEmpty());

        String tipo;
        do {
            System.out.print("Tipo (ALUNO ou PROFESSOR): ");
            tipo = scanner.nextLine();

            if (!tipo.equals("ALUNO") && !tipo.equals("PROFESSOR")) {
                System.out.println(
                        "Tipo invalido. Digite ALUNO ou PROFESSOR."
                );
            }
        } while (!tipo.equals("ALUNO") && !tipo.equals("PROFESSOR"));

        int limite;
        do {
            System.out.print("Limite de emprestimos: ");
            String limiteDigitado = scanner.nextLine();

            // Aceita somente numeros inteiros nao negativos.
          if (limiteDigitado.matches("[0-9]+")) {
    try {
        limite = Integer.parseInt(limiteDigitado);
        break;
    } catch (NumberFormatException e) {
        System.out.println("Numero muito grande. Tente novamente.");
    }
} else {
    System.out.println("Digite um numero inteiro igual ou maior que zero.");
}

limite = -1;
            }

            System.out.println("Digite um numero inteiro igual ou maior que zero.");
            limite = -1;

        } while (limite < 0);

        // Criacao do usuario somente depois da entrada validada
        Usuario usuario1 = new Usuario(
                nome, matricula, tipo, limite
        );

        if (usuario1.isValido()) {
            usuarios.add(usuario1);

            System.out.println("\n=== USUARIO CADASTRADO ===");
            System.out.println(usuario1);
            System.out.println(
                    "Emprestimos ativos: " + usuario1.getEmprestimosAtivos()
            );
        }

        scanner.close();
    }
}