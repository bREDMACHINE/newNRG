package get.a.big.head.newNRG.events;

import lombok.Data;

import javax.swing.*;
import java.awt.*;

@Data
public class EventFrame extends JFrame {

    private JFrame frame;
    private JLabel createEvent;
    private JLabel eventName;
    private JLabel description;
    private JLabel file;
    private JButton buttonOk;
    private JButton buttonCancel;

    public EventFrame(Event event) {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        frame.setSize(300, 300);

        JPanel panelLabels = new JPanel();
        createEvent = new JLabel(event.getCreateEvent().toString());
        eventName = new JLabel(event.getName());
        description = new JLabel(event.getDescription());
        file = new JLabel(event.getFile().getName());

        panelLabels.add(createEvent);
        panelLabels.add(eventName);
        panelLabels.add(description);
        panelLabels.add(file);
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