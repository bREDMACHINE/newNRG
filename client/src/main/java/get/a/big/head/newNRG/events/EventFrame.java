package get.a.big.head.newNRG.events;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
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
        createEvent = new JLabel(event.getCreateEvent());
        eventName = new JLabel(event.getNameEvent());
        description = new JLabel(event.getDescriptionEvent());
        file = new JLabel(event.getDescriptionEvent());

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