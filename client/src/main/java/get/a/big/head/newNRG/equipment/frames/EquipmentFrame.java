package get.a.big.head.newNRG.equipment.frames;

import get.a.big.head.newNRG.equipment.Equipment;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class EquipmentFrame extends JFrame {

    private JFrame frame;
    private JButton buttonShowDocumentation;
    private JButton buttonAddDocumentation;
    private JButton buttonShowEvents;
    private JButton buttonAddEvent;
    private JButton buttonOk;
    private JButton buttonCancel;

    public EquipmentFrame(Equipment equipment) {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        buttonShowDocumentation = new JButton("Показать проект");
        buttonAddDocumentation = new JButton("Добавить проект");
        buttonShowEvents = new JButton("Показать события");
        buttonAddEvent = new JButton("Добавить событие");
        JPanel panelSpecifications = new JPanel(new GridLayout(7, 2, 5, 0));
        panelSpecifications.add(new JLabel("Оперативное наименование"));
        panelSpecifications.add(new JLabel(equipment.getOperationalName()));
        panelSpecifications.add(new JLabel("Год ввода в эксплуатацию"));
        panelSpecifications.add(new JLabel(equipment.getInstallationYear()));
        panelSpecifications.add(new JLabel("Тип"));
        panelSpecifications.add(new JLabel(equipment.getType().getTypeName()));
        panelSpecifications.add(buttonShowDocumentation);
        panelSpecifications.add(buttonAddDocumentation);
        panelSpecifications.add(buttonShowEvents);
        panelSpecifications.add(buttonAddEvent);
        frame.getContentPane().add(BorderLayout.NORTH, panelSpecifications);


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
