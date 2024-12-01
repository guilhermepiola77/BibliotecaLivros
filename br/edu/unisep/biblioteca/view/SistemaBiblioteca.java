package br.edu.unisep.biblioteca.view;

import br.edu.unisep.biblioteca.model.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SistemaBiblioteca extends JFrame {
    private ArrayList<Livro> livros;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Emprestimo> emprestimos;

    public SistemaBiblioteca() {
        livros = new ArrayList<>();
        usuarios = new ArrayList<>();
        emprestimos = new ArrayList<>();
        initUI();
    }

    private void initUI() {
        setTitle("Sistema de Biblioteca");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        JPanel painelMenu = new JPanel();
        painelMenu.setLayout(new BoxLayout(painelMenu, BoxLayout.Y_AXIS));
        painelMenu.setPreferredSize(new Dimension(200, getHeight()));
        painelMenu.setBackground(Color.LIGHT_GRAY);

        JButton botaoCadastrarLivro = new JButton("Cadastrar Livro");
        botaoCadastrarLivro.addActionListener(e -> mostrarCadastroLivro());
        JButton botaoListarLivros = new JButton("Listar Livros");
        botaoListarLivros.addActionListener(e -> listarLivros());
        JButton botaoConsultarDisponiveis = new JButton("Consultar Livros Disponíveis");
        botaoConsultarDisponiveis.addActionListener(e -> consultarLivrosDisponiveis());
        JButton botaoListarEmprestimos = new JButton("Listar Livros no Empréstimo");
        botaoListarEmprestimos.addActionListener(e -> listarLivrosEmprestados());
        JButton botaoAdicionarUsuario = new JButton("Adicionar Usuário");
        botaoAdicionarUsuario.addActionListener(e -> mostrarCadastroUsuario());
        JButton botaoPegarEmprestado = new JButton("Pegar Livro Emprestado");
        botaoPegarEmprestado.addActionListener(e -> mostrarEmprestimoLivro());

        painelMenu.add(botaoCadastrarLivro);
        painelMenu.add(botaoListarLivros);
        painelMenu.add(botaoConsultarDisponiveis);
        painelMenu.add(botaoListarEmprestimos);
        painelMenu.add(botaoAdicionarUsuario);
        painelMenu.add(botaoPegarEmprestado);

        add(painelMenu, BorderLayout.WEST);

        JPanel painelConteudo = new JPanel();
        painelConteudo.setLayout(new BorderLayout());
        painelConteudo.setBackground(Color.WHITE);

        JLabel tituloPrincipal = new JLabel("Bem-vindo ao Sistema de Biblioteca", JLabel.CENTER);
        tituloPrincipal.setFont(new Font("Arial", Font.BOLD, 20));
        painelConteudo.add(tituloPrincipal, BorderLayout.CENTER);

        add(painelConteudo, BorderLayout.CENTER);
    }

    private void listarLivros() {
        StringBuilder listaLivros = new StringBuilder("Livros cadastrados:\n");
        for (Livro livro : livros) {
            listaLivros.append(livro.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(this, listaLivros.toString());
    }

    private void consultarLivrosDisponiveis() {
        StringBuilder livrosDisponiveis = new StringBuilder("Livros Disponíveis:\n");
        for (Livro livro : livros) {
            boolean emprestado = emprestimos.stream().anyMatch(emp -> emp.getLivro().equals(livro) && emp.getStatus().equals("Em Empréstimo"));
            if (!emprestado) {
                livrosDisponiveis.append(livro.toString()).append("\n");
            }
        }
        JOptionPane.showMessageDialog(this, livrosDisponiveis.toString());
    }

    private void listarLivrosEmprestados() {
        StringBuilder livrosEmprestados = new StringBuilder("Livros no Empréstimo:\n");
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getStatus().equals("Em Empréstimo")) {
                livrosEmprestados.append(emprestimo.getLivro().toString())
                        .append(" - Emprestado para: ")
                        .append(emprestimo.getUsuario().getNome())
                        .append("\n");
            }
        }
        JOptionPane.showMessageDialog(this, livrosEmprestados.toString());
    }

    private void mostrarCadastroLivro() {
        JFrame frameCadastroLivro = new JFrame("Cadastro de Livro");
        frameCadastroLivro.setSize(400, 400);
        frameCadastroLivro.setLayout(new GridLayout(7, 2));

        JTextField campoTitulo = new JTextField();
        JTextField campoAutor = new JTextField();
        JTextField campoGenero = new JTextField();
        JTextField campoIsbn = new JTextField();
        JTextField campoAnoPublicacao = new JTextField();
        JTextField campoEditora = new JTextField();

        JButton botaoFisico = new JButton("Livro Físico");
        JButton botaoDigital = new JButton("Livro Digital");

        String[] tipoLivro = new String[1];

        botaoFisico.addActionListener(e -> tipoLivro[0] = "Físico");
        botaoDigital.addActionListener(e -> tipoLivro[0] = "Digital");

        JButton botaoSalvar = new JButton("Salvar");
        botaoSalvar.addActionListener(e -> {
            try {
                String titulo = campoTitulo.getText();
                String autor = campoAutor.getText();
                String genero = campoGenero.getText();
                String isbn = campoIsbn.getText();
                int anoPublicacao = Integer.parseInt(campoAnoPublicacao.getText());
                String editora = campoEditora.getText();

                if ("Físico".equals(tipoLivro[0])) {
                    livros.add(new LivroFisico(titulo, autor, genero, isbn, anoPublicacao, editora, "Codigo123"));
                } else if ("Digital".equals(tipoLivro[0])) {
                    livros.add(new LivroDigital(titulo, autor, genero, isbn, anoPublicacao, editora, "http://livro.com"));
                }

                JOptionPane.showMessageDialog(frameCadastroLivro, "Livro cadastrado com sucesso!");
                frameCadastroLivro.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frameCadastroLivro, "Ano de publicação deve ser um número válido!");
            }
        });

        frameCadastroLivro.add(new JLabel("Título:"));
        frameCadastroLivro.add(campoTitulo);
        frameCadastroLivro.add(new JLabel("Autor:"));
        frameCadastroLivro.add(campoAutor);
        frameCadastroLivro.add(new JLabel("Gênero:"));
        frameCadastroLivro.add(campoGenero);
        frameCadastroLivro.add(new JLabel("ISBN:"));
        frameCadastroLivro.add(campoIsbn);
        frameCadastroLivro.add(new JLabel("Ano de Publicação:"));
        frameCadastroLivro.add(campoAnoPublicacao);
        frameCadastroLivro.add(new JLabel("Editora:"));
        frameCadastroLivro.add(campoEditora);

        frameCadastroLivro.add(botaoFisico);
        frameCadastroLivro.add(botaoDigital);
        frameCadastroLivro.add(botaoSalvar);

        frameCadastroLivro.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameCadastroLivro.setVisible(true);
    }

    private void mostrarCadastroUsuario() {
        JTextField campoNomeUsuario = new JTextField();
        int opcao = JOptionPane.showConfirmDialog(this, campoNomeUsuario, "Digite o nome do usuário", JOptionPane.OK_CANCEL_OPTION);
        if (opcao == JOptionPane.OK_OPTION) {
            String nome = campoNomeUsuario.getText();
            usuarios.add(new Usuario(nome));
            JOptionPane.showMessageDialog(this, "Usuário adicionado com sucesso!");
        }
    }

    private void mostrarEmprestimoLivro() {
        JComboBox<Livro> comboLivros = new JComboBox<>();
        for (Livro livro : livros) {
            comboLivros.addItem(livro);
        }

        JComboBox<Usuario> comboUsuarios = new JComboBox<>();
        for (Usuario usuario : usuarios) {
            comboUsuarios.addItem(usuario);
        }

        JPanel panel = new JPanel();
        panel.add(new JLabel("Escolha um livro:"));
        panel.add(comboLivros);
        panel.add(new JLabel("Escolha o usuário:"));
        panel.add(comboUsuarios);

        int opcao = JOptionPane.showConfirmDialog(this, panel, "Emprestar Livro", JOptionPane.OK_CANCEL_OPTION);
        if (opcao == JOptionPane.OK_OPTION) {
            Livro livroEscolhido = (Livro) comboLivros.getSelectedItem();
            Usuario usuarioEscolhido = (Usuario) comboUsuarios.getSelectedItem();

            if (livroEscolhido != null && usuarioEscolhido != null) {
                Emprestimo emprestimo = new Emprestimo(livroEscolhido, usuarioEscolhido, "Em Empréstimo");
                emprestimos.add(emprestimo);
                JOptionPane.showMessageDialog(this, "Livro emprestado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um livro e um usuário válidos!");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SistemaBiblioteca sistema = new SistemaBiblioteca();
            sistema.setVisible(true);
        });
    }
}
