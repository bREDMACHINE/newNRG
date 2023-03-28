package get.a.big.head.newNRG.general.frames;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import javax.swing.*;

import static javax.swing.GroupLayout.Alignment.*;

@Getter
@Setter
public class UserMainFrame extends JFrame {

    protected JFrame frame;
    private JButton buttonProfile;
    private JTextField textField;
    private JButton buttonFind;
    private JButton buttonAddEquipment;
    private JComponent panelButtons;

    public UserMainFrame(String email) {
        frame = new JFrame("Поиск оборудования");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setFont(new Font("Arial", Font.PLAIN, 16));
        frame.getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        JPanel panelFinder = new JPanel();
        textField = new JTextField("Введите оперативное наименование",30);
        buttonFind = new JButton("Найти");
        panelFinder.add(textField);
        panelFinder.add(buttonFind);
        buttonProfile = new JButton(email);

        panelButtons = new JPanel();
        JPanel flow = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        flow.add(panelButtons);
        panelButtons.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        buttonAddEquipment = new JButton("Добавить оборудование");

        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup(TRAILING, false)
                        .addComponent(buttonProfile)
                        .addComponent(panelFinder)
                        .addComponent(flow)
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(buttonProfile)
                .addGap(100)
                .addComponent(panelFinder)
                .addGap(80)
                .addComponent(flow)
        );
        frame.getContentPane().add(panel);

        frame.pack();
        frame.setVisible(true);
    }
}
