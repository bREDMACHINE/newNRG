package get.a.big.head.newNRG.general;

import lombok.Data;

import javax.swing.*;
import java.awt.*;

@Data
public class ModeratorMainFrame extends UserMainFrame {

    JButton buttonAddEquipment;

    public ModeratorMainFrame() {
        JPanel panel = new JPanel();
        buttonAddEquipment = new JButton("Добавить оборудование");
        panel.add(buttonAddEquipment);
        JPanel flow = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        flow.add(panel);
        getFrame().getContentPane().add(BorderLayout.SOUTH, flow);
    }
}
