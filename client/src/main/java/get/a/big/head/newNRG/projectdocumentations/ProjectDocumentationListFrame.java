package get.a.big.head.newNRG.projectdocumentations;

import get.a.big.head.newNRG.events.EventDto;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.List;

@Getter
@Setter
public class ProjectDocumentationListFrame {

    private JFrame frame;
    private List<JButton> openFileButtons;
    private List<JButton> deleteButtons;
    private JButton previous;
    private JButton next;
    private JButton buttonClose;

    public ProjectDocumentationListFrame(List<ProjectDocumentationDto> projects) {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel panelEvents = new JPanel(new GridLayout(18, 4, 5, 5));
        panelEvents.add(new JLabel("Наименование проекта"));
        panelEvents.add(new JLabel("Шифр проекта"));
        panelEvents.add(new JLabel("Прикрепленный файл"));
        panelEvents.add(new JLabel("Удалить событие"));
        for (int i = 0; i < projects.size(); i++) {
            ProjectDocumentationDto project = projects.get(i);
            JButton openFileButton = new JButton(project.getProjectDocumentation());
            openFileButton.setActionCommand(String.valueOf(i));
            openFileButtons.add(openFileButton);
            JButton deleteButton = new JButton("Удалить");
            deleteButton.setActionCommand(String.valueOf(i));
            deleteButtons.add(deleteButton);

            panelEvents.add(new JLabel(project.getNameProjectDocumentation()));
            panelEvents.add(new JLabel(project.getCodeProjectDocumentation()));
            panelEvents.add(openFileButton);
            panelEvents.add(deleteButton);
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
