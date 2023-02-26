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

    public void initUserManagerFrameController() {
        frame = new UserManagerFrame();

        frame.getFrame().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                frame = null;
            }
        });

        frame.getButtonFind().addActionListener(e -> {
            String userName = frame.getTextFieldFinder().getText();
            log.info("Get user userName {}", userName);
            ResponseEntity<Object> getUserAnswer = userClient.getUser(
                    userName,
                    authorizationFrameController.getUser().getUserId()
            );
            if (getUserAnswer.getStatusCode().is2xxSuccessful() && getUserAnswer.getBody() != null) {
                User user = UserMapper.toUser(getUserAnswer.getBody());
                if (accountFrameController.getFrame() == null) {
                    accountFrameController.initUserAccountFrameController(user);
                } else {
                    accountFrameController.getFrame().getFrame().toFront();
                    accountFrameController.getFrame().getFrame().requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(frame.getFrame(), getUserAnswer.getStatusCode(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.getButtonAllUsers().addActionListener(e -> {
            ResponseEntity<Object> findAllUsersAnswer = userClient.findAllUsers(
                    frame.getRoleMenu().getSelectedItem().toString(),
                    frame.getStatusMenu().getSelectedItem().toString(),
                    authorizationFrameController.getUser().getUserId()
            );
            openListUsers(findAllUsersAnswer);
        });

        frame.getButtonCancel().addActionListener(e -> frame.getFrame().dispose());

        ResponseEntity<Object> findRequestRoleAnswer = findRequest();
        if (findRequestRoleAnswer.getStatusCode().is2xxSuccessful() && findRequestRoleAnswer.getBody() != null) {
            frame.getPanelButtons().remove(frame.getButtonCancel());
            frame.getPanelButtons().add(frame.getButtonRequest());
            frame.getPanelButtons().add(frame.getButtonCancel());
        }

        frame.getButtonRequest().addActionListener(e -> openListUsers(findRequest()));
    }

    private ResponseEntity<Object> findRequest() {
        return userClient.findAllUsers(
                "Roles",
                "Requested",
                authorizationFrameController.getUser().getUserId()
        );
    }

    private void openListUsers(ResponseEntity<Object> response) {
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            List<User> users = UserMapper.toUsers(response.getBody());
            if (listFrameController.getFrame() == null) {
                listFrameController.initUserListFrameController(users);
            } else {
                listFrameController.getFrame().getFrame().toFront();
                listFrameController.getFrame().getFrame().requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(frame.getFrame(), response.getStatusCode(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
