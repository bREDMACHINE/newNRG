package get.a.big.head.newNRG.users.frames;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
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
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
