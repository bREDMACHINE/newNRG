package get.a.big.head.newNRG.equipment;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class EquipmentFrame extends JFrame {

    private JFrame frame;
    private JLabel labelOperationalName;
    private JLabel labelRatedCurrent;
    private JLabel labelRatedVoltage;
    private JButton buttonAddEvent;
    private JButton buttonOk;
    private JButton buttonCancel;

    public EquipmentFrame(Equipment equipment) {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        frame.setSize(300, 300);

        JPanel panelLabels = new JPanel();
        labelOperationalName = new JLabel(equipment.getOperationalName());
        labelRatedCurrent = new JLabel(equipment.getRatedCurrent());
        labelRatedVoltage = new JLabel(equipment.getRatedVoltage());

        panelLabels.add(labelOperationalName);
        panelLabels.add(labelRatedCurrent);
        panelLabels.add(labelRatedVoltage);
        frame.getContentPane().add(BorderLayout.CENTER, panelLabels);

        buttonAddEvent = new JButton("Добавить событие");
        buttonOk = new JButton("OK");
        buttonCancel = new JButton("Отмена");
        JPanel panelButtons = new JPanel();
        panelButtons.add(buttonAddEvent);
        panelButtons.add(buttonOk);
        panelButtons.add(buttonCancel);
        frame.getContentPane().add(BorderLayout.SOUTH, panelButtons);
        frame.setVisible(true);
    }
}
