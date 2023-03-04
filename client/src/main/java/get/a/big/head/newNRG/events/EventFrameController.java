package get.a.big.head.newNRG.events;

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
import java.util.ArrayList;
import java.util.List;

@Lazy
@Controller
@Slf4j
@Getter
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class EventFrameController {

    private EventFrame frame;
    private final EventClient eventClient;

    public void initEventController(Event event) {
        frame = new EventFrame(event);

        frame.getFrame().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                frame = null;
            }
        });

        frame.getButtonCancel().addActionListener(e -> frame.getFrame().dispose());
    }

    public Event getEvent(String userId) {
        ResponseEntity<Object> eventResponse = eventClient.getEvent(userId);
        if (eventResponse.getStatusCode().is2xxSuccessful() && eventResponse.getBody() != null) {
            return EventMapper.toEvent(eventResponse.getBody());
        } else {
            JOptionPane.showMessageDialog(frame.getFrame(), eventResponse.getStatusCode().toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
}
