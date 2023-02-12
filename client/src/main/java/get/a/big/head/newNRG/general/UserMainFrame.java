package get.a.big.head.newNRG.general;

import lombok.Data;

import java.awt.*;
import javax.swing.*;

@Data
public class UserMainFrame extends JFrame {

    private JFrame frame;
    private JMenu menuMain;
    private JMenu menuOptions;
    private JMenu menuUserLogin;
    private JMenuItem menuItemAccount;
    private JMenuItem menuItemLogout;
    private JLabel label;
    private JTextField textField;
    private JButton buttonFind;

    public UserMainFrame() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame("Основное окно");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        JMenuBar menuBar = new JMenuBar();
        menuMain = new JMenu("Основное");
        menuOptions = new JMenu("Настройки");
        menuUserLogin = new JMenu("Личный кабинет");
        menuBar.add(menuMain);
        menuBar.add(menuOptions);
        menuBar.add(menuUserLogin);
        frame.getContentPane().add(BorderLayout.BEFORE_FIRST_LINE, menuBar);

        menuItemAccount = new JMenuItem("Личный кабинет");
        menuItemLogout = new JMenuItem("Выйти");
        menuUserLogin.add(menuItemAccount);
        menuUserLogin.add(menuItemLogout);

        JPanel panel = new JPanel();
        textField = new JTextField(10);
        buttonFind = new JButton("Найти");
        panel.add(textField);
        panel.add(buttonFind);

        JPanel panel1 = new JPanel();
        label = new JLabel("Введите оперативное наименование");
        panel1.add(label);
        panel1.add(panel);

        frame.getContentPane().add(BorderLayout.CENTER, panel1);
        frame.setVisible(true);
    }
}
