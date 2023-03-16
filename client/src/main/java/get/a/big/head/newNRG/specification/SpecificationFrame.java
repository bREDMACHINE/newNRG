package get.a.big.head.newNRG.specification;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class SpecificationFrame extends JFrame {

    private JFrame frame;
    private JButton buttonCancel;

    public SpecificationFrame(SpecificationDto specification) {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel panelSpecifications = new JPanel(new GridLayout(5, 2, 5, 0));
        panelSpecifications.add(new JLabel("Наименование характеристики"));
        panelSpecifications.add(new JLabel(specification.getSpecificationName()));
        panelSpecifications.add(new JLabel("Описание характеристики"));
        panelSpecifications.add(new JLabel(String.valueOf(specification.getSpecificationDescription())));
        panelSpecifications.add(new JLabel("Значение"));
        panelSpecifications.add(new JLabel(specification.getSpecificationValue()));
        frame.getContentPane().add(BorderLayout.NORTH, panelSpecifications);

        buttonCancel = new JButton("Закрыть");
        JPanel panelButtons = new JPanel();
        panelButtons.add(buttonCancel);
        frame.getContentPane().add(BorderLayout.SOUTH, panelButtons);

        frame.pack();
        frame.setVisible(true);
    }
}
