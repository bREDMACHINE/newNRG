package get.newNRG.type;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static javax.swing.GroupLayout.Alignment.*;
import static javax.swing.GroupLayout.Alignment.BASELINE;

@Getter
@Setter
public class AddTypeFrame extends JFrame {

    private JFrame frame;
    private JTextField textTypeName;
    private JComboBox<String> factoryMenu;
    private JButton buttonAddFactory;
    private JButton buttonAddSpecification;
    private JComboBox<String> specificationMenu1;
    private JComboBox<String> specificationMenu2;
    private JComboBox<String> specificationMenu3;
    private JComboBox<String> specificationMenu4;
    private JComboBox<String> specificationMenu5;
    private JTextField textSpecificationValue1;
    private JTextField textSpecificationValue2;
    private JTextField textSpecificationValue3;
    private JTextField textSpecificationValue4;
    private JTextField textSpecificationValue5;
    private JButton buttonOk;
    private JButton buttonCancel;

    public AddTypeFrame(List<String> specifications, List<String> factories) {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        frame.setFont(new Font("Arial", Font.PLAIN, 16));
        frame.getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        frame.setLocationRelativeTo(null);

        factories.add(0, "Выберите производителя");
        specifications.add(0, "Выберите характеристику");
        JPanel panel = new JPanel();
        JLabel labelType = new JLabel("Тип по паспорту");
        textTypeName = new JTextField(15);
        JLabel labelFactory = new JLabel("Завод изготовитель");
        factoryMenu = new JComboBox<>(factories.toArray(new String[factories.size()]));
        buttonAddFactory = new JButton("Добавить завод");
        buttonAddSpecification = new JButton("Добавить характеристику");
        String[] specificationsArray = specifications.toArray(new String[specifications.size()]);
        specificationMenu1 = new JComboBox<>(specificationsArray);
        specificationMenu2 = new JComboBox<>(specificationsArray);
        specificationMenu3 = new JComboBox<>(specificationsArray);
        specificationMenu4 = new JComboBox<>(specificationsArray);
        specificationMenu5 = new JComboBox<>(specificationsArray);
        textSpecificationValue1 = new JTextField(5);
        textSpecificationValue2 = new JTextField(5);
        textSpecificationValue3 = new JTextField(5);
        textSpecificationValue4 = new JTextField(5);
        textSpecificationValue5 = new JTextField(5);
        buttonOk = new JButton("OK");
        buttonCancel = new JButton("Отмена");
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(LEADING)
                        .addComponent(labelType)
                        .addComponent(labelFactory)
                        .addComponent(specificationMenu1)
                        .addComponent(specificationMenu2)
                        .addComponent(specificationMenu3)
                        .addComponent(specificationMenu4)
                        .addComponent(specificationMenu5))
                .addGroup(layout.createParallelGroup(TRAILING, false)
                        .addComponent(textTypeName)
                        .addComponent(factoryMenu)
                        .addComponent(buttonAddFactory)
                        .addComponent(textSpecificationValue1)
                        .addComponent(textSpecificationValue2)
                        .addComponent(textSpecificationValue3)
                        .addComponent(textSpecificationValue4)
                        .addComponent(textSpecificationValue5)
                        .addComponent(buttonAddSpecification)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(buttonOk)
                                .addComponent(buttonCancel)))
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addComponent(labelType)
                        .addComponent(textTypeName))
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addGap(10)
                        .addComponent(labelFactory)
                        .addComponent(factoryMenu))
                .addGap(10)
                .addComponent(buttonAddFactory)
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addGap(10)
                        .addComponent(specificationMenu1)
                        .addComponent(textSpecificationValue1))
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addGap(10)
                        .addComponent(specificationMenu2)
                        .addComponent(textSpecificationValue2))
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addGap(10)
                        .addComponent(specificationMenu3)
                        .addComponent(textSpecificationValue3))
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addGap(10)
                        .addComponent(specificationMenu4)
                        .addComponent(textSpecificationValue4))
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addGap(10)
                        .addComponent(specificationMenu5)
                        .addComponent(textSpecificationValue5))
                .addGap(10)
                .addComponent(buttonAddSpecification)
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addGap(10)
                        .addComponent(buttonOk)
                        .addComponent(buttonCancel))
        );

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
