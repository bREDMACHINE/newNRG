package get.a.big.head.newNRG.equipment;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class AddEquipmentFrame extends JFrame {

    private JFrame frame;
    private JLabel labelOperationalName;
    private JTextField textOperationalName;
    private JLabel labelRatedCurrent;
    private JTextField textRatedCurrent;
    private JLabel labelRatedVoltage;
    private JTextField textRatedVoltage;
    private JButton buttonOk;
    private JButton buttonCancel;

    public AddEquipmentFrame() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel panelLabels = new JPanel(new GridLayout(3, 2, 5, 0));
        labelOperationalName = new JLabel("Оперативное наименование");
        labelRatedCurrent = new JLabel("Номинальный ток");
        labelRatedVoltage = new JLabel("Номинальное напряжение");
        textOperationalName = new JTextField(15);
        textRatedCurrent = new JTextField(15);
        textRatedVoltage = new JTextField(15);
        panelLabels.add(labelOperationalName);
        panelLabels.add(textOperationalName);
        panelLabels.add(labelRatedCurrent);
        panelLabels.add(textRatedCurrent);
        panelLabels.add(labelRatedVoltage);
        panelLabels.add(textRatedVoltage);
        frame.getContentPane().add(BorderLayout.CENTER, panelLabels);

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
