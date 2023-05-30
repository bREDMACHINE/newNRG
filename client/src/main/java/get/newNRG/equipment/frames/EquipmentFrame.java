package get.newNRG.equipment.frames;

import get.newNRG.equipment.EquipmentDto;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class EquipmentFrame extends JFrame {

    private JFrame frame;
    private JButton buttonType;
    private JButton buttonProjects;
    private JButton buttonEvents;
    private JButton buttonOk;
    private JButton buttonCancel;

    public EquipmentFrame(EquipmentDto equipment) {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        buttonType = new JButton(equipment.getType().getTypeName());
        buttonProjects = new JButton("Показать проекты");
        buttonEvents = new JButton("Показать события");
        JPanel panelSpecifications = new JPanel(new GridLayout(7, 2, 5, 0));
        panelSpecifications.add(new JLabel("Оперативное наименование"));
        panelSpecifications.add(new JLabel(equipment.getOperationalName()));
        panelSpecifications.add(new JLabel("Год ввода в эксплуатацию"));
        panelSpecifications.add(new JLabel(String.valueOf(equipment.getInstallationYear())));
        panelSpecifications.add(new JLabel("Тип"));
        panelSpecifications.add(buttonType);
        panelSpecifications.add(buttonProjects);
        panelSpecifications.add(buttonEvents);
        frame.getContentPane().add(BorderLayout.NORTH, panelSpecifications);


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
