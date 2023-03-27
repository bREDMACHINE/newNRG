package get.a.big.head.newNRG.users.frames;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

import static javax.swing.GroupLayout.Alignment.*;
import static javax.swing.GroupLayout.Alignment.BASELINE;

@Getter
@Setter
public class UserRegistrationFrame extends JFrame {

    private JFrame frame;
    private JTextField textFieldLogin;
    private JPasswordField passwordField;
    private JButton buttonOk;
    private JButton buttonCancel;

    public UserRegistrationFrame() {
        frame = new JFrame("Регистрация");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setFont(new Font("Arial", Font.PLAIN, 16));
        frame.getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        JLabel loginLabel = new JLabel("Введите почту");
        textFieldLogin = new JTextField(15);
        JLabel passwordLabel = new JLabel("Введите пароль");
        passwordField = new JPasswordField(10);
        buttonOk = new JButton("OK");
        buttonCancel = new JButton("Отмена");

        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(LEADING)
                        .addComponent(loginLabel)
                        .addComponent(passwordLabel))
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
                        .addComponent(buttonOk)
                        .addComponent(buttonCancel))
        );

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
