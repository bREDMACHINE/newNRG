package get.newNRG.general;

import get.newNRG.files.DataFileClient;
import get.newNRG.files.DataFileDto;
import get.newNRG.users.controllers.UserAuthorizationFrameController;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Getter
@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ListFrameControllerWithFile {

    private ListFrameWithFile frame;
    private ClientForListWithFile client;
    private final UserAuthorizationFrameController authorizationFrameController;
    private final DataFileClient dataFileClient;
    private int size;
    private int maxSize;
    private int from;
    private int pages;
    private int maxShow;
    private Long parentObjectId;
    private int page;
    private List<? extends WithFile> list;
    private List<String> labels;

    public void initListFrameController(ClientForListWithFile client, int maxSize, List<String> labels, Long parentObjectId) {
        this.client = client;
        this.maxSize = maxSize;
        this.labels = labels;
        this.parentObjectId = parentObjectId;
        size = 15;
        pages = maxSize / size + 1;
        maxShow = pages * size - 15;
        from = 0;
        page = 1;
        openPage();
    }

    private void openPage() {
        list = client.findAll(
                frame,
                parentObjectId,
                from,
                size,
                authorizationFrameController.getUser().getUserId()
        );
        if (list != null) {
            if (maxSize <= size) {
                frame = new ListFrameWithFile(list, labels, page, pages);
                functionsFrame();
            } else if (from < size) {
                frame = new ListFrameWithFile(list, labels, page, pages);
                frame.getPanelButtons().add(frame.getButtonNext());
                functionsFrame();
            } else if (from > 14 && from < maxShow) {
                frame = new ListFrameWithFile(list, labels, page, pages);
                frame.getPanelButtons().add(frame.getButtonPrevious());
                frame.getPanelButtons().add(frame.getButtonNext());
                functionsFrame();
            } else if (from > 14 && from == maxShow) {
                frame = new ListFrameWithFile(list, labels, page, pages);
                frame.getPanelButtons().add(frame.getButtonPrevious());
                functionsFrame();
            }
        }
    }

    private void functionsFrame() {
        frame.getButtonNext().addActionListener(e -> {
            if (frame.getFrame() != null) {
                frame.getFrame().dispose();
            }
            from = from + 15;
            page = page + 1;
            openPage();
        });

        frame.getButtonPrevious().addActionListener(e -> {
            if (frame.getFrame() != null) {
                frame.getFrame().dispose();
            }
            from = from - 15;
            page = page - 1;
            openPage();
        });

        frame.getFrame().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame = null;
            }
        });

        for (JButton button : frame.getOpenFileButtons()) {
            button.addActionListener(e -> {
                DataFileDto dataFile = dataFileClient.getFile(
                        frame.getFrame(),
                        list.get(Integer.parseInt(button.getActionCommand())).getFileId(),
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
                if (frame.getFrame() != null) {
                    frame.getFrame().dispose();
                }
                client.delete(frame,
                        list.get(Integer.parseInt(button.getActionCommand())).getId(),
                        authorizationFrameController.getUser().getUserId());
                openPage();
            });
        }
    }
}
