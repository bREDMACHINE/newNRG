package get.a.big.head.newNRG.users.controllers;

import get.a.big.head.newNRG.users.UserClient;
import get.a.big.head.newNRG.users.dtos.User;
import get.a.big.head.newNRG.users.dtos.UserDto;
import get.a.big.head.newNRG.users.UserMapper;
import get.a.big.head.newNRG.users.frames.UserRegistrationFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@Lazy
@Controller
@Slf4j
public class UserRegistrationFrameController {

    private final UserClient userClient;
    private UserRegistrationFrame frame;

    @Autowired
    public UserRegistrationFrameController(UserClient userClient) {
        this.userClient = userClient;
    }

    public void UserRegistration() {
        frame = new UserRegistrationFrame();
        frame.getFrame().addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowLostFocus(WindowEvent e) {
                e.getWindow().toFront();
                e.getWindow().requestFocus();
            }
        });

        frame.getButton().addActionListener(e -> {
            String userLogin = frame.getTextFieldLogin().getText();
            String userPassword = String.valueOf(frame.getPasswordField().getPassword());
            log.info("Get userLogin {}, userPassword {}", userLogin, userPassword);
            UserDto userDto = UserMapper.toUserDto(userLogin, userPassword);
            ResponseEntity<Object> registrationAnswer = userClient.userRegistration(userDto);
            if (registrationAnswer.getStatusCode().is2xxSuccessful()) {
                User user = UserMapper.toUserShortDto(registrationAnswer.getBody(), userLogin);
                frame.getFrame().dispose();
                JOptionPane.showMessageDialog(frame.getFrame(), "Пользователь " + user.getEmail() + " успешно зарегистрирован");
            } else {
                JOptionPane.showMessageDialog(frame.getFrame(), registrationAnswer.getBody(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
