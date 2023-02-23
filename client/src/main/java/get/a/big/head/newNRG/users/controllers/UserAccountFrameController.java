package get.a.big.head.newNRG.users.controllers;

import get.a.big.head.newNRG.users.UserClient;
import get.a.big.head.newNRG.users.dtos.Role;
import get.a.big.head.newNRG.users.dtos.User;
import get.a.big.head.newNRG.users.frames.AdminAccountFrame;
import get.a.big.head.newNRG.users.frames.UserAccountFrame;
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

@Lazy
@Slf4j
@Controller
@Getter
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserAccountFrameController {

    private UserAccountFrame frame;
    private final UserAuthorizationFrameController authorizationFrameController;
    private final UserClient userClient;
    private boolean close = true;

    public void initUserAccountFrameController(final User user) {
        if (close) {
            if (authorizationFrameController.getUser().getRole().equals(Role.ADMIN.name())) {
                frame = new AdminAccountFrame(user);
            } else {
                frame = new UserAccountFrame(user);
            }
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

        frame.getButtonDeleteUser().addActionListener(e -> {
            log.info("Delete user {}", user);
            ResponseEntity<Object> deleteUserAnswer = userClient.deleteUser(user.getEmail(),
                    authorizationFrameController.getUser().getUserId());
            if (deleteUserAnswer.getStatusCode().is2xxSuccessful()) {
                JOptionPane.showMessageDialog(frame.getFrame(), "Пользователь удален");
            } else {
                JOptionPane.showMessageDialog(
                        frame.getFrame(),
                        deleteUserAnswer.getStatusCode().toString(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        frame.getButtonClose().addActionListener(e -> frame.getFrame().dispose());
    }
}
