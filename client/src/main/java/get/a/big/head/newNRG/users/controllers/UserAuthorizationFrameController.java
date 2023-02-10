package get.a.big.head.newNRG.users.controllers;

import get.a.big.head.newNRG.general.AdminMainFrameController;
import get.a.big.head.newNRG.general.ModeratorMainFrameController;
import get.a.big.head.newNRG.general.UserMainFrameController;
import get.a.big.head.newNRG.users.UserClient;
import get.a.big.head.newNRG.users.UserMapper;
import get.a.big.head.newNRG.users.dtos.Role;
import get.a.big.head.newNRG.users.dtos.User;
import get.a.big.head.newNRG.users.dtos.UserDto;
import get.a.big.head.newNRG.users.frames.UserAuthorizationFrame;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserAuthorizationFrameController {

    private final UserClient userClient;
    private final UserRegistrationFrameController registrationFrameController;
    private final UserMainFrameController userMainFrameController;
    private final ModeratorMainFrameController moderatorMainFrameController;
    private final AdminMainFrameController adminMainFrameController;
    private UserAuthorizationFrame frame;
    private final List<JFrame> windows = new ArrayList<>();
    private User user;

    public void UserAuthorization() {
        if (windows.size() == 0) {
            frame = new UserAuthorizationFrame();
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
            String userLogin = frame.getTextFieldLogin().getText();
            String userPassword = String.valueOf(frame.getPasswordField().getPassword());
            log.info("Get user with userLogin {}, userPassword {}", userLogin, userPassword);
            UserDto userDto = UserMapper.toUserDto(userLogin, userPassword);
            ResponseEntity<Object> authorizationAnswer = userClient.userAuthorization(userDto);

            if (authorizationAnswer.getStatusCode().is2xxSuccessful()) {
                user = UserMapper.toUser(authorizationAnswer.getBody(), userLogin);
                frame.getFrame().dispose();
                windows.clear();
                JOptionPane.showMessageDialog(frame.getFrame(), "Пользователь " + user.getEmail() + " успешно авторизован");
                if (user.getRole().equals(Role.USER.name())) {
                    userMainFrameController.initControllerFrame();
                } else if (user.getRole().equals(Role.MODERATOR.name())) {
                    moderatorMainFrameController.initControllerFrame();
                } else if (user.getRole().equals(Role.ADMIN.name())) {
                    adminMainFrameController.initControllerFrame();
                } else {
                    JOptionPane.showMessageDialog(frame.getFrame(), "Ваша роль не определена, пройдите авторизацию повторно", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(frame.getFrame(), authorizationAnswer.getStatusCode().toString(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.getButtonRegistration().addActionListener(e -> registrationFrameController.UserRegistration());

        frame.getButtonCancel().addActionListener(e -> {
            frame.getFrame().dispose();
            windows.clear();
        });
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

    public User getUser() {
        return user;
    }
}
