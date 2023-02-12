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
import java.util.ArrayList;
import java.util.List;

@Lazy
@Controller
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class EquipmentFrameController {

    private EquipmentFrame frame;
    private final List<JFrame> windows = new ArrayList<>();
    private final EquipmentClient equipmentClient;

    public void initEquipmentController(Equipment equipment) {
        if (windows.size() == 0) {
            frame = new EquipmentFrame(equipment);
            windows.add(frame);
        } else {
            frame.getFrame().toFront();
            frame.getFrame().requestFocus();
        }

        frame.getFrame().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                windows.clear();
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
}
