package get.a.big.head.newNRG.projectdocumentations;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class AddProjectDocumentationFrame extends JFrame {
    private JFrame frame;
    private JTextField textNameProject;
    private JTextField textCodeProject;
    private JButton buttonFile;
    private JButton buttonOk;
    private JButton buttonCancel;

    public AddProjectDocumentationFrame() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        frame.setSize(300, 800);

        JPanel panelLabels = new JPanel();
        JLabel labelFile = new JLabel("Документ");
        textNameProject = new JTextField(15);
        textCodeProject = new JTextField(15);
        buttonFile = new JButton("Прикрепить");

        panelLabels.add(new JLabel("Название проекта"));
        panelLabels.add(textNameProject);
        panelLabels.add(new JLabel("Шифр проекта"));
        panelLabels.add(textCodeProject);
        panelLabels.add(labelFile);
        panelLabels.add(buttonFile);
        frame.getContentPane().add(BorderLayout.CENTER, panelLabels);

        buttonOk = new JButton("OK");
        buttonCancel = new JButton("Отмена");
        JPanel panelButtons = new JPanel();
        panelButtons.add(buttonOk);
        panelButtons.add(buttonCancel);
        frame.getContentPane().add(BorderLayout.SOUTH, panelButtons);
        frame.setVisible(true);
    }
}
