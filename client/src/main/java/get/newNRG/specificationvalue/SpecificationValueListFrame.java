package get.newNRG.specificationvalue;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.List;

@Getter
@Setter
public class SpecificationValueListFrame extends JFrame {
    private JFrame frame;
    private JButton buttonPrevious;
    private JLabel labelPage;
    private JButton buttonNext;
    private JPanel panelButtons;

    public SpecificationValueListFrame(List<SpecificationValueDto> specificationValues, int page, int pages) {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel panelList = new JPanel(new GridLayout(18, 2, 5, 5));
        panelList.add(new JLabel("Наименование характеристики"));
        panelList.add(new JLabel("Значение"));
        for (SpecificationValueDto specificationValue : specificationValues) {
            panelList.add(new JLabel(specificationValue.getSpecification().getSpecificationName()));
            panelList.add(new JLabel(specificationValue.getSpecificationValue().toString()));
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
