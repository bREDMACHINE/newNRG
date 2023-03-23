package get.a.big.head.newNRG.specificationvalue;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.List;

@Getter
@Setter
public class AddSpecificationValueFrame extends JFrame {

    private JFrame frame;
    private JTextField textSpecificationValue;
    private JComboBox<String> specificationMenu;
    private JButton buttonOk;
    private JButton buttonCancel;

    public AddSpecificationValueFrame(List<String> specifications) {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        specifications.add(0, "Выберите характеристику");
        JPanel panelLabels = new JPanel(new GridLayout(4, 2, 5, 0));
        textSpecificationValue = new JTextField(15);
        specificationMenu = new JComboBox<>(specifications.toArray(new String[specifications.size()]));
        panelLabels.add(new JLabel());
        panelLabels.add(specificationMenu);
        panelLabels.add(new JLabel("Укажите значение"));
        panelLabels.add(textSpecificationValue);
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
