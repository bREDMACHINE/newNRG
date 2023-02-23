package get.a.big.head.newNRG.users.controllers;

import get.a.big.head.newNRG.users.UserClient;
import get.a.big.head.newNRG.users.UserMapper;
import get.a.big.head.newNRG.users.dtos.User;
import get.a.big.head.newNRG.users.frames.UserManagerFrame;
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
public class UserManagerFrameController {

    private UserManagerFrame frame;
    private final UserClient userClient;
    private final UserAuthorizationFrameController authorizationFrameController;
    private final UserAccountFrameController accountFrameController;
    private final UserListFrameController listFrameController;
    private boolean close = true;

    public void initUserManagerFrameController() {
        if (close) {
            frame = new UserManagerFrame();
            close = false;
        } else {
            frame.getFrame().toFront();
            frame.getFrame().requestFocus();
        }

        frame.getFrame().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                close = true;
            }
        });

        frame.getButtonFind().addActionListener(e -> {
            String userName = frame.getTextFieldFinder().getText();
            log.info("Get user userName {}", userName);
            ResponseEntity<Object> getUserAnswer = userClient.getUser(
                    userName,
                    authorizationFrameController.getUser().getUserId()
            );
            if (getUserAnswer.getStatusCode().is2xxSuccessful()) {
                User user = UserMapper.toUser(getUserAnswer.getBody(), userName);
                accountFrameController.initUserAccountFrameController(user);
            } else {
                JOptionPane.showMessageDialog(frame.getFrame(), getUserAnswer.getStatusCode(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.getButtonAllUsers().addActionListener(e -> {
            log.info("Get all users");
            ResponseEntity<Object> findAllUsersAnswer = userClient.findAllUsers(
                    authorizationFrameController.getUser().getUserId()
            );
            if (findAllUsersAnswer.getStatusCode().is2xxSuccessful()) {
                List<User> users = UserMapper.toUsers(findAllUsersAnswer.getBody());
                listFrameController.initUserListFrameController(users);
            } else {
                JOptionPane.showMessageDialog(frame.getFrame(), findAllUsersAnswer.getStatusCode(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.getButtonCancel().addActionListener(e -> frame.getFrame().dispose());
    }
}
