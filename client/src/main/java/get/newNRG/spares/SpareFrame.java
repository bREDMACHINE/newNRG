package get.newNRG.spares;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class SpareFrame extends JFrame {

    private JFrame frame;
    private JButton buttonCancel;

    public SpareFrame(SpareDto spare) {

        frame = new JFrame();
        frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel panelSpecifications = new JPanel(new GridLayout(5, 2, 5, 0));
        panelSpecifications.add(new JLabel("Краткое наименование"));
        panelSpecifications.add(new JLabel(spare.getSpareName()));
        panelSpecifications.add(new JLabel("Полное описание"));
        panelSpecifications.add(new JLabel(spare.getSpareDescription()));
        panelSpecifications.add(new JLabel("Номер материала"));
        panelSpecifications.add(new JLabel(spare.getSpareCode()));
        frame.getContentPane().add(BorderLayout.NORTH, panelSpecifications);

        buttonCancel = new JButton("Закрыть");
        JPanel panelButtons = new JPanel();
        panelButtons.add(buttonCancel);
        frame.getContentPane().add(BorderLayout.SOUTH, panelButtons);

        frame.pack();
        frame.setVisible(true);
    }
}
