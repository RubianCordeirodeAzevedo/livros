import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Classe principal da Biblioteca
public class Biblioteca {
    
    // Lista para armazenar todos os livros da biblioteca
    private List<Livro> livros;

    // Construtor inicializa a lista de livros
    public Biblioteca() {
        this.livros = new ArrayList<>();
    }

    // Método principal que roda o sistema
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca(); // Instância da biblioteca
        biblioteca.inicializar(); // Carrega alguns livros
        biblioteca.menu(); // Exibe o menu principal
    }

    // Inicializa com alguns livros padrões
    private void inicializar() {
        livros.add(new LivroFiccao("1984", "George Orwell", 328, "Distopia"));
        livros.add(new LivroFiccao("Fahrenheit 451", "Ray Bradbury", 256, "Distopia"));
        livros.add(new LivroDidatico("Java para Iniciantes", "Paulo Silveira", 500, "Programação"));
        livros.add(new LivroDidatico("Aprendendo SQL", "Alan Beaulieu", 400, "Banco de Dados"));
    }

    // Exibe o menu principal e executa as ações
    private void menu() {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("==== Biblioteca ====");
            System.out.println("1. Listar Livros");
            System.out.println("2. Adicionar Livro");
            System.out.println("3. Remover Livro");
            System.out.println("4. Buscar Livro");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    listarLivros();
                    break;
                case 2:
                    adicionarLivro();
                    break;
                case 3:
                    removerLivro();
                    break;
                case 4:
                    buscarLivro();
                    break;
                case 5:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 5);

        scanner.close();
    }

    // Método para listar todos os livros
    private void listarLivros() {
        System.out.println("==== Lista de Livros ====");
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro disponível.");
        } else {
            for (Livro livro : livros) {
                System.out.println(livro);
            }
        }
    }

    // Método para adicionar um novo livro
    private void adicionarLivro() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o título do livro: ");
        String titulo = scanner.nextLine();
        System.out.print("Digite o autor do livro: ");
        String autor = scanner.nextLine();
        System.out.print("Digite o número de páginas: ");
        int paginas = scanner.nextInt();
        scanner.nextLine(); // Consumir nova linha
        System.out.print("Digite o tipo (Ficção ou Didático): ");
        String tipo = scanner.nextLine();

        if (tipo.equalsIgnoreCase("Ficção")) {
            System.out.print("Digite o gênero: ");
            String genero = scanner.nextLine();
            livros.add(new LivroFiccao(titulo, autor, paginas, genero));
        } else if (tipo.equalsIgnoreCase("Didático")) {
            System.out.print("Digite a área de estudo: ");
            String areaEstudo = scanner.nextLine();
            livros.add(new LivroDidatico(titulo, autor, paginas, areaEstudo));
        } else {
            System.out.println("Tipo inválido.");
        }
    }

    // Método para remover um livro pelo título
    private void removerLivro() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o título do livro a remover: ");
        String titulo = scanner.nextLine();
        Livro livroParaRemover = null;

        for (Livro livro : livros) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                livroParaRemover = livro;
                break;
            }
        }

        if (livroParaRemover != null) {
            livros.remove(livroParaRemover);
            System.out.println("Livro removido com sucesso.");
        } else {
            System.out.println("Livro não encontrado.");
        }
    }

    // Método para buscar um livro pelo título
    private void buscarLivro() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o título do livro a buscar: ");
        String titulo = scanner.nextLine();

        for (Livro livro : livros) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                System.out.println(livro);
                return;
            }
        }

        System.out.println("Livro não encontrado.");
    }
}

// Classe abstrata Livro que representa as informações comuns a todos os livros
abstract class Livro {
    private String titulo;
    private String autor;
    private int paginas;

    // Construtor para inicializar os atributos comuns
    public Livro(String titulo, String autor, int paginas) {
        this.titulo = titulo;
        this.autor = autor;
        this.paginas = paginas;
    }

    // Métodos getter para acessar os atributos
    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getPaginas() {
        return paginas;
    }

    // Método abstrato que será implementado pelas subclasses
    public abstract String getTipo();

    @Override
    public String toString() {
        return String.format("Título: %s, Autor: %s, Páginas: %d, Tipo: %s", titulo, autor, paginas, getTipo());
    }
}

// Classe LivroFiccao que herda de Livro e representa livros de ficção
class LivroFiccao extends Livro {
    private String genero;

    // Construtor para inicializar os atributos específicos e herdados
    public LivroFiccao(String titulo, String autor, int paginas, String genero) {
        super(titulo, autor, paginas);
        this.genero = genero;
    }

    // Sobrescreve o método abstrato getTipo para retornar "Ficção"
    @Override
    public String getTipo() {
        return "Ficção (Gênero: " + genero + ")";
    }

    public String getGenero() {
        return genero;
    }
}

// Classe LivroDidatico que herda de Livro e representa livros didáticos
class LivroDidatico extends Livro {
    private String areaEstudo;

    // Construtor para inicializar os atributos específicos e herdados
    public LivroDidatico(String titulo, String autor, int paginas, String areaEstudo) {
        super(titulo, autor, paginas);
        this.areaEstudo = areaEstudo;
    }

    // Sobrescreve o método abstrato getTipo para retornar "Didático"
    @Override
    public String getTipo() {
        return "Didático (Área de Estudo: " + areaEstudo + ")";
    }

    public String getAreaEstudo() {
        return areaEstudo;
    }
}
