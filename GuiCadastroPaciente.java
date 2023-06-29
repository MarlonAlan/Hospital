package Hospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiCadastroPaciente extends JFrame {
    private Eventos eventos;
    public JTextField tfCPF, tfNome, tfTelefone;

    public GuiCadastroPaciente(Eventos eventos){
        this.eventos = eventos;
        // Configurações da janela
        setTitle("Cadastro Paciente");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Obter a resolução do monitor
        Dimension telaTamanho = Toolkit.getDefaultToolkit().getScreenSize();
        int telaLargura = (int) (telaTamanho.getWidth() * 0.4); //40% da largura do monitor
        int telaAltura = (int) (telaTamanho.getHeight() * 0.4); //40% da altura do monitor
        setSize(telaLargura, telaAltura);

        // Centraliza a tela
        setLocationRelativeTo(null);

        // Cria e configura os componentes da tela
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel lbCPF = new JLabel("CPF: ");
        JLabel lbNome = new JLabel("Nome: ");
        JLabel lbTelefone = new JLabel("Telefone: ");
        tfCPF = new JTextField(11);
        tfNome = new JTextField(60);
        tfTelefone = new JTextField(11);
        JButton btBuscar = new JButton("Buscar");
        JButton btGravar = new JButton("Gravar");
        JButton btSalvar = new JButton("Salvar");
        JButton btExcluir = new JButton("Excluir");

        btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cpf = tfCPF.getText();

                BuscarDados.buscarPaciente(cpf, GuiCadastroPaciente.this);
            }
        });
        btGravar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cpf = tfCPF.getText();
                String nome = tfNome.getText();
                String telefone = tfTelefone.getText();

                InserirDados.inserirPaciente(nome, cpf, telefone);
            }
        });
        btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cpf = tfCPF.getText();
                String novoNome = tfNome.getText();
                String novoTelefone = tfTelefone.getText();

                AlterarDados.alterarPaciente(cpf, novoNome, novoTelefone);
            }
        });
        btExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cpf = tfCPF.getText();

                ExcluirDados.excluirPaciente(cpf);
            }
        });

        lbCPF.setBounds(30,30,120,25);
        lbNome.setBounds(30,60,120,25);
        lbTelefone.setBounds(30,90,120,25);
        tfCPF.setBounds(130,30,120,25);
        tfNome.setBounds(130,60,320,25);
        tfTelefone.setBounds(130,90,120,25);
        btBuscar.setBounds(30,300,100,25);
        btGravar.setBounds(130,300,100,25);
        btSalvar.setBounds(230,300,100,25);
        btExcluir.setBounds(330,300,100,25);

        panel.add(lbCPF);
        panel.add(tfCPF);
        panel.add(lbNome);
        panel.add(tfNome);
        panel.add(lbTelefone);
        panel.add(tfTelefone);
        panel.add(btBuscar);
        panel.add(btGravar);
        panel.add(btSalvar);
        panel.add(btExcluir);
        contentPane.add(panel, BorderLayout.CENTER);

        setContentPane(contentPane);
    }
}