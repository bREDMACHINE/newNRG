package get.newNRG.files;

import get.newNRG.users.controllers.UserAuthorizationFrameController;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

@Getter
@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class DataFileCreatorFrameController {

    private DataFileCreatorFrame frame;
    private final DataFileClient dataFileClient;
    private final UserAuthorizationFrameController authorizationFrameController;
    private File file = null;

    public void initDataFileCreatorFrameController() {
        frame = new DataFileCreatorFrame();

        frame.getFrame().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                frame = null;
            }
        });

        frame.getButtonCancel().addActionListener(e -> frame.getFrame().dispose());

        frame.getButtonSave().addActionListener(e -> {
            int result = frame.getFileChooser().showSaveDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION ) {
                file = frame.getFileChooser().getSelectedFile();
            }
            String textField = frame.getTextField().getText();
            String textArea = frame.getTextArea().getText();
            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream contentStream;
            try {
                contentStream = new PDPageContentStream(document, page);
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.COURIER), 12);
                contentStream.beginText();
                contentStream.showText(textField);
                contentStream.showText(textArea);
                contentStream.endText();
                contentStream.close();

                document.save(file);
                document.close();
                JOptionPane.showMessageDialog(frame,"Файл " + frame.getFileChooser().getSelectedFile() + " сохранен");
                frame.getFrame().dispose();

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}
