package get.a.big.head.newNRG.users.frames;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class UserManagerFrame extends JFrame {
    private JFrame frame;
    private JButton buttonFind;
    private JTextField textFieldFinder;
    private JComboBox<String> roleMenu;
    private JComboBox<String> statusMenu;
    private JButton buttonAllUsers;
    private JPanel panelButtons;
    private JButton buttonRequest;
    private JButton buttonCancel;

    public UserManagerFrame() {
        frame = new JFrame("Управление аккаунтами пользователей");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        buttonFind = new JButton("Найти пользователя");
        textFieldFinder = new JTextField(15);
        JPanel panelFindUser = new JPanel();
        panelFindUser.add(buttonFind);
        panelFindUser.add(textFieldFinder);
        frame.getContentPane().add(BorderLayout.NORTH, panelFindUser);

        buttonAllUsers = new JButton("Найти пользователей");
        String[] roles = new String[] {"Roles", "User", "Moderator", "Admin"};
        roleMenu = new JComboBox<>(roles);
        String[] status = new String[] {"Statuses", "Accepted", "Requested"};
        statusMenu = new JComboBox<>(status);
        JPanel panelAllUsers = new JPanel();
        panelAllUsers.add(buttonAllUsers);
        panelAllUsers.add(roleMenu);
        panelAllUsers.add(statusMenu);
        frame.getContentPane().add(BorderLayout.CENTER, panelAllUsers);

        buttonCancel = new JButton("Отмена");
        buttonRequest = new JButton("Запрос повышения роли");
        panelButtons = new JPanel();
        panelButtons.add(buttonCancel);
        frame.getContentPane().add(BorderLayout.SOUTH, panelButtons);
        frame.pack();
        frame.setVisible(true);
    }
}
