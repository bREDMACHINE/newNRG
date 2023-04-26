package get.newNRG.files;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

import static javax.swing.GroupLayout.Alignment.*;
import static javax.swing.GroupLayout.Alignment.BASELINE;

@Getter
@Setter
public class DataFileCreatorFrame extends JFrame {

    private JFrame frame;
    private JTextField textField;
    private JTextArea textArea;
    private  JFileChooser fileChooser;

    private JButton buttonSave;
    private JButton buttonOk;
    private JButton buttonCancel;

    public DataFileCreatorFrame() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        frame.setFont(new Font("Arial", Font.PLAIN, 16));
        frame.getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        frame.setLocationRelativeTo(null);

        buttonSave = new JButton("Сохранить на диске");
        fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Сохранение файла");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        textField = new JTextField();
        textArea = new JTextArea();
        JLabel labelField = new JLabel("Вид ремонта");
        JLabel labelArea = new JLabel("Дата ремонта");

        buttonOk = new JButton("OK");
        buttonCancel = new JButton("Отмена");
        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(LEADING)
                        .addComponent(labelField)
                        .addComponent(labelArea))
                .addGroup(layout.createParallelGroup(TRAILING, false)
                        .addComponent(textField)
                        .addComponent(textArea)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(buttonOk)
                                .addComponent(buttonCancel)))
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addComponent(labelField)
                        .addComponent(textField))
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addGap(10)
                        .addComponent(labelArea)
                        .addComponent(textArea))
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addComponent(buttonOk)
                        .addComponent(buttonCancel))
        );

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
