package get.newNRG.factories;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

import static javax.swing.GroupLayout.Alignment.*;
import static javax.swing.GroupLayout.Alignment.BASELINE;

@Getter
@Setter
public class AddFactoryFrame extends JFrame {

    private JFrame frame;
    private JTextField textFactoryName;
    private JButton buttonOk;
    private JButton buttonCancel;

    public AddFactoryFrame() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        frame.setFont(new Font("Arial", Font.PLAIN, 16));
        frame.getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        frame.setLocationRelativeTo(null);

        textFactoryName = new JTextField(15);
        JLabel labelName = new JLabel("Наименование завода");
        buttonOk = new JButton("Ок");
        buttonCancel = new JButton("Закрыть");

        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(LEADING)
                        .addComponent(labelName))
                .addGroup(layout.createParallelGroup(TRAILING, false)
                        .addComponent(textFactoryName)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(buttonOk)
                                .addComponent(buttonCancel)))
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addGap(10)
                        .addComponent(labelName)
                        .addComponent(textFactoryName))
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addComponent(buttonOk)
                        .addComponent(buttonCancel))
        );

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
