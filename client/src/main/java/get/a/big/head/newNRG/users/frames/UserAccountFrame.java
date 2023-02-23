package get.a.big.head.newNRG.users.frames;

import get.a.big.head.newNRG.users.dtos.User;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class UserAccountFrame extends JFrame {

    private JFrame frame;
    private JLabel labelEmail;
    private JLabel labelRole;
    protected JButton buttonDeleteUser;
    private JButton buttonClose;
    protected JPanel panelButtons;

    public UserAccountFrame(User user) {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        frame.setSize(300, 300);

        JPanel panelLabels = new JPanel();
        labelEmail = new JLabel(user.getEmail());
        labelRole = new JLabel(user.getRole());
        panelLabels.add(labelEmail);
        panelLabels.add(labelRole);
        frame.getContentPane().add(BorderLayout.CENTER, panelLabels);

        buttonDeleteUser = new JButton("Удалить пользователя");
        buttonClose = new JButton("Закрыть");
        panelButtons = new JPanel();
        panelButtons.add(buttonClose);
        frame.getContentPane().add(BorderLayout.SOUTH, panelButtons);
        frame.setVisible(true);
    }
}
