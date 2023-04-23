package get.newNRG.factories;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class AddFactoryFrame extends JFrame {

    private JFrame frame;
    private JTextField textFactoryName;
    private JButton buttonOk;
    private JButton buttonCancel;

    public AddFactoryFrame() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel panelLabels = new JPanel(new GridLayout(3, 2, 5, 0));
        textFactoryName = new JTextField(15);
        panelLabels.add(new JLabel("Наименование завода"));
        panelLabels.add(textFactoryName);
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
