package get.a.big.head.newNRG.users.frames;

import lombok.Data;

import javax.swing.*;
import java.awt.*;

@Data
public class UserAccountFrame extends JFrame {

    private JFrame frame;
    private JLabel labelEmail;
    private JLabel labelRole;
    private JButton buttonOk;
    private JButton buttonCancel;

    public UserAccountFrame(String email, String role) {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        frame.setSize(300, 300);

        JPanel panelLabels = new JPanel();
        labelEmail = new JLabel(email);
        labelRole = new JLabel(role);
        panelLabels.add(labelEmail);
        panelLabels.add(labelRole);
        frame.getContentPane().add(BorderLayout.CENTER, panelLabels);

        buttonOk = new JButton("OK");
        buttonCancel = new JButton("Отмена");
        JPanel panelButtons = new JPanel();
        panelButtons.add(buttonOk);
        panelButtons.add(buttonCancel);
        frame.getContentPane().add(BorderLayout.SOUTH, panelButtons);
        frame.setVisible(true);
    }
}
