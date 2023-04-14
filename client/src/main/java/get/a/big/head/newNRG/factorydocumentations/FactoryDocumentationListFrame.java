package get.a.big.head.newNRG.factorydocumentations;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class FactoryDocumentationListFrame {
    private JFrame frame;
    private List<JButton> openFileButtons = new ArrayList<>();
    private List<JButton> deleteButtons = new ArrayList<>();
    private JButton buttonPrevious;
    private JLabel labelPage;
    private JButton buttonNext;
    private JPanel panelButtons;

    public FactoryDocumentationListFrame(List<FactoryDocumentationDto> documents, int page, int pages) {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel panelList = new JPanel(new GridLayout(18, 4, 5, 5));
        panelList.add(new JLabel("Наименование документа"));
        panelList.add(new JLabel("Шифр документа"));
        panelList.add(new JLabel("Прикрепленный файл"));

        for (int i = 0; i < documents.size(); i++) {
            FactoryDocumentationDto document = documents.get(i);
            JButton openFileButton = new JButton("Открыть");
            openFileButton.setActionCommand(String.valueOf(i));
            openFileButtons.add(openFileButton);
            JButton deleteEventButton = new JButton("Удалить");
            deleteEventButton.setActionCommand(String.valueOf(i));
            deleteButtons.add(deleteEventButton);

            panelList.add(new JLabel(document.getNameFactoryDocumentation()));
            panelList.add(new JLabel(document.getCodeFactoryDocumentation()));
            panelList.add(openFileButton);
            panelList.add(deleteEventButton);
        }
        buttonPrevious = new JButton("< Предыдущая");
        buttonNext = new JButton("Следующая >");
        panelList.add(new JLabel());
        panelList.add(new JLabel());
        frame.getContentPane().add(BorderLayout.NORTH, panelList);

        labelPage = new JLabel("Страница " + page + " из " + pages);
        panelButtons = new JPanel();
        panelButtons.add(labelPage);
        frame.getContentPane().add(BorderLayout.SOUTH, panelButtons);
        frame.pack();
        frame.setVisible(true);
    }
}
