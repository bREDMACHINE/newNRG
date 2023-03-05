package get.a.big.head.newNRG.events.controllers;

import get.a.big.head.newNRG.events.Event;
import get.a.big.head.newNRG.events.EventClient;
import get.a.big.head.newNRG.events.EventMapper;
import get.a.big.head.newNRG.events.frames.AddEventFrame;
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
    private Event event;
    private final EventClient eventClient;
    private final UserAuthorizationFrameController authorizationFrameController;

    public void initAddEventFrameController() {
        frame = new AddEventFrame();

        frame.getFrame().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                frame = null;
            }
        });

        frame.getButtonOk().addActionListener(e -> {
            String createEvent = frame.getTextCreateEvent().getText();
            String nameEvent = frame.getTextEventName().getText();
            String description = frame.getTextDescription().getText();
            log.info("Add event  with createEvent {}, nameEvent {}, description {}",
                    createEvent, nameEvent, description);
            ResponseEntity<Object> addEventResponse = eventClient.addEvent(
                    Event.builder()
                            .createEvent(createEvent)
                            .descriptionEvent(description)
                            .nameEvent(nameEvent).build(),
                    authorizationFrameController.getUser().getUserId()
            );

            if (addEventResponse.getStatusCode().is2xxSuccessful() && addEventResponse.getBody() != null) {
                event = EventMapper.toEvent(addEventResponse.getBody());
                frame.getFrame().dispose();
                JOptionPane.showMessageDialog(frame.getFrame(),
                        "Событие " + event.getNameEvent() + " успешно создано");
            } else {
                JOptionPane.showMessageDialog(
                        frame.getFrame(),
                        addEventResponse.getStatusCode().toString(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        frame.getButtonCancel().addActionListener(e -> frame.getFrame().dispose());
    }
}
