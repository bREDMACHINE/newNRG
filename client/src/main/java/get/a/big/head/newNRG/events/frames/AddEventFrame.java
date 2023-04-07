package get.a.big.head.newNRG.events.frames;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.awt.*;
import java.text.SimpleDateFormat;

@Getter
@Setter
public class AddEventFrame extends JFrame {

    private JFrame frame;
    private JFormattedTextField textEventDate;
    private JTextField textEventName;
    private JTextField textDescription;
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

        JPanel panelLabels = new JPanel(new GridLayout(0, 4, 12, 5));
        DateFormatter dateFormatter = new DateFormatter(new SimpleDateFormat("yyyy-MM-dd"));
        dateFormatter.setAllowsInvalid(false);
        dateFormatter.setOverwriteMode(true);
        textEventDate = new JFormattedTextField(dateFormatter);
        textEventDate.setColumns(10);
        textEventName = new JTextField(15);
        textDescription = new JTextField(15);
        buttonFile = new JButton("Прикрепить");
        fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Выберите файл");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        panelLabels.add(new JLabel("Дата события"));
        panelLabels.add(textEventDate);
        panelLabels.add(new JLabel("Наименование"));
        panelLabels.add(textEventName);
        panelLabels.add(new JLabel("Описание"));
        panelLabels.add(textDescription);
        panelLabels.add(new JLabel("Документ"));
        panelLabels.add(buttonFile);
        frame.getContentPane().add(BorderLayout.CENTER, panelLabels);

        buttonOk = new JButton("OK");
        buttonCancel = new JButton("Отмена");
        JPanel panelButtons = new JPanel();
        panelButtons.add(buttonOk);
        panelButtons.add(buttonCancel);

        frame.getContentPane().add(BorderLayout.SOUTH, panelButtons);
        frame.pack();
        frame.setVisible(true);
    }
}
