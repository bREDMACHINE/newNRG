package get.newNRG.spares;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SpareListFrame extends JFrame {
    private JFrame frame;
    private List<JButton> deleteButtons = new ArrayList<>();
    private JButton buttonPrevious;
    private JLabel labelPage;
    private JButton buttonNext;
    private JPanel panelButtons;

    public SpareListFrame(List<SpareDto> spares, int page, int pages) {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel panelList = new JPanel(new GridLayout(18, 4, 5, 5));
        panelList.add(new JLabel("Краткое наименование"));
        panelList.add(new JLabel("Полное описание"));
        panelList.add(new JLabel("Номер материала"));
        panelList.add(new JLabel(" "));
        for (int i = 0; i < spares.size(); i++) {
            SpareDto spare = spares.get(i);
            JButton deleteButton = new JButton("Удалить");
            deleteButton.setActionCommand(String.valueOf(i));
            deleteButtons.add(deleteButton);

            panelList.add(new JLabel(spare.getSpareName()));
            panelList.add(new JLabel(spare.getSpareDescription()));
            panelList.add(new JLabel(spare.getSpareCode()));
            panelList.add(deleteButton);
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
