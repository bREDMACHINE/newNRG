package get.a.big.head.newNRG.general;

import javax.swing.*;
import java.awt.*;

public class MessageFromServer extends JFrame {

    private JFrame frame;
    private JLabel labelMessage;
    private JPanel grid;
    private JPanel flow;

    public MessageFromServer(String message) {
        this.frame = new JFrame();
        this.labelMessage = new JLabel(message);
        frame.setLocation(100, 100);
        frame.setDefaultCloseOperation( EXIT_ON_CLOSE );

        grid = new JPanel(new GridLayout(1, 2, 5, 0));
        grid.add(labelMessage);
        grid.add (new JButton("OK"));
        flow = new JPanel(new FlowLayout(FlowLayout.CENTER));
        flow.add(grid);
        frame.getContentPane().add(flow, BorderLayout.SOUTH);
        frame.setVisible(true);
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
}
