package get.a.big.head.newNRG.projectdocumentations;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ProjectDocumentationListFrame {

    private JFrame frame;
    private List<JButton> openFileButtons = new ArrayList<>();
    private JButton buttonPrevious;
    private JLabel labelPage;
    private JButton buttonNext;
    private JPanel panelButtons;

    public ProjectDocumentationListFrame(List<ProjectDocumentationDto> projects, int page, int pages) {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel panelList = new JPanel(new GridLayout(0, 3, 12, 5));
        panelList.add(new JLabel("Наименование проекта"));
        panelList.add(new JLabel("Шифр проекта"));
        panelList.add(new JLabel("Прикрепленный файл"));

        for (int i = 0; i < projects.size(); i++) {
            ProjectDocumentationDto project = projects.get(i);
            JButton openFileButton = new JButton(project.getProjectDocumentation());
            openFileButton.setActionCommand(String.valueOf(i));
            openFileButtons.add(openFileButton);

            panelList.add(new JLabel(project.getNameProjectDocumentation()));
            panelList.add(new JLabel(project.getCodeProjectDocumentation()));
            panelList.add(openFileButton);
        }
        frame.getContentPane().add(BorderLayout.NORTH, panelList);

        labelPage = new JLabel("Страница " + page + " из " + pages);
        buttonPrevious = new JButton("< Предыдущая");
        buttonNext = new JButton("Следующая >");
        panelButtons = new JPanel();
        panelButtons.add(labelPage);
        frame.getContentPane().add(BorderLayout.SOUTH, panelButtons);
        frame.pack();
        frame.setVisible(true);
    }
}
