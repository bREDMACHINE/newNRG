package get.a.big.head.newNRG.general;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WindowToFront extends JFrame {

    private JFrame frame;

    public WindowToFront() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowLostFocus(WindowEvent e) {
                e.getWindow().toFront();
                e.getWindow().requestFocus();
            }
        });
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }
}
