package get.a.big.head.newNRG.factorydocumentations;

import get.a.big.head.newNRG.events.EventDto;
import get.a.big.head.newNRG.files.DataFileClient;
import get.a.big.head.newNRG.files.DataFileDto;
import get.a.big.head.newNRG.type.TypeDto;
import get.a.big.head.newNRG.users.controllers.UserAuthorizationFrameController;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Controller
@Getter
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class FactoryDocumentationListFrameController {

    private FactoryDocumentationListFrame frame;
    private final FactoryDocumentationClient factoryDocumentationClient;
    private final UserAuthorizationFrameController authorizationFrameController;
    private final DataFileClient dataFileClient;
    private final int size = 15;
    private int maxSize;
    private int from;
    private int pages = maxSize / size + 1;
    private int maxShow = pages * size - 15;
    private Long typeId;
    private int page;
    private List<FactoryDocumentationDto> list;

    public void initFactoryDocumentationListFrameController(TypeDto type) {
        typeId = type.getTypeId();
        maxSize = type.getFactoryDocuments().size();
        from = 0;
        page = 1;
        openPage();

        frame.getButtonNext().addActionListener(e -> {
            from = from + 15;
            page = page + 1;
            openPage();
        });

        frame.getButtonPrevious().addActionListener(e -> {
            from = from - 15;
            page = page - 1;
            openPage();
        });

        frame.getFrame().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                frame = null;
            }
        });

        for (JButton button : frame.getOpenFileButtons()) {
            button.addActionListener(e -> {
                FactoryDocumentationDto factoryDocumentationDto = list.get(Integer.parseInt(button.getActionCommand()));
                DataFileDto dataFile = dataFileClient.getFile(
                        frame.getFrame(),
                        factoryDocumentationDto.getFileId(),
                        authorizationFrameController.getUser().getUserId()
                );
                if (Desktop.isDesktopSupported() && dataFile != null) {
                    try {

                        File file = new File(dataFile.getName());
                        try (OutputStream os = new FileOutputStream(file)) {
                            os.write(dataFile.getContent());
                        }
                        Desktop.getDesktop().open(file);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(
                                frame.getFrame(),
                                "Ошибка открытия файла",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                }
            });
        }

        for (JButton button : frame.getDeleteButtons()) {
            button.addActionListener(e -> {
                deleteDocument(Long.parseLong(button.getActionCommand()));
                openPage();
            });
        }
    }

    private void deleteDocument(Long documentId) {
        ResponseEntity<Object> deleteDocumentResponse = factoryDocumentationClient.deleteDocument(
                documentId,
                authorizationFrameController.getUser().getUserId()
        );
        if (deleteDocumentResponse.getStatusCode().is2xxSuccessful() && deleteDocumentResponse.getBody() != null) {
            frame.getFrame().dispose();
            JOptionPane.showMessageDialog(frame.getFrame(),"Документ удален");
        } else {
            JOptionPane.showMessageDialog(
                    frame.getFrame(),
                    deleteDocumentResponse.getStatusCode().toString(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private List<FactoryDocumentationDto> findAllDocuments(Long typeId, int from) {
        ResponseEntity<Object> documentListResponse = factoryDocumentationClient.findAllDocuments(
                typeId,
                from,
                size,
                authorizationFrameController.getUser().getUserId()
        );
        if (documentListResponse.getStatusCode().is2xxSuccessful() && documentListResponse.getBody() != null) {
            return FactoryDocumentationMapper.toDocumentDtos(documentListResponse.getBody());
        } else {
            JOptionPane.showMessageDialog(
                    frame.getFrame(),
                    documentListResponse.getStatusCode().toString(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        return null;
    }

    private void openPage() {
        list = findAllDocuments(typeId, from);
        if (frame != null) {
            frame.getFrame().dispose();
        }
        if (list != null) {
            if (maxSize <= size) {
                frame = new FactoryDocumentationListFrame(list, page, pages);
            } else if (from < size) {
                frame = new FactoryDocumentationListFrame(list, page, pages);
                frame.getPanelButtons().add(frame.getButtonNext());
            } else if (from > size && from < maxShow) {
                frame = new FactoryDocumentationListFrame(list, page, pages);
                frame.getPanelButtons().add(frame.getButtonPrevious());
                frame.getPanelButtons().add(frame.getButtonNext());
            } else if (from > size && from == maxShow) {
                frame = new FactoryDocumentationListFrame(list, page, pages);
                frame.getPanelButtons().add(frame.getButtonPrevious());
            }
        }
    }
}
