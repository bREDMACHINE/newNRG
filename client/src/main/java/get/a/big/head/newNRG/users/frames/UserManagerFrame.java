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
    private JButton buttonAllUsers;
    private JButton buttonCancel;

    public UserManagerFrame() {
        frame = new JFrame("Управление аккаунтами пользователей");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(320, 240);

        buttonFind = new JButton("Найти пользователя");
        textFieldFinder = new JTextField(15);
        JPanel panelFindUser = new JPanel();
        panelFindUser.add(buttonFind);
        panelFindUser.add(textFieldFinder);
        frame.getContentPane().add(BorderLayout.NORTH, panelFindUser);

        buttonAllUsers = new JButton("Показать всех пользователей");
        JPanel panelAllUsers = new JPanel();
        panelAllUsers.add(buttonAllUsers);
        frame.getContentPane().add(BorderLayout.CENTER, panelAllUsers);

        buttonCancel = new JButton("Отмена");
        JPanel panelButtons = new JPanel();
        panelButtons.add(buttonCancel);
        frame.getContentPane().add(BorderLayout.SOUTH, panelButtons);
        frame.setVisible(true);
    }
}
