package get.newNRG.spares;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

import static javax.swing.GroupLayout.Alignment.*;
import static javax.swing.GroupLayout.Alignment.BASELINE;

@Getter
@Setter
public class SpareFrame extends JFrame {

    private JFrame frame;
    private JButton buttonDelete;
    private JButton buttonCancel;

    public SpareFrame(SpareDto spare) {

        frame = new JFrame();
        frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        frame.setFont(new Font("Arial", Font.PLAIN, 16));
        frame.getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        frame.setLocationRelativeTo(null);

        JLabel labelName = new JLabel("Краткое наименование");
        JLabel labelNameSpare = new JLabel(spare.getSpareName());
        JLabel labelDescription = new JLabel("Полное описание");
        JLabel labelDescriptionSpare = new JLabel(spare.getSpareDescription());
        JLabel labelCode = new JLabel("Номер материала");
        JLabel labelCodeSpare = new JLabel(spare.getSpareCode());
        buttonDelete = new JButton("Удалить");
        buttonCancel = new JButton("Закрыть");

        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(LEADING)
                        .addComponent(labelName)
                        .addComponent(labelDescription)
                        .addComponent(labelCode))
                .addGroup(layout.createParallelGroup(TRAILING, false)
                        .addComponent(labelNameSpare)
                        .addComponent(labelDescriptionSpare)
                        .addComponent(labelCodeSpare)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(buttonDelete)
                                .addComponent(buttonCancel)))
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addGap(10)
                        .addComponent(labelName)
                        .addComponent(labelNameSpare))
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addGap(10)
                        .addComponent(labelDescription)
                        .addComponent(labelDescriptionSpare))
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addGap(10)
                        .addComponent(labelCode)
                        .addComponent(labelCodeSpare))
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addComponent(buttonDelete)
                        .addComponent(buttonCancel))
        );

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
