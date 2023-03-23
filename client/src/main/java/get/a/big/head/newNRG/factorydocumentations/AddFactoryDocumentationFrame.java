package get.a.big.head.newNRG.factorydocumentations;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class AddFactoryDocumentationFrame extends JFrame {

    private JFrame frame;
    private JTextField textNameDocument;
    private JTextField textCodeDocument;
    private JButton buttonFile;
    private JButton buttonOk;
    private JButton buttonCancel;

    public AddFactoryDocumentationFrame() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        frame.setSize(300, 800);

        JPanel panelLabels = new JPanel();
        JLabel labelFile = new JLabel("Документ");
        textNameDocument = new JTextField(15);
        textCodeDocument = new JTextField(15);
        buttonFile = new JButton("Прикрепить");

        panelLabels.add(new JLabel("Название документа"));
        panelLabels.add(textNameDocument);
        panelLabels.add(new JLabel("Шифр документа"));
        panelLabels.add(textCodeDocument);
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
