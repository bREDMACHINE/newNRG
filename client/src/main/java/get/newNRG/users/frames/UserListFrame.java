package get.newNRG.users.frames;

import get.newNRG.users.models.User;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;
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
        Field[] fields = list.get(0).getClass().getDeclaredFields();
        for (User user : list) {
            for (Field field : fields) {
                try {
                    field.setAccessible(true);
                    if (field.getType().equals(String.class)) {
                        panel.add(new JLabel(field.get(user).toString()));
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
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
