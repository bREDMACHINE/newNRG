package get.newNRG.users.frames;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

import static javax.swing.GroupLayout.Alignment.*;

@Getter
@Setter
public class UserAuthorizationFrame extends JFrame {

    private JFrame frame;
    private JTextField textFieldLogin;
    private JPasswordField passwordField;
    private JButton buttonOk;
    private JButton buttonCancel;
    private JButton buttonRegistration;

    public UserAuthorizationFrame() {
        frame = new JFrame("Авторизация");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setFont(new Font("Arial", Font.PLAIN, 16));
        frame.getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        textFieldLogin = new JTextField(15);
        passwordField = new JPasswordField(15);
        buttonOk = new JButton("Oк");
        buttonCancel = new JButton("Отмена");
        buttonRegistration = new JButton("Регистрация");
        JLabel loginLabel = new JLabel("Введите почту");
        JLabel passwordLabel = new JLabel("Введите пароль");

        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(LEADING)
                        .addComponent(loginLabel)
                        .addComponent(passwordLabel)
                        .addComponent(buttonRegistration))
                .addGroup(layout.createParallelGroup(TRAILING, false)
                        .addComponent(textFieldLogin)
                        .addComponent(passwordField)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(buttonOk)
                                .addComponent(buttonCancel)))
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addComponent(loginLabel)
                        .addComponent(textFieldLogin))
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addGap(30)
                        .addComponent(passwordLabel)
                        .addComponent(passwordField))
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addGap(40)
                        .addComponent(buttonRegistration))
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addComponent(buttonOk)
                        .addComponent(buttonCancel))
        );

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
