package get.a.big.head.newNRG.type;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.List;

@Getter
@Setter
public class AddTypeFrame extends JFrame {

    private JFrame frame;
    private JTextField textTypeName;
    private JComboBox<String> factoryMenu;
    private JComboBox<String> specificationMenu;
    private JTextField textSpecificationValue;
    private JButton buttonOk;
    private JButton buttonCancel;

    public AddTypeFrame(List<String> specifications, List<String> factories) {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        factories.add(0, "Выберите производителя");
        specifications.add(0, "Выберите характеристику");
        JPanel panelLabels = new JPanel(new GridLayout(5, 2, 5, 0));
        textTypeName = new JTextField(15);
        textSpecificationValue = new JTextField(5);
        factoryMenu = new JComboBox<>(factories.toArray(new String[factories.size()]));
        specificationMenu = new JComboBox<>(specifications.toArray(new String[specifications.size()]));
        panelLabels.add(new JLabel("Тип по паспорту"));
        panelLabels.add(textTypeName);
        panelLabels.add(new JLabel("Завод изготовитель"));
        panelLabels.add(factoryMenu);
        panelLabels.add(specificationMenu);
        panelLabels.add(textSpecificationValue);
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
