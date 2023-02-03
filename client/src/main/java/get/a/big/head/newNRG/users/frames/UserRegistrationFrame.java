package get.a.big.head.newNRG.users.frames;

import javax.swing.*;
import java.awt.*;

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

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JLabel getLabelLogin() {
        return labelLogin;
    }

    public void setLabelLogin(JLabel labelLogin) {
        this.labelLogin = labelLogin;
    }

    public JTextField getTextFieldLogin() {
        return textFieldLogin;
    }

    public void setTextFieldLogin(JTextField textFieldLogin) {
        this.textFieldLogin = textFieldLogin;
    }

    public JPanel getPanelLogin() {
        return panelLogin;
    }

    public void setPanelLogin(JPanel panelLogin) {
        this.panelLogin = panelLogin;
    }

    public JLabel getLabelPassword() {
        return labelPassword;
    }

    public void setLabelPassword(JLabel labelPassword) {
        this.labelPassword = labelPassword;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public void setPasswordField(JPasswordField passwordField) {
        this.passwordField = passwordField;
    }

    public JPanel getPanelPassword() {
        return panelPassword;
    }

    public void setPanelPassword(JPanel panelPassword) {
        this.panelPassword = panelPassword;
    }

    public JButton getButton() {
        return button;
    }

    public void setButton(JButton button) {
        this.button = button;
    }
}
