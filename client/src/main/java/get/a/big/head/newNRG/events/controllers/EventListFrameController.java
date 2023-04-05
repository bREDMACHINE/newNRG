package get.a.big.head.newNRG.events.controllers;

import get.a.big.head.newNRG.equipment.EquipmentDto;
import get.a.big.head.newNRG.events.EventClient;
import get.a.big.head.newNRG.events.EventDto;
import get.a.big.head.newNRG.events.EventMapper;
import get.a.big.head.newNRG.events.frames.EventListFrame;
import get.a.big.head.newNRG.files.DataFile;
import get.a.big.head.newNRG.files.FileClient;
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
public class EventListFrameController {

    private EventListFrame frame;
    private final EventClient eventClient;
    private final UserAuthorizationFrameController authorizationFrameController;
    private final FileClient fileClient;
    private final int size = 15;
    private int maxSize;
    private int from;
    private final int pages = maxSize / size + 1;
    private final int maxShow = pages * size - 15;
    private Long equipmentId;
    private int page;
    List<EventDto> list;

    public void initEventListFrameController(EquipmentDto equipment) {
        this.equipmentId = equipment.getEquipmentId();
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
                DataFile dataFile = fileClient.getFile(
                        frame.getFrame(),
                        eventDto.getFileId(),
                        authorizationFrameController.getUser().getUserId()
                );
                if (Desktop.isDesktopSupported() && dataFile != null) {
                    String ext = "";
                    String contentType = dataFile.getContentType();
                    if (contentType.equalsIgnoreCase("image/gif")) {
                        ext = ".gif";
                    } else if (contentType.equalsIgnoreCase("image/jpeg")) {
                        ext = ".jpg";
                    }else if (contentType.equalsIgnoreCase("image/png")) {
                        ext = ".png";
                    } else if (contentType.equalsIgnoreCase("image/bmp")) {
                        ext = ".bmp";
                    } else if (contentType.equalsIgnoreCase("application/pdf")
                            || (contentType.equalsIgnoreCase("application/x-pdf"))) {
                        ext = ".pdf";
                    }
                    try {

                        File file = new File(dataFile.getName() + ext);
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
                deleteEvent(Long.parseLong(button.getActionCommand()));
                openPage();
            });
        }
    }

    private void deleteEvent(Long eventId) {
        ResponseEntity<Object> deleteEventResponse = eventClient.deleteEvent(
                eventId,
                authorizationFrameController.getUser().getUserId()
        );
        if (deleteEventResponse.getStatusCode().is2xxSuccessful() && deleteEventResponse.getBody() != null) {
            frame.getFrame().dispose();
            JOptionPane.showMessageDialog(frame.getFrame(),"Событие удалено");
        } else {
            JOptionPane.showMessageDialog(
                    frame.getFrame(),
                    deleteEventResponse.getStatusCode().toString(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private List<EventDto> findAllEvents(Long equipmentId, int from) {
        ResponseEntity<Object> eventListResponse = eventClient.findAllEvents(
                equipmentId,
                from,
                size,
                authorizationFrameController.getUser().getUserId()
        );
        if (eventListResponse.getStatusCode().is2xxSuccessful() && eventListResponse.getBody() != null) {
            return EventMapper.toEventDtos(eventListResponse.getBody());
        } else {
            JOptionPane.showMessageDialog(
                    frame.getFrame(),
                    eventListResponse.getStatusCode().toString(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        return null;
    }

    private void openPage() {
        list = findAllEvents(equipmentId, from);
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
