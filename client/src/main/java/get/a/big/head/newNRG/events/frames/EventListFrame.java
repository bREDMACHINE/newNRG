package get.a.big.head.newNRG.events.frames;

import get.a.big.head.newNRG.events.EventDto;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.List;

@Getter
@Setter
public class EventListFrame extends JFrame {
    private JFrame frame;
    private List<JButton> openFileButtons;
    private List<JButton> deleteEventButtons;
    private JButton buttonPrevious;
    private JLabel labelPage;
    private JButton buttonNext;
    private JButton buttonClose;

    public EventListFrame(List<EventDto> events, int page) {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel panelEvents = new JPanel(new GridLayout(18, 4, 5, 5));
        panelEvents.add(new JLabel("Дата события"));
        panelEvents.add(new JLabel("Событие"));
        panelEvents.add(new JLabel("Описание"));
        panelEvents.add(new JLabel("Прикрепленный файл"));
        panelEvents.add(new JLabel("Удалить событие"));
        for (int i = 0; i < events.size(); i++) {
            EventDto event = events.get(i);
            JButton openFileButton = new JButton(event.getDocumentEvent());
            openFileButton.setActionCommand(String.valueOf(i));
            openFileButtons.add(openFileButton);
            JButton deleteEventButton = new JButton("Удалить");
            deleteEventButton.setActionCommand(String.valueOf(i));
            deleteEventButtons.add(deleteEventButton);

            panelEvents.add(new JLabel(event.getTimeEvent()));
            panelEvents.add(new JLabel(event.getNameEvent()));
            panelEvents.add(new JLabel(event.getDescriptionEvent()));
            panelEvents.add(openFileButton);
            panelEvents.add(deleteEventButton);
        }
        buttonPrevious = new JButton("Предыдущая");
        buttonNext = new JButton("Следующая");
        panelEvents.add(new JLabel());
        panelEvents.add(new JLabel());
        panelEvents.add(buttonPrevious);
        panelEvents.add(buttonNext);
        frame.getContentPane().add(BorderLayout.NORTH, panelEvents);

        labelPage = new JLabel("Страница " + page);
        buttonClose = new JButton("Закрыть");
        JPanel panelButtons = new JPanel();
        panelButtons.add(labelPage);
        panelButtons.add(buttonClose);
        frame.getContentPane().add(BorderLayout.SOUTH, panelButtons);
        frame.pack();
        frame.setVisible(true);
    }
}
