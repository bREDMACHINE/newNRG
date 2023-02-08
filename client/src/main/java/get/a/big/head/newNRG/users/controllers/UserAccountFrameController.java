package get.a.big.head.newNRG.users.controllers;

import get.a.big.head.newNRG.users.frames.UserAccountFrame;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

@Lazy
@Controller
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserAccountFrameController {

    private UserAccountFrame frame;
    private final List<JFrame> windows = new ArrayList<>();
    public void UserAccount() {
        if (windows.size() == 0) {
            frame = new UserAccountFrame();
            windows.add(frame);
        } else {
            frame.getFrame().toFront();
            frame.getFrame().requestFocus();
        }

        frame.getFrame().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                windows.clear();
            }
        });

        frame.getButtonOk().addActionListener(e -> {
            log.info("Get userLogin , userPassword ");
        });

        frame.getButtonCancel().addActionListener(e -> frame.getFrame().dispose());
    }
}
