package get.newNRG.factorydocumentations.controllers;

import get.newNRG.factorydocumentations.AddFactoryDocumentationFrame;
import get.newNRG.factorydocumentations.FactoryDocumentationClient;
import get.newNRG.factorydocumentations.FactoryDocumentationMapper;
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
import java.util.Collections;
import java.util.List;

@Getter
@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AddFactoryDocumentationFrameController implements AddCardFromCardFrameController {

    private AddFactoryDocumentationFrame frame;
    private final FactoryDocumentationClient factoryDocumentationClient;
    private final UserAuthorizationFrameController authorizationFrameController;
    private final DataFileClient dataFileClient;
    private File file = null;

    @Override
    public void initAddCardFromCardFrameController(Long typeId) {
        frame = new AddFactoryDocumentationFrame();

        frame.getFrame().addWindowListener(new WindowAdapter() {
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
            String documentName = frame.getTextNameDocument().getText();
            String documentCode = frame.getTextCodeDocument().getText();
            DataFileDto dataFile = null;
            if (file != null) {
                dataFile = DataFileMapper.toDataFileDto(file);
            }

            DataFileDto dataFileDto = dataFileClient.addFile(
                    frame, dataFile, authorizationFrameController.getUser().getUserId()
            );
            List<Long> types = Collections.singletonList(typeId);
            factoryDocumentationClient.addDocument(
                    frame,
                    FactoryDocumentationMapper.toDocumentDto(documentName, documentCode, types, dataFileDto.getFileId()),
                    authorizationFrameController.getUser().getUserId()
            );
        });
    }
}
