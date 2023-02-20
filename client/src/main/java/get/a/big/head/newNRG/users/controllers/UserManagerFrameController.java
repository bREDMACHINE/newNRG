package get.a.big.head.newNRG.users.controllers;

import get.a.big.head.newNRG.users.UserClient;
import get.a.big.head.newNRG.users.UserMapper;
import get.a.big.head.newNRG.users.dtos.User;
import get.a.big.head.newNRG.users.dtos.UserDto;
import get.a.big.head.newNRG.users.frames.UserManagerFrame;
import get.a.big.head.newNRG.users.frames.UserRegistrationFrame;
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
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserManagerFrameController {

    private UserManagerFrame frame;
    private final UserClient userClient;
    private final List<JFrame> windows = new ArrayList<>();

    public void initUserManagerFrame(String userId) {
        if (windows.size() == 0) {
            frame = new UserManagerFrame();
            windows.add(frame);
        } else {
            frame.getFrame().toFront();
            frame.getFrame().requestFocus();
        }

        frame.getFrame().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                windows.clear();
            }
        });

        frame.getButtonOk().addActionListener(e -> {
            String userName = frame.getTextFieldFinder().getText();
            log.info("Get user userName {}", userName);
            ResponseEntity<Object> getUserAnswer = userClient.getUser(userName, userId);
            if (getUserAnswer.getStatusCode().is2xxSuccessful()) {
                User user = UserMapper.toUser(getUserAnswer.getBody(), userName);
                frame.getFrame().dispose();
            } else {
                JOptionPane.showMessageDialog(frame.getFrame(), getUserAnswer.getStatusCode(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.getButtonCancel().addActionListener(e -> frame.getFrame().dispose());
    }

    public UserManagerFrame getFrame() {
        return frame;
    }
}
