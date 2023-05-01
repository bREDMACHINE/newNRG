package get.newNRG.general;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class ListFrameWithOpenCard extends JFrame {
    private JFrame frame;
    private List<JButton> openCardButtons = new ArrayList<>();
    private JButton buttonAddCard;
    private JButton buttonPrevious;
    private JLabel labelPage;
    private JButton buttonNext;
    private JPanel panelButtons;

    public ListFrameWithOpenCard(List<?> list, List<String> labels, int page, int pages) {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        frame.setFont(new Font("Arial", Font.PLAIN, 16));
        frame.getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(0, labels.size(), 12, 5));
        for (String label : labels) {
            panel.add(new JLabel(label));
        }
        Class<?> clazz = list.get(0).getClass();
        Field[] fields = clazz.getDeclaredFields();
        Field[] fileFields = clazz.getSuperclass().getDeclaredFields();
        Field[] allFields = new Field[fields.length + fileFields.length];
        Arrays.setAll(allFields, j -> (j < fields.length ? fields[j] : fileFields[j - fields.length]));
        for (int i = 0; i < list.size(); i++) {
            var object = list.get(i);
            for (Field field : allFields) {
                try {
                    field.setAccessible(true);
                    if (field.getType().equals(String.class)) {
                        panel.add(new JLabel(field.get(object).toString()));
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            JButton openCardButton = new JButton("Открыть карточку");
            openCardButton.setActionCommand(String.valueOf(i));
            openCardButtons.add(openCardButton);
            panel.add(openCardButton);
        }
        buttonAddCard = new JButton("Добавить");
        panel.add(buttonAddCard);
        panel.add(new JLabel());
        panel.add(new JLabel());
        frame.getContentPane().add(BorderLayout.NORTH, panel);

        buttonPrevious = new JButton("< Предыдущая");
        buttonNext = new JButton("Следующая >");
        labelPage = new JLabel("Страница " + page + " из " + pages);
        panelButtons = new JPanel();
        panelButtons.add(labelPage);
        frame.getContentPane().add(BorderLayout.SOUTH, panelButtons);
        frame.pack();
        frame.setVisible(true);
    }
}
