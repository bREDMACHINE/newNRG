package get.a.big.head.newNRG.users.frames;

import lombok.Data;

import javax.swing.*;
import java.awt.*;

@Data
public class UserAuthorizationFrame extends JFrame {

    private JFrame frame;
    private JLabel labelLogin;
    private JTextField textFieldLogin;
    private JLabel labelPassword;
    private JPasswordField passwordField;
    private JButton buttonOk;
    private JButton buttonCancel;
    private JButton buttonRegistration;

    public UserAuthorizationFrame() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        frame.setSize(300, 300);

        labelLogin = new JLabel("Введите почту");
        textFieldLogin = new JTextField(15);
        JPanel panelLogin = new JPanel();
        panelLogin.add(labelLogin);
        panelLogin.add(textFieldLogin);
        frame.getContentPane().add(BorderLayout.NORTH, panelLogin);

        labelPassword = new JLabel("Введите пароль");
        passwordField = new JPasswordField(10);
        JPanel panelPassword = new JPanel();
        panelPassword.add(labelPassword);
        panelPassword.add(passwordField);
        frame.getContentPane().add(BorderLayout.CENTER, panelPassword);

        buttonOk = new JButton("OK");
        buttonCancel = new JButton("Отмена");
        buttonRegistration = new JButton("Регистрация");
        JPanel panelButtons = new JPanel();
        panelButtons.add(buttonRegistration);
        panelButtons.add(buttonOk);
        panelButtons.add(buttonCancel);
        frame.getContentPane().add(BorderLayout.SOUTH, panelButtons);
        frame.setVisible(true);
    }
}
