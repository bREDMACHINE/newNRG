package get.a.big.head.newNRG.events;

import get.a.big.head.newNRG.equipment.Equipment;
import get.a.big.head.newNRG.equipment.EquipmentDto;
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
public class AddEventFrameController {

    private AddEventFrame frame;
    private final List<JFrame> windows = new ArrayList<>();
    private final EventClient eventClient;

    public void initAddEventController(String role) {
        if (windows.size() == 0) {
            frame = new AddEventFrame();
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

        frame.getButtonOk().addActionListener(e -> {
            String createEvent = frame.getTextCreateEvent().getText();
            String nameEvent = frame.getTextEventName().getText();
            String description = frame.getTextDescription().getText();
            log.info("Add event  with createEvent {}, nameEvent {}, description {}",
                    createEvent, nameEvent, description);
            EventDto eventDto = EventMapper.toEventDto(createEvent, nameEvent, description);
            ResponseEntity<Object> addEventAnswer = eventClient.addEvent(eventDto, role);

            if (addEventAnswer.getStatusCode().is2xxSuccessful()) {
                Equipment equipment = EquipmentMapper.toEquipment(addEventAnswer.getBody());
                frame.getFrame().dispose();
                JOptionPane.showMessageDialog(frame.getFrame(),
                        "Оборудование " + equipment.getOperationalName() + " успешно добавлено");
            } else {
                JOptionPane.showMessageDialog(frame.getFrame(), addEventAnswer.getStatusCode().toString(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.getButtonCancel().addActionListener(e -> frame.getFrame().dispose());
    }
}
