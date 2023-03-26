package get.a.big.head.newNRG.events.controllers;

import get.a.big.head.newNRG.equipment.EquipmentDto;
import get.a.big.head.newNRG.events.EventClient;
import get.a.big.head.newNRG.events.EventDto;
import get.a.big.head.newNRG.events.EventMapper;
import get.a.big.head.newNRG.events.frames.EventListFrame;
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
import java.util.List;

@Controller
@Slf4j
@Getter
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class EventListFrameController {

    private EventListFrame frame;
    private final EventClient eventClient;
    private final UserAuthorizationFrameController authorizationFrameController;
    private final int size = 15;
    private int maxSize;
    private int from;
    private final int pages = maxSize / size + 1;
    private final int maxShow = pages * size - 15;
    private Long equipmentId;
    private int page;

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
                // открытие файла
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
        List<EventDto> list = findAllEvents(equipmentId, from);
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
