package get.newNRG.projectdocumentations;

import get.newNRG.files.DataFileClient;
import get.newNRG.files.DataFileDto;
import get.newNRG.files.DataFileMapper;
import get.newNRG.general.AddCardFromCardFrameController;
import get.newNRG.users.controllers.UserAuthorizationFrameController;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.List;

@Component
@Getter
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AddProjectDocumentationFrameController implements AddCardFromCardFrameController {

    private AddProjectDocumentationFrame frame;
    private final ProjectDocumentationClient projectDocumentationClient;
    private final UserAuthorizationFrameController authorizationFrameController;
    private final DataFileClient dataFileClient;
    private File file = null;

    @Override
    public void initAddCardFromCardFrameController(Long equipmentId) {
        frame = new AddProjectDocumentationFrame();

        frame.getFrame().addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                frame = null;
            }
        });

        frame.getButtonFile().addActionListener(e -> {
            int result = frame.getFileChooser().showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION ) {
                file = frame.getFileChooser().getSelectedFile();
                JOptionPane.showMessageDialog(frame,
                        "Файл " + frame.getFileChooser().getSelectedFile() + " выбран");
            }
        });

        frame.getButtonOk().addActionListener(e -> {
            String projectName = frame.getTextNameProject().getText();
            String projectCode = frame.getTextCodeProject().getText();
            DataFileDto dataFileDto = null;
            if (file != null) {
                dataFileDto = dataFileClient.addFile(
                        frame,
                        DataFileMapper.toDataFileDto(file),
                        authorizationFrameController.getUser().getUserId()
                );
            }
            projectDocumentationClient.addProject(
                    frame,
                    ProjectDocumentationMapper.toProjectDto(projectName,
                            projectCode,
                            List.of(equipmentId),
                            dataFileDto != null ? dataFileDto.getFileId() : null),
                    authorizationFrameController.getUser().getUserId()
            );
        });

        frame.getButtonCancel().addActionListener(e -> frame.getFrame().dispose());
    }
}
