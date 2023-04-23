package get.newNRG.users.frames;

import get.newNRG.users.models.User;
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

    public UserListFrame(List<User> list, List<String> labels, int page, int pages) {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        frame.setFont(new Font("Arial", Font.PLAIN, 16));
        frame.getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(0, labels.size(), 12, 5));
        for (String label : labels) {
            panel.add(new JLabel(label));
        }
        for (User user : list) {
            panel.add(new JLabel(user.getEmail()));
            panel.add(new JLabel(user.getRole()));
            panel.add(new JLabel(user.getStatus()));
        }
        buttonPrevious = new JButton("< Предыдущая");
        buttonNext = new JButton("Следующая >");
        panel.add(new JLabel());
        panel.add(new JLabel());
        frame.getContentPane().add(BorderLayout.NORTH, panel);

        labelPage = new JLabel("Страница " + page + " из " + pages);
        panelButtons = new JPanel();
        panelButtons.add(labelPage);
        frame.getContentPane().add(BorderLayout.SOUTH, panelButtons);
        frame.pack();
        frame.setVisible(true);
    }
}
