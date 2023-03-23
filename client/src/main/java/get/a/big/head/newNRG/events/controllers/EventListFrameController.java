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
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

@Lazy
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
    private int maxShow;
    private int from;
    private Long equipmentId;
    private int page;
    private int pages;

    public void initEventListFrameController(EquipmentDto equipment) {
        this.equipmentId = equipment.getEquipmentId();
        maxSize = equipment.getEvents().size();
        maxShow = (from / size) * size + size;
        from = 0;
        page = 1;
        pages = maxSize / size;
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
//                EventDto event = events.get(Integer.parseInt(button.getActionCommand()));
//                System.out.println(event.getNameEvent());
            });
        }
    }

    private void openPage() {
        ResponseEntity<Object> eventListResponse = eventClient.findAllEvents(
                equipmentId,
                from,
                size,
                authorizationFrameController.getUser().getUserId()
        );
        if (eventListResponse.getStatusCode().is2xxSuccessful() && eventListResponse.getBody() != null) {
            if (frame != null) {
                frame.getFrame().dispose();
            }
            List<EventDto> events = EventMapper.toEventDtos(eventListResponse);
            if (from < size && maxSize <= maxShow) {
                frame = new EventListFrame(events, page, pages);
                frame.getPanelButtons().remove(frame.getButtonNext());
                frame.getPanelButtons().remove(frame.getButtonPrevious());
            } else if (from < size && maxSize > maxShow) {
                frame = new EventListFrame(events, page, pages);
                frame.getPanelButtons().remove(frame.getButtonNext());
                frame.getPanelButtons().remove(frame.getButtonPrevious());
                frame.getPanelButtons().add(frame.getButtonNext());
            } else if (from > size && maxSize > maxShow) {
                frame = new EventListFrame(events, page, pages);
                frame.getPanelButtons().remove(frame.getButtonNext());
                frame.getPanelButtons().remove(frame.getButtonPrevious());
                frame.getPanelButtons().add(frame.getButtonPrevious());
                frame.getPanelButtons().add(frame.getButtonNext());
            } else if (from > size && maxSize <= maxShow) {
                frame = new EventListFrame(events, page, pages);
                frame.getPanelButtons().remove(frame.getButtonNext());
                frame.getPanelButtons().remove(frame.getButtonPrevious());
                frame.getPanelButtons().add(frame.getButtonPrevious());
            }
        } else {
            JOptionPane.showMessageDialog(
                    frame.getFrame(),
                    eventListResponse.getStatusCode().toString(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
