package get.a.big.head.newNRG.projectdocumentations;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

@Getter
@Setter
public class ProjectDocumentationFrame extends JFrame {

    private JFrame frame;
    private JButton buttonOpenFile;
    private JButton buttonOk;
    private JButton buttonCancel;

    public ProjectDocumentationFrame(Set<ProjectDocumentation> projectDocuments) {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);


        JPanel panelSpecifications = new JPanel(new GridLayout(7, 2, 5, 0));
        for (ProjectDocumentation projectDocumentation : projectDocuments) {
            buttonOpenFile = new JButton(projectDocumentation.getCodeProjectDocumentation());
            panelSpecifications.add(new JLabel(projectDocumentation.getNameProjectDocumentation()));
            panelSpecifications.add(buttonOpenFile);
        }
        frame.getContentPane().add(BorderLayout.NORTH, panelSpecifications);

        buttonOk = new JButton("OK");
        buttonCancel = new JButton("Отмена");
        JPanel panelButtons = new JPanel();
        panelButtons.add(buttonOk);
        panelButtons.add(buttonCancel);
        frame.getContentPane().add(BorderLayout.SOUTH, panelButtons);

        frame.pack();
        frame.setVisible(true);
    }
}
