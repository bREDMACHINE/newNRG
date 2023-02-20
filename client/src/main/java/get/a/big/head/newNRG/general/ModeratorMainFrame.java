package get.a.big.head.newNRG.general;

import lombok.Data;

import javax.swing.*;
import java.awt.*;

@Data
public class ModeratorMainFrame extends UserMainFrame {

    private JButton buttonAddEquipment;
    protected JPanel panelButtons;

    public ModeratorMainFrame() {
        panelButtons = new JPanel();
        buttonAddEquipment = new JButton("Добавить оборудование");
        panelButtons.add(buttonAddEquipment);
        JPanel flow = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        flow.add(panelButtons);
        frame.getContentPane().add(BorderLayout.SOUTH, flow);
    }
}
