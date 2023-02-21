package get.a.big.head.newNRG.general.frames;

import lombok.Data;

import java.awt.*;
import javax.swing.*;

@Data
public class UserMainFrame extends JFrame {

    protected JFrame frame;
    private JMenu menuMain;
    private JMenu menuOptions;
    private JMenu menuUserLogin;
    private JMenuItem menuItemAccount;
    private JMenuItem menuItemLogout;
    private JLabel label;
    private JTextField textField;
    private JButton buttonFind;
    protected JButton buttonAddEquipment;
    protected JButton userManager;
    protected JPanel panelButtons;

    public UserMainFrame() {
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

        panelButtons = new JPanel();
        buttonAddEquipment = new JButton("Добавить оборудование");
        userManager = new JButton("Управление пользователями");
        JPanel flow = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        flow.add(panelButtons);
        frame.getContentPane().add(BorderLayout.SOUTH, flow);

        frame.getContentPane().add(BorderLayout.CENTER, panel1);
        frame.setVisible(true);
    }
}
