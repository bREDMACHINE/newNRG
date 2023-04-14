package get.a.big.head.newNRG.projectdocumentations;

import get.a.big.head.newNRG.files.DataFileClient;
import get.a.big.head.newNRG.files.DataFileDto;
import get.a.big.head.newNRG.files.DataFileMapper;
import get.a.big.head.newNRG.users.controllers.UserAuthorizationFrameController;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
@Getter
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AddProjectDocumentationFrameController {

    private AddProjectDocumentationFrame frame;
    private final ProjectDocumentationClient projectDocumentationClient;
    private final UserAuthorizationFrameController authorizationFrameController;
    private final DataFileClient dataFileClient;
    private File file = null;

    public void initAddProjectDocumentationFrameController(Long equipmentId) {
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
            DataFileDto dataFile = null;
            if (file != null) {
                dataFile = DataFileMapper.toDataFileDto(file);
            }

            log.info("Add project  with projectName {}, projectCode {}, project {}",
                    projectName, projectCode, dataFile);
            Long fileId = dataFileClient.addFile(frame, dataFile, authorizationFrameController.getUser().getUserId()).getFileId();
            List<Long> equipment = new ArrayList<>();
            equipment.add(equipmentId);
            ResponseEntity<Object> addProjectResponse = projectDocumentationClient.addProject(
                    ProjectDocumentationMapper.toProjectDto(projectName, projectCode, equipment, fileId),
                    authorizationFrameController.getUser().getUserId()
            );
            if (addProjectResponse.getStatusCode().is2xxSuccessful() && addProjectResponse.getBody() != null) {
                ProjectDocumentationDto project = ProjectDocumentationMapper.toProjectDto(addProjectResponse.getBody());
                frame.getFrame().dispose();
                JOptionPane.showMessageDialog(frame.getFrame(),
                        "Проект " + project.getNameProjectDocumentation() + " успешно добавлен");
            } else {
                JOptionPane.showMessageDialog(
                        frame.getFrame(),
                        addProjectResponse.getStatusCode().toString(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        frame.getButtonCancel().addActionListener(e -> frame.getFrame().dispose());
    }
}
