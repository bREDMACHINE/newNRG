package get.a.big.head.newNRG.events.frames;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class AddEventFrame extends JFrame {

    private JFrame frame;
    private JLabel labelCreateEvent;
    private JTextField textCreateEvent;
    private JLabel labelEventName;
    private JTextField textEventName;
    private JLabel labelDescription;
    private JTextField textDescription;
    private JLabel labelFile;
    private JButton buttonFile;
    private JButton buttonOk;
    private JButton buttonCancel;

    public AddEventFrame() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        frame.setSize(300, 800);

        JPanel panelLabels = new JPanel();
        labelCreateEvent = new JLabel("Дата события");
        labelEventName = new JLabel("Наименование");
        labelDescription = new JLabel("Описание");
        labelFile = new JLabel("Документ");
        textCreateEvent = new JTextField(15);
        textEventName = new JTextField(15);
        textDescription = new JTextField(15);
        buttonFile = new JButton("Прикрепить");

        panelLabels.add(labelCreateEvent);
        panelLabels.add(textCreateEvent);
        panelLabels.add(labelEventName);
        panelLabels.add(textEventName);
        panelLabels.add(labelDescription);
        panelLabels.add(textDescription);
        panelLabels.add(labelFile);
        panelLabels.add(buttonFile);
        frame.getContentPane().add(BorderLayout.CENTER, panelLabels);

        buttonOk = new JButton("OK");
        buttonCancel = new JButton("Отмена");
        JPanel panelButtons = new JPanel();
        panelButtons.add(buttonOk);
        panelButtons.add(buttonCancel);
        frame.getContentPane().add(BorderLayout.SOUTH, panelButtons);
        frame.setVisible(true);
    }
}
