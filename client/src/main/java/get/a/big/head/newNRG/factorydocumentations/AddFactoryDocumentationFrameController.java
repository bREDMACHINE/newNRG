package get.a.big.head.newNRG.factorydocumentations;

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
public class AddFactoryDocumentationFrameController {

    private AddFactoryDocumentationFrame frame;
    private final FactoryDocumentationClient factoryDocumentationClient;
    private final UserAuthorizationFrameController authorizationFrameController;

    public void initAddFactoryDocumentationFrameController() {
        frame = new AddFactoryDocumentationFrame();

        frame.getFrame().addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                frame = null;
            }
        });

        frame.getButtonOk().addActionListener(e -> {
            String documentName = frame.getTextNameDocument().getText();
            String documentCode = frame.getTextCodeDocument().getText();
            String documentFile = "Считанный файл";
            log.info("Add document  with documentName {}, documentCode {}, document {}",
                    documentName, documentCode, documentFile);

            ResponseEntity<Object> addDocumentResponse = factoryDocumentationClient.addDocument(
                    FactoryDocumentationMapper.toDocumentDto(documentName, documentCode, documentFile),
                    authorizationFrameController.getUser().getUserId()
            );
            if (addDocumentResponse.getStatusCode().is2xxSuccessful() && addDocumentResponse.getBody() != null) {
                FactoryDocumentationDto document = FactoryDocumentationMapper.toDocumentDto(addDocumentResponse.getBody());
                frame.getFrame().dispose();
                JOptionPane.showMessageDialog(frame.getFrame(),
                        "Документ " + document.getNameFactoryDocumentation() + " успешно добавлен");
            } else {
                JOptionPane.showMessageDialog(
                        frame.getFrame(),
                        addDocumentResponse.getStatusCode().toString(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        frame.getButtonCancel().addActionListener(e -> frame.getFrame().dispose());
    }
}
