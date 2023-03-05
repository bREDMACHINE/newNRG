package get.a.big.head.newNRG.users.frames;

import get.a.big.head.newNRG.users.models.User;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.List;

@Getter
@Setter
public class UserListFrame extends JFrame {
    private JFrame frame;
    private JButton buttonClose;
    private JList<String> list;

    public UserListFrame(List<User> users) {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        JPanel panelUsers = new JPanel();
        String[] usersString = new String[users.size()];
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            usersString[i] = user.getEmail();
        }
        list = new JList<>(usersString);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(0);
        panelUsers.add(list);
        frame.getContentPane().add(BorderLayout.CENTER, panelUsers);

        buttonClose = new JButton("Закрыть");
        JPanel panelButtons = new JPanel();
        panelButtons.add(buttonClose);
        frame.getContentPane().add(BorderLayout.SOUTH, panelButtons);
        frame.pack();
        frame.setVisible(true);
    }
}
