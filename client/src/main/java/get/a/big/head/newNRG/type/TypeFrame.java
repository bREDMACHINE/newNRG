package get.a.big.head.newNRG.type;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

@Getter
@Setter
public class TypeFrame extends JFrame {

    private JFrame frame;

    public TypeFrame() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        frame.pack();
        frame.setVisible(true);
    }
}
