package get.a.big.head.newNRG.users.frames;

import get.a.big.head.newNRG.users.dtos.User;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.List;

@Getter
@Setter
public class UserListFrame extends JFrame {
    private JFrame frame;
    private JButton button;
    private JButton buttonClose;

    public UserListFrame(List<User> users) {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel panelUsers = new JPanel(new GridLayout(users.size(), 2, 5, 0));
        for (User user : users) {
            button = new JButton(user.getEmail());
            button.setActionCommand(user.getEmail());
            panelUsers.add(button);
            panelUsers.add(new JLabel(user.getRole()));
        }
        frame.getContentPane().add(BorderLayout.CENTER, panelUsers);

        buttonClose = new JButton("Закрыть");
        JPanel panelButtons = new JPanel();
        panelButtons.add(buttonClose);
        frame.getContentPane().add(BorderLayout.SOUTH, panelButtons);
        frame.pack();
        frame.setVisible(true);
    }
}
