package get.a.big.head.newNRG.events.controllers;

import get.a.big.head.newNRG.events.EventClient;
import get.a.big.head.newNRG.events.EventMapper;
import get.a.big.head.newNRG.files.DataFileDto;
import get.a.big.head.newNRG.files.DataFileClient;
import get.a.big.head.newNRG.files.DataFileMapper;
import get.a.big.head.newNRG.events.frames.AddEventFrame;
import get.a.big.head.newNRG.users.controllers.UserAuthorizationFrameController;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

@Getter
@Component
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

            Long fileId = dataFileClient.addFile(frame, dataFile, authorizationFrameController.getUser().getUserId()).getFileId();
            eventClient.addEvent(frame,
                    EventMapper.toEventDto(equipmentId, dateEvent, nameEvent, descriptionEvent, fileId),
                    authorizationFrameController.getUser().getUserId());
        });
    }
}
