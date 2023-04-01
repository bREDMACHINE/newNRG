package get.a.big.head.newNRG.events.controllers;

import get.a.big.head.newNRG.events.EventClient;
import get.a.big.head.newNRG.events.EventDto;
import get.a.big.head.newNRG.events.EventMapper;
import get.a.big.head.newNRG.events.frames.AddEventFrame;
import get.a.big.head.newNRG.users.controllers.UserAuthorizationFrameController;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Controller
@Slf4j
@Getter
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AddEventFrameController {

    private AddEventFrame frame;
    private final EventClient eventClient;
    private final UserAuthorizationFrameController authorizationFrameController;
    private Path path = null;

    public void initAddEventFrameController(Long equipmentId) {
        frame = new AddEventFrame();

        frame.getFrame().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                frame = null;
            }
        });

        frame.getButtonCancel().addActionListener(e -> frame.getFrame().dispose());

        frame.getButtonFile().addActionListener(e -> {
            int result = frame.getFileChooser().showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION ) {
                path = Path.of(frame.getFileChooser().getSelectedFile().getAbsolutePath());
                JOptionPane.showMessageDialog(frame,
                        "Файл " + frame.getFileChooser().getSelectedFile() + " выбран");
            }
        });

        frame.getButtonOk().addActionListener(e -> {
            String dateEvent = frame.getTextEventTime().getText();
            String nameEvent = frame.getTextEventName().getText();
            String descriptionEvent = frame.getTextDescription().getText();
            byte[] fileArray = null;
            try {
                fileArray = Files.readAllBytes(path);
            } catch (IOException x) {
                JOptionPane.showMessageDialog(
                        frame.getFrame(),
                        "Файл не загружен",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
            log.info("Add event  with createEvent {}, nameEvent {}, description {}, document {}",
                    dateEvent, nameEvent, descriptionEvent, fileArray);
            ResponseEntity<Object> addEventResponse = eventClient.addEvent(EventMapper.toEventDto(
                    equipmentId,
                    dateEvent,
                    nameEvent,
                    descriptionEvent,
                    fileArray),
                    authorizationFrameController.getUser().getUserId()
            );

            if (addEventResponse.getStatusCode().is2xxSuccessful() && addEventResponse.getBody() != null) {
                EventDto event = EventMapper.toEventDto(addEventResponse.getBody());
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
    }
}
