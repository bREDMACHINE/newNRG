package get.newNRG.spares;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

import static javax.swing.GroupLayout.Alignment.*;
import static javax.swing.GroupLayout.Alignment.BASELINE;

@Getter
@Setter
public class AddSpareFrame extends JFrame {

    private JFrame frame;
    private JTextField textSpareName;
    private JTextField textSpareDescription;
    private JTextField textSpareCode;
    private JButton buttonOk;
    private JButton buttonCancel;

    public AddSpareFrame() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        frame.setFont(new Font("Arial", Font.PLAIN, 16));
        frame.getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        frame.setLocationRelativeTo(null);

        textSpareName = new JTextField(15);
        textSpareDescription = new JTextField(15);
        textSpareCode = new JTextField(15);
        JLabel labelName = new JLabel("Краткое наименование");
        JLabel labelDescription = new JLabel("Полное описание");
        JLabel labelCode = new JLabel("Номер материала");
        buttonOk = new JButton("Ок");
        buttonCancel = new JButton("Закрыть");

        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(LEADING)
                        .addComponent(labelName)
                        .addComponent(labelDescription)
                        .addComponent(labelCode))
                .addGroup(layout.createParallelGroup(TRAILING, false)
                        .addComponent(textSpareName)
                        .addComponent(textSpareDescription)
                        .addComponent(textSpareCode)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(buttonOk)
                                .addComponent(buttonCancel)))
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addGap(10)
                        .addComponent(labelName)
                        .addComponent(textSpareName))
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addGap(10)
                        .addComponent(labelDescription)
                        .addComponent(textSpareDescription))
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addGap(10)
                        .addComponent(labelCode)
                        .addComponent(textSpareCode))
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addComponent(buttonOk)
                        .addComponent(buttonCancel))
        );

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
