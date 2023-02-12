package get.a.big.head.newNRG.general;

import lombok.Data;

import javax.swing.*;
import java.awt.*;

@Data
public class ModeratorMainFrame extends UserMainFrame {

    JButton buttonAddEquipment;
    JButton buttonAddEvent;

    public ModeratorMainFrame() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 5, 0));
        buttonAddEquipment = new JButton("Добавить оборудование");
        buttonAddEvent = new JButton("Добавить событие");
        panel.add(buttonAddEquipment);
        panel.add(buttonAddEvent);
        JPanel flow = new JPanel(new FlowLayout( FlowLayout.RIGHT ));
        flow.add(panel);
        getFrame().getContentPane().add(BorderLayout.SOUTH, panel);
    }
}
