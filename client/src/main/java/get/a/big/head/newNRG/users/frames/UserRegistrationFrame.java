package get.a.big.head.newNRG.users.frames;

import lombok.Data;

import javax.swing.*;
import java.awt.*;

@Data
public class UserRegistrationFrame extends JFrame {

    private JFrame frame;
    private JLabel labelLogin;
    private JTextField textFieldLogin;
    private JPanel panelLogin;
    private JLabel labelPassword;
    private JPasswordField passwordField;
    private JPanel panelPassword;
    private JButton button;

    public UserRegistrationFrame() {
        frame = new JFrame("Регистрация");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(320, 240);

        labelLogin = new JLabel("Введите почту");
        textFieldLogin = new JTextField(15);
        panelLogin = new JPanel();
        panelLogin.add(labelLogin);
        panelLogin.add(textFieldLogin);
        frame.getContentPane().add(BorderLayout.NORTH, panelLogin);

        labelPassword = new JLabel("Введите пароль");
        passwordField = new JPasswordField(10);
        panelPassword = new JPanel();
        panelPassword.add(labelPassword);
        panelPassword.add(passwordField);
        frame.getContentPane().add(BorderLayout.CENTER, panelPassword);

        button = new JButton("OK");
        frame.getContentPane().add(BorderLayout.SOUTH, button);
        frame.setVisible(true);
    }
}
