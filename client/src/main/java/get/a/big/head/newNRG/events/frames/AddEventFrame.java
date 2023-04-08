package get.a.big.head.newNRG.events.frames;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.awt.*;
import java.text.SimpleDateFormat;

import static javax.swing.GroupLayout.Alignment.*;
import static javax.swing.GroupLayout.Alignment.BASELINE;

@Getter
@Setter
public class AddEventFrame extends JFrame {

    private JFrame frame;
    private JFormattedTextField textEventDate;
    private JTextField textEventName;
    private JTextArea textDescription;
    private JButton buttonFile;
    private  JFileChooser fileChooser;
    private JButton buttonOk;
    private JButton buttonCancel;

    public AddEventFrame() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        frame.setFont(new Font("Arial", Font.PLAIN, 16));
        frame.getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        DateFormatter dateFormatter = new DateFormatter(new SimpleDateFormat("yyyy.MM.dd"));
        dateFormatter.setAllowsInvalid(true);
        dateFormatter.setOverwriteMode(true);
        JLabel labelDate = new JLabel("Дата события");
        textEventDate = new JFormattedTextField(dateFormatter);
        textEventDate.setColumns(10);
        JLabel labelName = new JLabel("Наименование");
        textEventName = new JTextField(15);
        JLabel labelDescription = new JLabel("Описание");
        textDescription = new JTextArea(30,5);
        JLabel labelFile = new JLabel("Документ");
        buttonFile = new JButton("Прикрепить");
        fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Выберите файл");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        buttonOk = new JButton("OK");
        buttonCancel = new JButton("Отмена");

        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(LEADING)
                        .addComponent(labelDate)
                        .addComponent(labelName)
                        .addComponent(labelDescription)
                        .addComponent(labelFile))
                .addGroup(layout.createParallelGroup(TRAILING, false)
                        .addComponent(textEventDate)
                        .addComponent(textEventName)
                        .addComponent(textDescription)
                        .addComponent(buttonFile)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(buttonOk)
                                .addComponent(buttonCancel)))
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addComponent(labelDate)
                        .addComponent(textEventDate))
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addGap(10)
                        .addComponent(labelName)
                        .addComponent(textEventName))
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addGap(10)
                        .addComponent(labelDescription)
                        .addComponent(textDescription))
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addGap(10)
                        .addComponent(labelFile)
                        .addComponent(buttonFile))
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addComponent(buttonOk)
                        .addComponent(buttonCancel))
        );

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
