package get.newNRG.general;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static javax.swing.GroupLayout.Alignment.TRAILING;

@Getter
@Setter
public class ListFrameWithOpenCard extends JFrame {
    private JFrame frame;
    private JTextField textFieldFinder;
    private JButton buttonFind;
    private List<JButton> openCardButtons = new ArrayList<>();
    private JButton buttonCreateCard;
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

        JPanel panelFinder = new JPanel();
        textFieldFinder = new JTextField(30);
        buttonFind = new JButton("Найти");
        panelFinder.add(textFieldFinder);
        panelFinder.add(buttonFind);

        JPanel panelList = new JPanel(new GridLayout(0, labels.size(), 12, 5));
        for (String label : labels) {
            panelList.add(new JLabel(label));
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
                        panelList.add(new JLabel(field.get(object).toString()));
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            JButton openCardButton = new JButton("Открыть карточку");
            openCardButton.setActionCommand(String.valueOf(i));
            openCardButtons.add(openCardButton);
            panelList.add(openCardButton);
        }

        buttonCreateCard = new JButton("Создать");

        buttonPrevious = new JButton("< Предыдущая");
        buttonNext = new JButton("Следующая >");
        labelPage = new JLabel("Страница " + page + " из " + pages);
        panelButtons = new JPanel();
        panelButtons.add(labelPage);
        frame.getContentPane().add(BorderLayout.SOUTH, panelButtons);

        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup(TRAILING, false)
                .addComponent(panelFinder)
                .addComponent(panelList)
                .addComponent(buttonCreateCard)
                .addComponent(panelButtons)
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(panelFinder)
                .addGap(100)
                .addComponent(panelList)
                .addGap(20)
                .addComponent(buttonCreateCard)
                .addGap(80)
                .addComponent(panelButtons)
        );
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
