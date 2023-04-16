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
        frame.setFont(new Font("Arial", Font.PLAIN, 16));
        frame.getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(0, 5, 12, 5));
        panel.add(new JLabel("Дата события"));
        panel.add(new JLabel("Событие"));
        panel.add(new JLabel("Описание"));
        panel.add(new JLabel("Документы"));
        panel.add(new JLabel("Удалить событие"));
        for (int i = 0; i < events.size(); i++) {
            EventDto event = events.get(i);
            panel.add(new JLabel(event.getDateEvent()));
            panel.add(new JLabel(event.getNameEvent()));
            panel.add(new JLabel(event.getDescriptionEvent()));
            if (event.getFileId() != null) {
                JButton openFileButton = new JButton("Открыть");
                openFileButton.setActionCommand(String.valueOf(i));
                openFileButtons.add(openFileButton);
                panel.add(openFileButton);
            } else {
                panel.add(new JLabel("Файла нет"));
            }
            JButton deleteEventButton = new JButton("Удалить");
            deleteEventButton.setActionCommand(String.valueOf(i));
            deleteButtons.add(deleteEventButton);
            panel.add(deleteEventButton);
        }
        buttonPrevious = new JButton("< Предыдущая");
        buttonNext = new JButton("Следующая >");
        panel.add(new JLabel());
        panel.add(new JLabel());
        frame.getContentPane().add(BorderLayout.NORTH, panel);

        labelPage = new JLabel("Страница " + page + " из " + pages);
        panelButtons = new JPanel();
        panelButtons.add(labelPage);
        frame.getContentPane().add(BorderLayout.SOUTH, panelButtons);
        frame.pack();
        frame.setVisible(true);
    }
}
