package get.a.big.head.newNRG.users.controllers;

import get.a.big.head.newNRG.users.UserClient;
import get.a.big.head.newNRG.users.UserMapper;
import get.a.big.head.newNRG.users.dtos.User;
import get.a.big.head.newNRG.users.dtos.UserDto;
import get.a.big.head.newNRG.users.frames.UserAuthorizationFrame;
import get.a.big.head.newNRG.users.frames.UserRegistrationFrame;
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
public class UserAuthorizationFrameController {

    private final UserClient userClient;
    private final UserRegistrationFrameController registrationFrameController;
    private UserAuthorizationFrame frame;
    private User user;

    public void initUserAuthorizationFrameController() {
        frame = new UserAuthorizationFrame();

        frame.getFrame().addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                if (registrationFrameController.getFrame() !=null) {
                    registrationFrameController.getFrame().getFrame().dispose();
                }
            }
        });

        frame.getButtonOk().addActionListener(e -> {
            String userLogin = frame.getTextFieldLogin().getText();
            String userPassword = String.valueOf(frame.getPasswordField().getPassword());
            log.info("Get user with userLogin {}, userPassword {}", userLogin, userPassword);
            ResponseEntity<Object> authorizationAnswer = userClient.userAuthorization(new UserDto(userLogin, userPassword));

            if (authorizationAnswer.getStatusCode().is2xxSuccessful()) {
                user = UserMapper.toUser(authorizationAnswer.getHeaders(), userLogin);
                frame.getFrame().dispose();
            } else {
                JOptionPane.showMessageDialog(frame.getFrame(), authorizationAnswer.getStatusCode().toString(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.getButtonRegistration().addActionListener(e -> {
            if (registrationFrameController.getFrame() == null) {
                registrationFrameController.initUserRegistrationController();
            } else {
                registrationFrameController.getFrame().getFrame().toFront();
                registrationFrameController.getFrame().getFrame().requestFocus();
            }
        });

        frame.getButtonCancel().addActionListener(e -> frame.getFrame().dispose());
    }

    public void logout() {
        log.info("Logout user={}", user);
        ResponseEntity<Object> logoutAnswer = userClient.logout(user.getUserId());
        if (logoutAnswer.getStatusCode().is2xxSuccessful()) {
            user = null;
            JOptionPane.showMessageDialog(frame.getFrame(), "Вы вышли из аккаунта");
        } else {
            JOptionPane.showMessageDialog(frame.getFrame(), logoutAnswer.getStatusCode().toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
