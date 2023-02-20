package get.a.big.head.newNRG.users.frames;

import lombok.Data;

import javax.swing.*;
import java.awt.*;

@Data
public class UserManagerFrame extends JFrame {
    private JFrame frame;
    private JLabel labelFinder;
    private JTextField textFieldFinder;
    private JButton buttonAllUsers;
    private JPasswordField passwordField;
    private JButton buttonOk;
    private JButton buttonCancel;

    public UserManagerFrame() {
        frame = new JFrame("Управление аккаунтами пользователей");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(320, 240);

        labelFinder = new JLabel("Найти пользователя");
        textFieldFinder = new JTextField(15);
        JPanel panelFindUser = new JPanel();
        panelFindUser.add(labelFinder);
        panelFindUser.add(textFieldFinder);
        frame.getContentPane().add(BorderLayout.NORTH, panelFindUser);

        buttonAllUsers = new JButton("Показать всех пользователей");
        JPanel panelAllUsers = new JPanel();
        panelAllUsers.add(buttonAllUsers);
        frame.getContentPane().add(BorderLayout.CENTER, panelAllUsers);

        buttonOk = new JButton("OK");
        buttonCancel = new JButton("Отмена");
        JPanel panelButtons = new JPanel();
        panelButtons.add(buttonOk);
        panelButtons.add(buttonCancel);
        frame.getContentPane().add(BorderLayout.SOUTH, panelButtons);
        frame.setVisible(true);
    }
}
