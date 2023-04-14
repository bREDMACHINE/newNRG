package get.a.big.head.newNRG.factorydocumentations;

import get.a.big.head.newNRG.files.DataFileClient;
import get.a.big.head.newNRG.files.DataFileDto;
import get.a.big.head.newNRG.files.DataFileMapper;
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
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@Getter
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AddFactoryDocumentationFrameController {

    private AddFactoryDocumentationFrame frame;
    private final FactoryDocumentationClient factoryDocumentationClient;
    private final UserAuthorizationFrameController authorizationFrameController;
    private final DataFileClient dataFileClient;
    private File file = null;

    public void initAddFactoryDocumentationFrameController(Long typeId) {
        frame = new AddFactoryDocumentationFrame();

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
            String documentName = frame.getTextNameDocument().getText();
            String documentCode = frame.getTextCodeDocument().getText();
            DataFileDto dataFile = null;
            if (file != null) {
                dataFile = DataFileMapper.toDataFileDto(file);
            }

            log.info("Add document  with documentName {}, documentCode {}, document {}",
                    documentName, documentCode, dataFile);
            Long fileId = dataFileClient.addFile(frame, dataFile, authorizationFrameController.getUser().getUserId()).getFileId();
            List<Long> types = new ArrayList<>();
            types.add(typeId);
            ResponseEntity<Object> addDocumentResponse = factoryDocumentationClient.addDocument(
                    FactoryDocumentationMapper.toDocumentDto(documentName, documentCode, types, fileId),
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
