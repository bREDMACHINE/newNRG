package get.a.big.head.newNRG.events;

import get.a.big.head.newNRG.equipment.Equipment;
import get.a.big.head.newNRG.equipment.EquipmentMapper;
import get.a.big.head.newNRG.users.controllers.UserAuthorizationFrameController;
import lombok.Getter;
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
@Getter
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AddEventFrameController {

    private AddEventFrame frame;
    private boolean close = true;
    private final EventClient eventClient;
    private final UserAuthorizationFrameController authorizationFrameController;

    public void initAddEventFrameController() {
        if (close) {
            frame = new AddEventFrame();
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

        frame.getButtonOk().addActionListener(e -> {
            String createEvent = frame.getTextCreateEvent().getText();
            String nameEvent = frame.getTextEventName().getText();
            String description = frame.getTextDescription().getText();
            log.info("Add event  with createEvent {}, nameEvent {}, description {}",
                    createEvent, nameEvent, description);
            EventDto eventDto = EventMapper.toEventDto(createEvent, nameEvent, description);
            ResponseEntity<Object> addEventAnswer = eventClient.addEvent(
                    eventDto,
                    authorizationFrameController.getUser().getUserId()
            );

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
