package get.a.big.head.newNRG.events.controllers;

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

    public void initEventListFrameController(Long equipmentId, List<Long> events) {
        this.equipmentId = equipmentId;
        maxSize = events.size();
        maxShow = (from / size) * size + size;
        from = 0;
        openPage();

        frame.getButtonNext().addActionListener(e -> {
            from = from + 15;
            openPage();
        });

        frame.getButtonPrevious().addActionListener(e -> {
            from = from - 15;
            openPage();
        });

        frame.getFrame().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                frame = null;
            }
        });

        frame.getButtonClose().addActionListener(e -> frame.getFrame().dispose());

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
            if (from < size && maxSize <= maxShow) {
                // without buttons
            } else if (from < size && maxSize > maxShow) {
                // with next button
            } else if (from > size && maxSize > maxShow) {
                frame = new EventListFrame(EventMapper.toEventDtos(eventListResponse.getBody()), from);
            } else if (from > size && maxSize <= maxShow) {
                // with previous
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
