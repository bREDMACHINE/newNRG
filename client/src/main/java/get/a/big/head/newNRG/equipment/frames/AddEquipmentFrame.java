package get.a.big.head.newNRG.equipment.frames;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.List;

@Getter
@Setter
public class AddEquipmentFrame extends JFrame {

    private JFrame frame;
    private JTextField textOperationalName;
    private JTextField textInstallationYear;
    private JComboBox<String> typeMenu;
    private JButton buttonAddType;
    private JButton buttonOk;
    private JButton buttonCancel;

    public AddEquipmentFrame(List<String> types) {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        types.add(0, "Выберите тип");
        JPanel panelLabels = new JPanel(new GridLayout(7, 2, 5, 0));
        textOperationalName = new JTextField(15);
        textInstallationYear = new JTextField(15);
        typeMenu = new JComboBox<>(types.toArray(new String[types.size()]));
        buttonAddType = new JButton("Добавить тип");
        panelLabels.add(new JLabel("Оперативное наименование"));
        panelLabels.add(textOperationalName);
        panelLabels.add(new JLabel("Год ввода в эксплуатацию"));
        panelLabels.add(textInstallationYear);
        panelLabels.add(new JLabel("Тип"));
        panelLabels.add(typeMenu);
        panelLabels.add(new JLabel());
        panelLabels.add(new JLabel());
        panelLabels.add(new JLabel());
        panelLabels.add(buttonAddType);
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
