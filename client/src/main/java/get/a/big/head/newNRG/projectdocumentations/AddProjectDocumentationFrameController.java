package get.a.big.head.newNRG.projectdocumentations;

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

@Controller
@Slf4j
@Getter
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AddProjectDocumentationFrameController {

    private AddProjectDocumentationFrame frame;
    private final ProjectDocumentationClient projectDocumentationClient;
    private final UserAuthorizationFrameController authorizationFrameController;

    public void initAddProjectDocumentationFrameController() {
        frame = new AddProjectDocumentationFrame();

        frame.getFrame().addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                frame = null;
            }
        });

        frame.getButtonOk().addActionListener(e -> {
            String projectName = frame.getTextNameProject().getText();
            String projectCode = frame.getTextCodeProject().getText();
            String projectFile = "Считанный файл";
            log.info("Add project  with projectName {}, projectCode {}, project {}",
                    projectName, projectCode, projectFile);

            ResponseEntity<Object> addProjectResponse = projectDocumentationClient.addProject(
                    ProjectDocumentationMapper.toProjectDto(projectName, projectCode, projectFile),
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
