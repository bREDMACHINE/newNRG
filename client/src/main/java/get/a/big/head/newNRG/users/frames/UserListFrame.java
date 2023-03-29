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
    private JButton buttonPrevious;
    private JLabel labelPage;
    private JButton buttonNext;
    private JPanel panelButtons;

    public UserListFrame(List<User> users, int page, int pages) {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel panelList = new JPanel(new GridLayout(0, 3, 12, 5));
        panelList.add(new JLabel("Логин"));
        panelList.add(new JLabel("Уровень доступа"));
        panelList.add(new JLabel("Статус"));
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            panelList.add(new JLabel(user.getEmail()));
            panelList.add(new JLabel(user.getRole()));
            panelList.add(new JLabel(user.getStatus()));
        }

        frame.getContentPane().add(BorderLayout.NORTH, panelList);

        labelPage = new JLabel("Страница " + page + " из " + pages);
        buttonPrevious = new JButton("< Предыдущая");
        buttonNext = new JButton("Следующая >");
        panelButtons = new JPanel();
        panelButtons.add(labelPage);
        frame.getContentPane().add(BorderLayout.SOUTH, panelButtons);
        frame.pack();
        frame.setVisible(true);
    }
}
