package Hospital;

import javax.swing.*;
import java.awt.*;

public class GuiMenu extends JFrame {
    public GuiMenu() {
        // Configurações da janela
        setTitle("Hospital IFG");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Obter a resolução do monitor
        Dimension telaTamanho = Toolkit.getDefaultToolkit().getScreenSize();
        int telaLargura = (int) (telaTamanho.getWidth() * 0.25); //25% da largura do monitor
        int telaAltura = (int) (telaTamanho.getHeight() * 0.25); //25% da altura do monitor
        setSize(telaLargura, telaAltura);

        // Centraliza a tela
        setLocationRelativeTo(null);

        // Cria e configura os componenetes da tela
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JButton agendarConsulta = new JButton("Agendar consulta");
        JButton cadastroPaciente = new JButton("Cadastro de Paciente");
        JButton cadastroMedico = new JButton("Cadastro de Médico");

        // Define a largura e altura dos botões
        int larguraBotao = 200;
        int alturaBotao = 30;

        // Centraliza os botões horizontalmente
        int posicaoX = (telaLargura - larguraBotao) / 2;

        // Define as posições dos botões verticalmente
        int espacamentoVertical = 20;
        int posicaoYInicial = (telaAltura - (alturaBotao * 5 + espacamentoVertical * 3)) / 2;

        agendarConsulta.setBounds(posicaoX, posicaoYInicial, larguraBotao, alturaBotao);
        cadastroPaciente.setBounds(posicaoX, posicaoYInicial + 2 * (alturaBotao + espacamentoVertical), larguraBotao, alturaBotao);
        cadastroMedico.setBounds(posicaoX, posicaoYInicial + 3 * (alturaBotao + espacamentoVertical), larguraBotao, alturaBotao);
        Eventos eventos = new Eventos();

        agendarConsulta.addActionListener(eventos);
        cadastroPaciente.addActionListener(eventos);
        cadastroMedico.addActionListener(eventos);

        panel.add(agendarConsulta);
        panel.add(cadastroPaciente);
        panel.add(cadastroMedico);
        contentPane.add(panel, BorderLayout.CENTER);

        setContentPane(contentPane);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GuiMenu telaMenu = new GuiMenu();
            telaMenu.setVisible(true);
        });
    }
}
