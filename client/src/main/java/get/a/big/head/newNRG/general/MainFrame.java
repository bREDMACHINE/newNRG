package get.a.big.head.newNRG.general;

import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame {

    private JFrame frame;
    private JMenuBar menuBar;
    private JMenu menuMain;
    private JMenu menuOptions;
    private JMenu menuUserLogin;
    private JMenuItem menuItemAuthorization;
    private JMenuItem menuItemRegistration;
    private JPanel panel;
    private JLabel label;
    private JTextField textField;
    private JButton buttonFind;

    public MainFrame() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame("Основное окно");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        menuBar = new JMenuBar();
        menuMain = new JMenu("Основное");
        menuOptions = new JMenu("Настройки");
        menuUserLogin = new JMenu("Войти");
        menuBar.add(menuMain);
        menuBar.add(menuOptions);
        menuBar.add(menuUserLogin);
        frame.getContentPane().add(BorderLayout.BEFORE_FIRST_LINE, menuBar);

        menuItemAuthorization = new JMenuItem("Авторизация");
        menuItemRegistration = new JMenuItem("Регистрация");
        menuUserLogin.add(menuItemAuthorization);
        menuUserLogin.add(menuItemRegistration);

        panel = new JPanel();
        textField = new JTextField(10);
        buttonFind = new JButton("Найти");
        panel.add(textField);
        panel.add(buttonFind);
        frame.getContentPane().add(BorderLayout.CENTER, panel);

        label = new JLabel("Введите оперативное наименование");
        label.setFont(new Font("Verdana", Font.PLAIN, 11));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        frame.getContentPane().add(label);
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }

    public JMenu getMenuMain() {
        return menuMain;
    }

    public JMenu getMenuOptions() {
        return menuOptions;
    }

    public JMenu getMenuUserLogin() {
        return menuUserLogin;
    }

    public JMenuItem getMenuItemAuthorization() {
        return menuItemAuthorization;
    }

    public JMenuItem getMenuItemRegistration() {
        return menuItemRegistration;
    }

    public JPanel getPanel() {
        return panel;
    }

    public JLabel getLabel() {
        return label;
    }

    public JTextField getTextField() {
        return textField;
    }

    public JButton getButtonFind() {
        return buttonFind;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public void setMenuBar(JMenuBar menuBar) {
        this.menuBar = menuBar;
    }

    public void setMenuMain(JMenu menuMain) {
        this.menuMain = menuMain;
    }

    public void setMenuOptions(JMenu menuOptions) {
        this.menuOptions = menuOptions;
    }

    public void setMenuUserLogin(JMenu menuUserLogin) {
        this.menuUserLogin = menuUserLogin;
    }

    public void setMenuItemAuthorization(JMenuItem menuItemAuthorization) {
        this.menuItemAuthorization = menuItemAuthorization;
    }

    public void setMenuItemRegistration(JMenuItem menuItemRegistration) {
        this.menuItemRegistration = menuItemRegistration;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    public void setTextField(JTextField textField) {
        this.textField = textField;
    }

    public void setButtonFind(JButton buttonFind) {
        this.buttonFind = buttonFind;
    }
}
