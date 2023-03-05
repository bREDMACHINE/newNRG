package get.a.big.head.newNRG.events.controllers;

import get.a.big.head.newNRG.events.Event;
import get.a.big.head.newNRG.events.frames.EventListFrame;
import lombok.Getter;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

@Lazy
@Controller
@Getter
public class EventListFrameController {

    private EventListFrame frame;

    public void initEventListFrameController(List<Event> events) {

        int size = 15;
        if (events.size() < size) {
            size = events.size();
        }
        List<Event> page = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            page.add(events.get(i));
        }
        frame = new EventListFrame(page);

        frame.getFrame().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                frame = null;
            }
        });

        frame.getButtonClose().addActionListener(e -> frame.getFrame().dispose());

        for (JButton button : frame.getButtons()) {
            button.addActionListener(e -> {
                Event event = events.get(Integer.parseInt(button.getActionCommand()));
                System.out.println(event.getNameEvent());
            });
        }

        frame.getNext().addActionListener(e -> {
            frame.getFrame().dispose();
            frame = new EventListFrame(page);
        });
    }
}
