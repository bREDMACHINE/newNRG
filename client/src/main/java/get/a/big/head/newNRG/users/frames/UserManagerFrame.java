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
    private JCheckBox checkBoxUser;
    private JCheckBox checkBoxModerator;
    private JCheckBox checkBoxRequested;
    private JButton buttonAllUsers;
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
        checkBoxUser = new JCheckBox("User");
        checkBoxModerator = new JCheckBox("Moderator");
        checkBoxRequested = new JCheckBox("Requested");
        JPanel panelAllUsers = new JPanel();
        JPanel panelCheckBox = new JPanel();
        panelCheckBox.add(checkBoxUser);
        panelCheckBox.add(checkBoxModerator);
        panelCheckBox.add(checkBoxRequested);
        panelAllUsers.add(buttonAllUsers);
        panelAllUsers.add(panelCheckBox);
        frame.getContentPane().add(BorderLayout.CENTER, panelAllUsers);

        buttonCancel = new JButton("Отмена");
        JPanel panelButtons = new JPanel();
        panelButtons.add(buttonCancel);
        frame.getContentPane().add(BorderLayout.SOUTH, panelButtons);
        frame.pack();
        frame.setVisible(true);
    }
}
