package get.newNRG.users.controllers;

import get.newNRG.users.UserClient;
import get.newNRG.users.frames.UserManagerFrame;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@Component
@Getter
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserManagerFrameController {

    private UserManagerFrame frame;
    private final UserClient userClient;
    private final UserAuthorizationFrameController authorizationFrameController;
    private final UserAccountFrameController accountFrameController;
    private final UserListFrameController listFrameController;
    private int size;

    public void initUserManagerFrameController() {
        size = userClient.findAllUsers(frame,
                "Roles",
                "Requested",
                0,
                15,
                authorizationFrameController.getUser().getUserId()).size();

        frame = new UserManagerFrame();

        if (size > 0) {
            frame.getPanelButtons().remove(frame.getButtonCancel());
            frame.getPanelButtons().add(frame.getButtonRequest());
            frame.getPanelButtons().add(frame.getButtonCancel());
        }

        frame.getFrame().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                frame = null;
            }
        });

        frame.getButtonFind().addActionListener(e -> {
            String userName = frame.getTextFieldFinder().getText();
            if (accountFrameController.getFrame() == null) {
                accountFrameController.initUserAccountFrameController(userClient.getUser(
                        frame,
                        userName,
                        authorizationFrameController.getUser().getUserId()
                ));
            } else {
                accountFrameController.getFrame().getFrame().toFront();
                accountFrameController.getFrame().getFrame().requestFocus();
            }
        });

        frame.getButtonAllUsers().addActionListener(e -> {
            if (frame.getRoleMenu().getSelectedItem() != null && frame.getStatusMenu().getSelectedItem() != null) {
                openListUsers(frame.getRoleMenu().getSelectedItem().toString(),
                        frame.getStatusMenu().getSelectedItem().toString());
            }
        });

        frame.getButtonCancel().addActionListener(e -> frame.getFrame().dispose());

        frame.getButtonRequest().addActionListener(e -> openListUsers("Roles", "Requested"));
    }

    private void openListUsers(String role, String status) {
        if (listFrameController.getFrame() == null) {
            listFrameController.initUserListFrameController(size, role, status);
        } else {
            listFrameController.getFrame().toFront();
            listFrameController.getFrame().requestFocus();
        }
    }
}