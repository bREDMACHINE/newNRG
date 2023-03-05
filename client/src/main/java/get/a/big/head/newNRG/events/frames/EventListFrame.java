package get.a.big.head.newNRG.events.frames;

import get.a.big.head.newNRG.events.Event;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.List;

@Getter
@Setter
public class EventListFrame extends JFrame {
    private JFrame frame;
    private List<JButton> buttons;
    private JButton previous;
    private JButton next;
    private JButton buttonClose;

    public EventListFrame(List<Event> events) {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel panelEvents = new JPanel(new GridLayout(18, 4, 5, 5));
        panelEvents.add(new JLabel("Дата создания"));
        panelEvents.add(new JLabel("Событие"));
        panelEvents.add(new JLabel("Описание"));
        panelEvents.add(new JLabel("Прикрепленный файл"));
        for (int i = 0; i < events.size(); i++) {
            Event event = events.get(i);
            JButton button = new JButton(event.getDocumentEvent());
            button.setActionCommand(String.valueOf(i));
            buttons.add(button);
            panelEvents.add(new JLabel(event.getCreateEvent()));
            panelEvents.add(new JLabel(event.getNameEvent()));
            panelEvents.add(new JLabel(event.getDescriptionEvent()));
            panelEvents.add(button);
        }
        previous = new JButton("Предыдущая");
        next = new JButton("Следующая");
        panelEvents.add(new JLabel());
        panelEvents.add(new JLabel());
        panelEvents.add(previous);
        panelEvents.add(next);
        frame.getContentPane().add(BorderLayout.NORTH, panelEvents);

        buttonClose = new JButton("Закрыть");
        JPanel panelButtons = new JPanel();
        panelButtons.add(buttonClose);
        frame.getContentPane().add(BorderLayout.SOUTH, panelButtons);
        frame.pack();
        frame.setVisible(true);
    }
}
