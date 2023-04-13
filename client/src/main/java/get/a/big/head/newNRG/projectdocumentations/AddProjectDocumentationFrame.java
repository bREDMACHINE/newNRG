package get.a.big.head.newNRG.projectdocumentations;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

import static javax.swing.GroupLayout.Alignment.*;
import static javax.swing.GroupLayout.Alignment.BASELINE;

@Getter
@Setter
public class AddProjectDocumentationFrame extends JFrame {
    private JFrame frame;
    private JTextField textNameProject;
    private JTextField textCodeProject;
    private JButton buttonFile;
    private  JFileChooser fileChooser;
    private JButton buttonOk;
    private JButton buttonCancel;

    public AddProjectDocumentationFrame() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        frame.setFont(new Font("Arial", Font.PLAIN, 16));
        frame.getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        frame.setLocationRelativeTo(null);


        JPanel panel = new JPanel();
        JLabel labelName = new JLabel("Название проекта");
        textNameProject = new JTextField(15);
        JLabel labelCode = new JLabel("Шифр проекта");
        textCodeProject = new JTextField(15);
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
                        .addComponent(labelName)
                        .addComponent(labelCode)
                        .addComponent(labelFile))
                .addGroup(layout.createParallelGroup(TRAILING, false)
                        .addComponent(textNameProject)
                        .addComponent(textCodeProject)
                        .addComponent(buttonFile)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(buttonOk)
                                .addComponent(buttonCancel)))
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addComponent(labelName)
                        .addComponent(textNameProject))
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addGap(10)
                        .addComponent(labelName)
                        .addComponent(textCodeProject))
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
