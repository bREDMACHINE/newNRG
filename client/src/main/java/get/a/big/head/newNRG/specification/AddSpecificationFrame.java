package get.a.big.head.newNRG.specification;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class AddSpecificationFrame extends JFrame {

    private JFrame frame;
    private JTextField textSpecificationName;
    private JTextField textSpecificationDescription;
    private JButton buttonOk;
    private JButton buttonCancel;

    public AddSpecificationFrame() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel panelLabels = new JPanel(new GridLayout(4, 2, 5, 0));
        textSpecificationName = new JTextField(15);
        textSpecificationDescription = new JTextField(15);
        panelLabels.add(new JLabel("Наименование характеристики"));
        panelLabels.add(textSpecificationName);
        panelLabels.add(new JLabel("Описание характеристики"));
        panelLabels.add(textSpecificationDescription);
        frame.getContentPane().add(BorderLayout.NORTH, panelLabels);

        buttonOk = new JButton("Oк");
        buttonCancel = new JButton("Отмена");
        JPanel panelButtons = new JPanel();
        panelButtons.add(buttonOk);
        panelButtons.add(buttonCancel);
        frame.getContentPane().add(BorderLayout.SOUTH, panelButtons);
        frame.pack();
        frame.setVisible(true);
    }
}
