package Hospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiAgendarConsulta extends JFrame {
    private Eventos eventos;
    public JTextField tfCPF, tfData, tfHora, tfMedico;
    public GuiAgendarConsulta(Eventos eventos){
        this.eventos = eventos;
        // Configurações da janela
        setTitle("Marcar Consulta");
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
        JLabel lbData = new JLabel("Data: ");
        JLabel lbHora = new JLabel("Hora: ");
        JLabel lbMedico = new JLabel("Médico: ");

        tfCPF = new JTextField(11);
        tfData = new JTextField(8);
        tfHora = new JTextField(4);
        tfMedico = new JTextField(60);

        JButton btGravar = new JButton("Gravar");
        JButton btSalvar = new JButton("Salvar");
        JButton btExcluir = new JButton("Excluir");

        btGravar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cpf = tfCPF.getText();
                String data = tfData.getText();
                String hora = tfHora.getText();
                String medico = tfMedico.getText();

                InserirDados.inserirConsulta(cpf, data, hora, medico);
            }
        });
        btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cpf = tfCPF.getText();
                String novaData = tfData.getText();
                String novoHorario = tfHora.getText();

                AlterarDados.alterarConsulta(cpf, novaData, novoHorario);
            }
        });
        btExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cpf = tfCPF.getText();

                ExcluirDados.excluirConsulta(cpf);
            }
        });

        lbCPF.setBounds(30,30,120,25);
        lbData.setBounds(30,60,120,25);
        lbHora.setBounds(30,90,120,25);
        lbMedico.setBounds(30,120,120,25);
        tfCPF.setBounds(130,30,120,25);
        tfData.setBounds(130,60,120,25);
        tfHora.setBounds(130,90,120,25);
        tfMedico.setBounds(130,120,320,25);
        btGravar.setBounds(130,300,100,25);
        btSalvar.setBounds(230,300,100,25);
        btExcluir.setBounds(330,300,100,25);

        panel.add(lbCPF);
        panel.add(tfCPF);
        panel.add(lbData);
        panel.add(tfData);
        panel.add(lbHora);
        panel.add(tfHora);
        panel.add(lbMedico);
        panel.add(tfMedico);
        panel.add(btGravar);
        panel.add(btSalvar);
        panel.add(btExcluir);
        contentPane.add(panel, BorderLayout.CENTER);

        setContentPane(contentPane);
    }
}
