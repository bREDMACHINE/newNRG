package get.newNRG.type;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class TypeFrame extends JFrame {

    private JFrame frame;
    private JButton buttonSpecifications;
    private JButton buttonDocuments;
    private JButton buttonSpares;
    private JButton buttonDelete;
    private JButton buttonOk;
    private JButton buttonCancel;

    public TypeFrame(TypeDto type) {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel panelSpecifications = new JPanel(new GridLayout(5, 2, 5, 0));
        buttonSpecifications = new JButton("Просмотреть характеристики типа");
        buttonDocuments = new JButton("Просмотреть документы типа");
        buttonSpares = new JButton("Просмотреть запчасти");
        buttonDelete = new JButton("Удалить тип");
        panelSpecifications.add(new JLabel("Полный тип"));
        panelSpecifications.add(new JLabel(type.getTypeName()));
        panelSpecifications.add(new JLabel("Завод изготовитель"));
        panelSpecifications.add(new JLabel(type.getFactory().getFactoryName()));
        panelSpecifications.add(new JLabel("Полные характеристики"));
        panelSpecifications.add(buttonSpecifications);
        panelSpecifications.add(new JLabel("Заводские документы"));
        panelSpecifications.add(buttonDocuments);
        panelSpecifications.add(new JLabel("Детали для ремонта"));
        panelSpecifications.add(buttonSpares);
        panelSpecifications.add(buttonDelete);
        frame.getContentPane().add(BorderLayout.NORTH, panelSpecifications);

        buttonOk = new JButton("Ок");
        buttonCancel = new JButton("Закрыть");
        JPanel panelButtons = new JPanel();
        panelButtons.add(buttonOk);
        panelButtons.add(buttonCancel);
        frame.getContentPane().add(BorderLayout.SOUTH, panelButtons);

        frame.pack();
        frame.setVisible(true);
    }
}
