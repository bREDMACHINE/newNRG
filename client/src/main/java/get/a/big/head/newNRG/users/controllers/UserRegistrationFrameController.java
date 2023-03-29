package get.a.big.head.newNRG.users.controllers;

import get.a.big.head.newNRG.users.UserClient;
import get.a.big.head.newNRG.users.models.User;
import get.a.big.head.newNRG.users.dtos.UserDto;
import get.a.big.head.newNRG.users.UserMapper;
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
public class UserRegistrationFrameController {

    private final UserClient userClient;
    private UserRegistrationFrame frame;

    public void initUserRegistrationController() {
        frame = new UserRegistrationFrame();

        frame.getFrame().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                frame = null;
            }
        });

        frame.getButtonOk().addActionListener(e -> {
            String userLogin = frame.getTextFieldLogin().getText();
            String userPassword = String.valueOf(frame.getPasswordField().getPassword());
            log.info("Set userLogin {}, userPassword {}", userLogin, userPassword);
            userRegistration(userLogin, userPassword);
        });

        frame.getButtonCancel().addActionListener(e -> frame.getFrame().dispose());
    }

    public void userRegistration(String userLogin, String userPassword) {
        ResponseEntity<Object> registrationAnswer = userClient.userRegistration(new UserDto(userLogin, userPassword));

        if (registrationAnswer.getStatusCode().is2xxSuccessful()) {
            User user = UserMapper.toUser(registrationAnswer.getHeaders(), userLogin);
            frame.getFrame().dispose();
            JOptionPane.showMessageDialog(
                    frame.getFrame(),
                    "Пользователь " + user.getEmail() + " зарегистрирован с ролью " + user.getRole()
            );
        } else {
            JOptionPane.showMessageDialog(frame.getFrame(),
                    registrationAnswer.getStatusCode(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
