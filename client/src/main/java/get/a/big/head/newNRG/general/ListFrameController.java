package get.a.big.head.newNRG.general;

import get.a.big.head.newNRG.files.DataFileClient;
import get.a.big.head.newNRG.files.DataFileDto;
import get.a.big.head.newNRG.users.controllers.UserAuthorizationFrameController;
import lombok.Getter;

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
public class ListFrameController {

    private ListFrame frame;
    private final Client client;
    private final UserAuthorizationFrameController authorizationFrameController;
    private final DataFileClient dataFileClient;
    private final int size;
    private final int maxSize;
    private int from;
    private final int pages;
    private final int maxShow;
    private final Long parentObjectId;
    private int page;
    private List<? extends WithFile> list;
    private final List<String> labels;

    public ListFrameController(Client client,
                               UserAuthorizationFrameController authorizationFrameController,
                               DataFileClient dataFileClient,
                               int maxSize,
                               List<String> labels,
                               Long parentObjectId) {
        this.client = client;
        this.authorizationFrameController = authorizationFrameController;
        this.dataFileClient = dataFileClient;
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
                frame = new ListFrame(list, labels, page, pages);
                functionsFrame();
            } else if (from < size) {
                frame = new ListFrame(list, labels, page, pages);
                frame.getPanelButtons().add(frame.getButtonNext());
                functionsFrame();
            } else if (from > 14 && from < maxShow) {
                frame = new ListFrame(list, labels, page, pages);
                frame.getPanelButtons().add(frame.getButtonPrevious());
                frame.getPanelButtons().add(frame.getButtonNext());
                functionsFrame();
            } else if (from > 14 && from == maxShow) {
                frame = new ListFrame(list, labels, page, pages);
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
