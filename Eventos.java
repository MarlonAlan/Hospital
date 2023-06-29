package Hospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Eventos implements ActionListener {
    public void actionPerformed(ActionEvent e){
        if (e.getActionCommand().equals("Agendar consulta")) {
            GuiAgendarConsulta telaAgendarConsulta = new GuiAgendarConsulta(this);
            telaAgendarConsulta.setVisible(true);

        } else if (e.getActionCommand().equals("Cadastro de Paciente")) {
            GuiCadastroPaciente telaCadastroPaciente = new GuiCadastroPaciente(this);
            telaCadastroPaciente.setVisible(true);

        } else if (e.getActionCommand().equals("Cadastro de Médico")) {
            GuiCadastroMedico telaCadastroMedico = new GuiCadastroMedico(this);
            telaCadastroMedico.setVisible(true);

        }
    }

}

