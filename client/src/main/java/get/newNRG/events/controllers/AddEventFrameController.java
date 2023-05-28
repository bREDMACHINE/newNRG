package get.newNRG.events.controllers;

import get.newNRG.events.EventClient;
import get.newNRG.events.EventMapper;
import get.newNRG.files.DataFileCreatorFrameController;
import get.newNRG.files.DataFileDto;
import get.newNRG.files.DataFileClient;
import get.newNRG.files.DataFileMapper;
import get.newNRG.events.AddEventFrame;
import get.newNRG.general.AddCardFromCardFrameController;
import get.newNRG.general.ControllerUtil;
import get.newNRG.users.controllers.UserAuthorizationFrameController;
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
public class AddEventFrameController implements AddCardFromCardFrameController {

    private AddEventFrame frame;
    private final EventClient eventClient;
    private final DataFileClient dataFileClient;
    private final DataFileCreatorFrameController dataFileCreatorFrameController;
    private final UserAuthorizationFrameController authorizationFrameController;
    private File file = null;

    @Override
    public void initAddCardFromCardFrameController(Long equipmentId) {
        frame = new AddEventFrame();

        frame.getFrame().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                frame = null;
            }
        });

        frame.getButtonCancel().addActionListener(e -> frame.getFrame().dispose());

        frame.getButtonFileChooser().addActionListener(e -> {
            int result = frame.getFileChooser().showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION ) {
                file = frame.getFileChooser().getSelectedFile();
                JOptionPane.showMessageDialog(frame,
                        "Файл " + frame.getFileChooser().getSelectedFile() + " выбран");
            }
        });

        frame.getButtonFileCreator().addActionListener(e -> ControllerUtil.start(dataFileCreatorFrameController));

        frame.getButtonOk().addActionListener(e -> {
            String dateEvent = frame.getTextEventDate().getText();
            String nameEvent = frame.getTextEventName().getText();
            String descriptionEvent = frame.getTextDescription().getText();
            DataFileDto dataFileDto = null;
            if (file != null) {
                dataFileDto = dataFileClient.addFile(
                        frame,
                        DataFileMapper.toDataFileDto(file),
                        authorizationFrameController.getUser().getUserId()
                );
            }
            eventClient.addEvent(
                    frame,
                    EventMapper.toEventDto(equipmentId,
                            dateEvent,
                            nameEvent,
                            descriptionEvent,
                            dataFileDto != null ? dataFileDto.getFileId() : null),
                    authorizationFrameController.getUser().getUserId()
            );
        });
    }
}
