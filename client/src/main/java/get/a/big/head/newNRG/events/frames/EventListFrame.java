package get.a.big.head.newNRG.events.frames;

import get.a.big.head.newNRG.events.EventDto;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class EventListFrame extends JFrame {
    private JFrame frame;
    private List<JButton> openFileButtons = new ArrayList<>();
    private List<JButton> deleteButtons = new ArrayList<>();
    private JButton buttonPrevious;
    private JLabel labelPage;
    private JButton buttonNext;
    private JPanel panelButtons;

    public EventListFrame(List<EventDto> events, int page, int pages) {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel panelEvents = new JPanel(new GridLayout(0, 5, 12, 5));
        panelEvents.add(new JLabel("Дата события"));
        panelEvents.add(new JLabel("Событие"));
        panelEvents.add(new JLabel("Описание"));
        panelEvents.add(new JLabel("Документы"));
        panelEvents.add(new JLabel("Удалить событие"));
        for (int i = 0; i < events.size(); i++) {
            EventDto event = events.get(i);
            JButton openFileButton = new JButton("Открыть");
            openFileButton.setActionCommand(String.valueOf(i));
            openFileButtons.add(openFileButton);
            JButton deleteEventButton = new JButton("Удалить");
            deleteEventButton.setActionCommand(String.valueOf(i));
            deleteButtons.add(deleteEventButton);

            panelEvents.add(new JLabel(event.getDateEvent()));
            panelEvents.add(new JLabel(event.getNameEvent()));
            panelEvents.add(new JLabel(event.getDescriptionEvent()));
            panelEvents.add(openFileButton);
            panelEvents.add(deleteEventButton);
        }
        buttonPrevious = new JButton("< Предыдущая");
        buttonNext = new JButton("Следующая >");
        panelEvents.add(new JLabel());
        panelEvents.add(new JLabel());
        frame.getContentPane().add(BorderLayout.NORTH, panelEvents);

        labelPage = new JLabel("Страница " + page + " из " + pages);
        panelButtons = new JPanel();
        panelButtons.add(labelPage);
        frame.getContentPane().add(BorderLayout.SOUTH, panelButtons);
        frame.pack();
        frame.setVisible(true);
    }
}
