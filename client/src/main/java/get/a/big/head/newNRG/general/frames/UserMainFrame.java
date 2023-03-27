package get.a.big.head.newNRG.general.frames;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import javax.swing.*;

import static javax.swing.GroupLayout.Alignment.BASELINE;

@Getter
@Setter
public class UserMainFrame extends JFrame {

    protected JFrame frame;
    private JMenu menu;
    private JMenuItem menuItemAccount;
    private JMenuItem menuItemLogout;
    private JTextField textField;
    private JButton buttonFind;
    private JButton buttonAddEquipment;
    private JButton userManager;
    private JPanel panelButtons;

    public UserMainFrame() {
        frame = new JFrame("Основное окно");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setFont(new Font("Arial", Font.PLAIN, 16));
        frame.getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        frame.setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();
        menu = new JMenu("Меню пользователя");
        menuBar.add(menu);
        menuItemAccount = new JMenuItem("Личный кабинет");
        menuItemLogout = new JMenuItem("Выйти");
        menu.add(menuItemAccount);
        menu.add(menuItemLogout);
        frame.getContentPane().add(BorderLayout.BEFORE_FIRST_LINE, menuBar);

        JPanel panel = new JPanel();
        textField = new JTextField("Введите оперативное наименование",20);
        buttonFind = new JButton("Найти");
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addComponent(textField)
                .addComponent(buttonFind)
                .addGap(40)
        );
        layout.setVerticalGroup(layout.createParallelGroup(BASELINE)
                .addComponent(textField)
                .addComponent(buttonFind)
        );
        frame.getContentPane().add(BorderLayout.CENTER, panel);

        panelButtons = new JPanel();
        buttonAddEquipment = new JButton("Добавить оборудование");
        userManager = new JButton("Управление пользователями");
        JPanel flow = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        flow.add(panelButtons);
        frame.getContentPane().add(BorderLayout.SOUTH, flow);


        frame.pack();
        frame.setVisible(true);
    }
}
