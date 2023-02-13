package get.a.big.head.newNRG.events;

import get.a.big.head.newNRG.equipment.Equipment;
import get.a.big.head.newNRG.equipment.EquipmentClient;
import get.a.big.head.newNRG.equipment.EquipmentFrame;
import get.a.big.head.newNRG.equipment.EquipmentMapper;
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
public class EventFrameController {

    private EventFrame frame;
    private final List<JFrame> windows = new ArrayList<>();
    private final EventClient eventClient;

    public void initEventController(Event event) {
        if (windows.size() == 0) {
            frame = new EventFrame(event);
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

    public Event getEvent(String userId) {
        ResponseEntity<Object> eventAnswer = eventClient.getEvent(userId);
        if (eventAnswer.getStatusCode().is2xxSuccessful()) {
            return EventMapper.toEvent(eventAnswer.getBody());
        } else {
            JOptionPane.showMessageDialog(frame.getFrame(), eventAnswer.getStatusCode().toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
}
