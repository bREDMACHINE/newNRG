package get.a.big.head.newNRG.general;

import javax.swing.*;
import java.awt.*;

public class MessageFromServerFrame extends JFrame {

    private JFrame frame;
    private JLabel labelMessage;
    private JPanel grid;
    private JPanel flow;
    private JButton button;

    public MessageFromServerFrame(String message) {
        frame = new JFrame();
        frame.setSize(320, 240);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

//        labelMessage = new JLabel(message);
//        button = new JButton("OK");
//        grid = new JPanel();
//        grid.setLayout(new GridLayout(1, 0, 1, 20));
//        grid.add(button);
//        flow = new JPanel();
//        flow.add(labelMessage);
//        flow.add(button);
//        frame.getContentPane().add(flow, );
//        frame.setVisible(true);
        JOptionPane.showMessageDialog(frame, message);
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JLabel getLabelMessage() {
        return labelMessage;
    }

    public void setLabelMessage(JLabel labelMessage) {
        this.labelMessage = labelMessage;
    }

    public JPanel getGrid() {
        return grid;
    }

    public void setGrid(JPanel grid) {
        this.grid = grid;
    }

    public JPanel getFlow() {
        return flow;
    }

    public void setFlow(JPanel flow) {
        this.flow = flow;
    }

    public JButton getButton() {
        return button;
    }

    public void setButton(JButton button) {
        this.button = button;
    }
}
