package get.a.big.head.newNRG.equipment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@Lazy
@Controller
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class EquipmentFrameController {

    private EquipmentFrame frame;
    private boolean close = true;
    private final EquipmentClient equipmentClient;

    public void initEquipmentFrameController(Equipment equipment) {
        if (close) {
            frame = new EquipmentFrame(equipment);
            close = false;
        } else {
            frame.getFrame().toFront();
            frame.getFrame().requestFocus();
        }

        frame.getFrame().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                close = true;
            }
        });

        frame.getButtonCancel().addActionListener(e -> frame.getFrame().dispose());
    }

    public Equipment getEquipment(String text, String userId) {
        ResponseEntity<Object> equipmentAnswer = equipmentClient.findEquipment(text, userId);
        if (equipmentAnswer.getStatusCode().is2xxSuccessful()) {
            return EquipmentMapper.toEquipment(equipmentAnswer.getBody());
        } else {
            JOptionPane.showMessageDialog(frame.getFrame(), equipmentAnswer.getStatusCode().toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public EquipmentFrame getFrame() {
        return frame;
    }
}
