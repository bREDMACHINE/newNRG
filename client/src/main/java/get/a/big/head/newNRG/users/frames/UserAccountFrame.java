package get.a.big.head.newNRG.users.frames;

import lombok.Data;

import javax.swing.*;
import java.awt.*;

@Data
public class UserAccountFrame extends JFrame {

    private JFrame frame;
    private JButton buttonOk;
    private JButton buttonCancel;

    public UserAccountFrame() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        frame.setSize(300, 300);

        buttonOk = new JButton("OK");
        buttonCancel = new JButton("Отмена");
        JPanel jPanel = new JPanel();
        jPanel.add(buttonOk);
        jPanel.add(buttonCancel);
        frame.getContentPane().add(BorderLayout.SOUTH, jPanel);
        frame.setVisible(true);
    }
}
