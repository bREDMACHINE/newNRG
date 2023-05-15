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
public class ListFrameWithFile extends JFrame {
    private JFrame frame;
    private List<JButton> openFileButtons = new ArrayList<>();
    private List<JButton> deleteButtons = new ArrayList<>();
    private JButton buttonPrevious;
    private JLabel labelPage;
    private JButton buttonNext;
    private JPanel panelButtons;

    public ListFrameWithFile(List<?> list, List<String> labels, int page, int pages) {
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
                    if (field.getName().equals("fileId") && field.get(object) != null) {
                        JButton openFileButton = new JButton("Открыть");
                        openFileButton.setActionCommand(String.valueOf(i));
                        openFileButtons.add(openFileButton);
                        panel.add(openFileButton);
                    }
                    if (field.getName().equals("fileId") && field.get(object) == null) {
                        panel.add(new JLabel("Файла нет"));
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            JButton deleteButton = new JButton("Удалить");
            deleteButton.setActionCommand(String.valueOf(i));
            deleteButtons.add(deleteButton);
            panel.add(deleteButton);
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
