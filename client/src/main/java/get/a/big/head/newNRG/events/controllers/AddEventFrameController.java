package get.a.big.head.newNRG.events.controllers;

import get.a.big.head.newNRG.events.EventClient;
import get.a.big.head.newNRG.events.EventDto;
import get.a.big.head.newNRG.events.EventMapper;
import get.a.big.head.newNRG.files.DataFileDto;
import get.a.big.head.newNRG.files.DataFileClient;
import get.a.big.head.newNRG.files.DataFileMapper;
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
import java.io.File;

@Controller
@Slf4j
@Getter
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AddEventFrameController {

    private AddEventFrame frame;
    private final EventClient eventClient;
    private final DataFileClient dataFileClient;
    private final UserAuthorizationFrameController authorizationFrameController;
    private File file = null;

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
                file = frame.getFileChooser().getSelectedFile();
                JOptionPane.showMessageDialog(frame,
                        "Файл " + frame.getFileChooser().getSelectedFile() + " выбран");
            }
        });

        frame.getButtonOk().addActionListener(e -> {
            String dateEvent = frame.getTextEventDate().getText();
            String nameEvent = frame.getTextEventName().getText();
            String descriptionEvent = frame.getTextDescription().getText();
            DataFileDto dataFile = null;
            if (file != null) {
                dataFile = DataFileMapper.toDataFileDto(file);
            }

            log.info("Add event  with dateEvent {}, nameEvent {}, description {}, document {}",
                    dateEvent, nameEvent, descriptionEvent, dataFile);
            Long fileId = dataFileClient.addFile(frame, dataFile, authorizationFrameController.getUser().getUserId()).getFileId();
            ResponseEntity<Object> addEventResponse = eventClient.addEvent(EventMapper.toEventDto(
                    equipmentId,
                    dateEvent,
                    nameEvent,
                    descriptionEvent,
                    fileId),
                    authorizationFrameController.getUser().getUserId()
            );

            if (addEventResponse.getStatusCode().is2xxSuccessful() && addEventResponse.getBody() != null) {
                frame.getFrame().dispose();
                EventDto eventDto = EventMapper.toEventDto(addEventResponse.getBody());
                JOptionPane.showMessageDialog(frame.getFrame(),
                         "Событие " + eventDto.getNameEvent() + " успешно добавлено");
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
