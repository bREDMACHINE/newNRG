package get.a.big.head.newNRG.users.controllers;

import get.a.big.head.newNRG.users.UserClient;
import get.a.big.head.newNRG.users.UserMapper;
import get.a.big.head.newNRG.users.models.User;
import get.a.big.head.newNRG.users.frames.UserManagerFrame;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
            if (accountFrameController.getFrame() == null) {
                accountFrameController.initUserAccountFrameController(getUser(userName));
            } else {
                accountFrameController.getFrame().getFrame().toFront();
                accountFrameController.getFrame().getFrame().requestFocus();
            }
        });

        frame.getButtonAllUsers().addActionListener(e -> {
            openListUsers(frame.getRoleMenu().getSelectedItem().toString(),
                    frame.getStatusMenu().getSelectedItem().toString());
        });

        frame.getButtonCancel().addActionListener(e -> frame.getFrame().dispose());

        if (listFrameController.findAllUsers("Roles", "Requested", 0).size() > 0) {
            frame.getPanelButtons().remove(frame.getButtonCancel());
            frame.getPanelButtons().add(frame.getButtonRequest());
            frame.getPanelButtons().add(frame.getButtonCancel());
        }

        frame.getButtonRequest().addActionListener(e -> openListUsers("Roles", "Requested"));
    }

    private void openListUsers(String role, String status) {
        if (listFrameController.getFrame() == null) {
            listFrameController.initUserListFrameController(role, status);
        } else {
            listFrameController.getFrame().getFrame().toFront();
            listFrameController.getFrame().getFrame().requestFocus();
        }
    }

    private User getUser(String userName) {
        ResponseEntity<Object> getUserAnswer = userClient.getUser(
                userName,
                authorizationFrameController.getUser().getUserId()
        );
        if (getUserAnswer.getStatusCode().is2xxSuccessful() && getUserAnswer.getBody() != null) {
            return UserMapper.toUser(getUserAnswer.getBody());

        } else {
            JOptionPane.showMessageDialog(frame.getFrame(),
                    getUserAnswer.getStatusCode(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return  null;
    }
}
