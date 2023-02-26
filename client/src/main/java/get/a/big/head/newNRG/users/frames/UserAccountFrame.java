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
    protected JButton buttonAcceptRole;
    protected JButton buttonRejectRole;
    protected JButton buttonRequestRole;
    protected JButton buttonDeleteUser;
    protected JButton buttonClose;
    protected JPanel panelButtons;

    public UserAccountFrame(User user) {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel panelLabels = new JPanel(new GridLayout(4, 2, 5,5));
        JLabel labelEmail = new JLabel(user.getEmail());
        JLabel labelRole = new JLabel(user.getRole());
        JLabel labelStatus = new JLabel(user.getStatus());
        panelLabels.add(new JLabel("Email: "));
        panelLabels.add(labelEmail);
        panelLabels.add(new JLabel("Role: "));
        panelLabels.add(labelRole);
        panelLabels.add(new JLabel("Status: "));
        panelLabels.add(labelStatus);
        frame.getContentPane().add(BorderLayout.NORTH, panelLabels);

        buttonDeleteUser = new JButton("Удалить пользователя");
        buttonAcceptRole = new JButton("Согласовано");
        buttonRejectRole = new JButton("Отказано");
        buttonRequestRole = new JButton("Запросить повышение роли");
        buttonClose = new JButton("Закрыть");
        panelButtons = new JPanel(new GridLayout(5, 1, 5,5));
        panelButtons.add(buttonRequestRole);
        panelButtons.add(buttonClose);
        frame.getContentPane().add(BorderLayout.SOUTH, panelButtons);

        frame.pack();
        frame.setVisible(true);
    }
}
