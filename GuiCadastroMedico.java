package Hospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class GuiCadastroMedico extends JFrame {
    private Eventos eventos;
    public JTextField tfCPF, tfNome;
    public JComboBox<String> cbEspecialidade;
    public GuiCadastroMedico(Eventos eventos){
        this.eventos = eventos;
        // Configurações da janela
        setTitle("Cadastro Médico");
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
        JLabel lbEspecialidade = new JLabel("Especialidade: ");

        tfCPF = new JTextField(11);
        tfNome = new JTextField(60);

        cbEspecialidade = new JComboBox<>();
        cbEspecialidade.addItem("Alergista");
        cbEspecialidade.addItem("Angiologista");
        cbEspecialidade.addItem("Cardiologista");
        cbEspecialidade.addItem("Dermatologista");
        cbEspecialidade.addItem("Endocrinologista");
        cbEspecialidade.addItem("Ginocologista");
        cbEspecialidade.addItem("Infectologista");
        cbEspecialidade.addItem("Neurologista");
        cbEspecialidade.addItem("Oftalmologista");
        cbEspecialidade.addItem("Pediatra");
        cbEspecialidade.addItem("Urologista");

        JButton btBuscar = new JButton("Buscar");
        JButton btGravar = new JButton("Gravar");
        JButton btSalvar = new JButton("Salvar");
        JButton btExcluir = new JButton("Excluir");

        btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cpf = tfCPF.getText();

                BuscarDados.buscarMedico(cpf, GuiCadastroMedico.this, cbEspecialidade);
            }
        });
        btGravar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cpf = tfCPF.getText();
                String nome = tfNome.getText();
                String especialidade = (String) cbEspecialidade.getSelectedItem();

                InserirDados.inserirMedico(nome, cpf, especialidade);
            }
        });
        btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cpf = tfCPF.getText();
                String novoNome = tfNome.getText();
                String novaEspecialidade = (String) cbEspecialidade.getSelectedItem();

                AlterarDados.alterarMedico(cpf, novoNome, novaEspecialidade);
            }
        });
        btExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cpf = tfCPF.getText();

                ExcluirDados.excluirMedico(cpf);
            }
        });

        lbCPF.setBounds(30,30,120,25);
        lbNome.setBounds(30,60,120,25);
        lbEspecialidade.setBounds(30,90,120,25);
        tfCPF.setBounds(130,30,120,25);
        tfNome.setBounds(130,60,320,25);
        cbEspecialidade.setBounds(130,90,120,25);
        btBuscar.setBounds(30,300,100,25);
        btGravar.setBounds(130,300,100,25);
        btSalvar.setBounds(230,300,100,25);
        btExcluir.setBounds(330,300,100,25);

        panel.add(lbCPF);
        panel.add(tfCPF);
        panel.add(lbNome);
        panel.add(tfNome);
        panel.add(lbEspecialidade);
        panel.add(cbEspecialidade);
        panel.add(btBuscar);
        panel.add(btGravar);
        panel.add(btSalvar);
        panel.add(btExcluir);
        contentPane.add(panel, BorderLayout.CENTER);

        setContentPane(contentPane);
    }
}
