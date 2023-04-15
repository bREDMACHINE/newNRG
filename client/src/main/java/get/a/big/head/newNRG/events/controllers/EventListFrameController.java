package get.a.big.head.newNRG.events.controllers;

import get.a.big.head.newNRG.equipment.EquipmentDto;
import get.a.big.head.newNRG.events.EventClient;
import get.a.big.head.newNRG.events.EventDto;
import get.a.big.head.newNRG.events.frames.EventListFrame;
import get.a.big.head.newNRG.files.DataFileDto;
import get.a.big.head.newNRG.files.DataFileClient;
import get.a.big.head.newNRG.users.controllers.UserAuthorizationFrameController;
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

@Component
@Getter
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class EventListFrameController {

    private EventListFrame frame;
    private final EventClient eventClient;
    private final UserAuthorizationFrameController authorizationFrameController;
    private final DataFileClient dataFileClient;
    private final int size = 15;
    private int maxSize;
    private int from;
    private final int pages = maxSize / size + 1;
    private final int maxShow = pages * size - 15;
    private Long equipmentId;
    private int page;
    private List<EventDto> list;

    public void initEventListFrameController(EquipmentDto equipment) {
        equipmentId = equipment.getEquipmentId();
        maxSize = equipment.getEvents().size();
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
                EventDto eventDto = list.get(Integer.parseInt(button.getActionCommand()));
                DataFileDto dataFile = dataFileClient.getFile(
                        frame.getFrame(),
                        eventDto.getFileId(),
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
                eventClient.deleteEvent(frame,
                        Long.parseLong(button.getActionCommand()),
                        authorizationFrameController.getUser().getUserId());
                openPage();
            });
        }
    }

    private void openPage() {
        list = eventClient.findAllEvents(
                frame,
                equipmentId,
                from,
                size,
                authorizationFrameController.getUser().getUserId()
        );
        if (frame != null) {
            frame.getFrame().dispose();
        }
        if (list != null) {
            if (maxSize <= size) {
                frame = new EventListFrame(list, page, pages);
            } else if (from < size) {
                frame = new EventListFrame(list, page, pages);
                frame.getPanelButtons().add(frame.getButtonNext());
            } else if (from > size && from < maxShow) {
                frame = new EventListFrame(list, page, pages);
                frame.getPanelButtons().add(frame.getButtonPrevious());
                frame.getPanelButtons().add(frame.getButtonNext());
            } else if (from > size && from == maxShow) {
                frame = new EventListFrame(list, page, pages);
                frame.getPanelButtons().add(frame.getButtonPrevious());
            }
        }
    }
}
