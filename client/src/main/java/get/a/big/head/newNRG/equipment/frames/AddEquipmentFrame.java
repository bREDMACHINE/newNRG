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
    private JButton buttonAddDocumentation;
    private JButton buttonOk;
    private JButton buttonCancel;

    public AddEquipmentFrame(List<String> types) {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        types.add(0, "Выберите тип");
        JPanel panelLabels = new JPanel(new GridLayout(5, 2, 5, 0));
        textOperationalName = new JTextField(15);
        textInstallationYear = new JTextField(15);
        typeMenu = new JComboBox<>(types.toArray(new String[types.size()]));
        buttonAddDocumentation = new JButton("Добавить проект");
        panelLabels.add(new JLabel("Оперативное наименование"));
        panelLabels.add(textOperationalName);
        panelLabels.add(new JLabel("Год ввода в эксплуатацию"));
        panelLabels.add(textInstallationYear);
        panelLabels.add(new JLabel("Тип"));
        panelLabels.add(typeMenu);
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
