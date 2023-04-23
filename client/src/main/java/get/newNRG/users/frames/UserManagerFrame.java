package get.newNRG.users.frames;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

import static javax.swing.GroupLayout.Alignment.*;
import static javax.swing.GroupLayout.Alignment.BASELINE;

@Getter
@Setter
public class UserManagerFrame extends JFrame {
    private JFrame frame;
    private JButton buttonFind;
    private JTextField textFieldFinder;
    private JComboBox<String> roleMenu;
    private JComboBox<String> statusMenu;
    private JButton buttonAllUsers;
    private JButton buttonRequest;
    private JButton buttonLogout;
    private JButton buttonCancel;

    public UserManagerFrame() {
        frame = new JFrame("Управление аккаунтами пользователей");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setFont(new Font("Arial", Font.PLAIN, 16));
        frame.getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        buttonFind = new JButton("Найти пользователя");
        textFieldFinder = new JTextField(15);

        buttonAllUsers = new JButton("Найти пользователей");
        String[] roles = new String[] {"Roles", "User", "Moderator", "Admin"};
        roleMenu = new JComboBox<>(roles);
        String[] status = new String[] {"Statuses", "Accepted", "Requested"};
        statusMenu = new JComboBox<>(status);

        buttonLogout = new JButton("Выйти из аккаунта");
        buttonCancel = new JButton("Отмена");
        buttonRequest = new JButton("Запрос повышения роли");
        buttonRequest.setVisible(false);

        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(LEADING)
                        .addGap(20)
                        .addComponent(buttonFind)
                        .addComponent(buttonAllUsers)
                        .addComponent(buttonRequest))
                .addGroup(layout.createParallelGroup(TRAILING, false)
                        .addGap(20)
                        .addComponent(textFieldFinder)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(roleMenu)
                                .addComponent(statusMenu))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(buttonLogout)
                                .addComponent(buttonCancel)))
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addGap(40)
                        .addComponent(buttonFind)
                        .addComponent(textFieldFinder))
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addGap(40)
                        .addComponent(buttonAllUsers)
                        .addComponent(roleMenu)
                        .addComponent(statusMenu))
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addGap(40)
                        .addComponent(buttonRequest)
                        .addComponent(buttonLogout)
                        .addComponent(buttonCancel))
        );

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
