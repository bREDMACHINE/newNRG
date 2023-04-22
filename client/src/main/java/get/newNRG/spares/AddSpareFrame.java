package get.newNRG.spares;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class AddSpareFrame extends JFrame {

    private JFrame frame;
    private JTextField textSpareName;
    private JTextField textSpareDescription;
    private JTextField textSpareCode;
    private JButton buttonOk;
    private JButton buttonCancel;

    public AddSpareFrame() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel panelLabels = new JPanel(new GridLayout(4, 2, 5, 0));
        textSpareName = new JTextField(15);
        textSpareDescription = new JTextField(15);
        textSpareCode = new JTextField(15);
        panelLabels.add(new JLabel("Краткое наименование"));
        panelLabels.add(textSpareName);
        panelLabels.add(new JLabel("Полное описание"));
        panelLabels.add(textSpareDescription);
        panelLabels.add(new JLabel("Номер материала"));
        panelLabels.add(textSpareCode);
        frame.getContentPane().add(BorderLayout.NORTH, panelLabels);

        buttonOk = new JButton("OK");
        buttonCancel = new JButton("Отмена");
        JPanel panelButtons = new JPanel();
        panelButtons.add(buttonOk);
        panelButtons.add(buttonCancel);
        frame.getContentPane().add(BorderLayout.SOUTH, panelButtons);
        frame.pack();
        frame.setVisible(true);
    }
}
