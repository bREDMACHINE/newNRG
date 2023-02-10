package get.a.big.head.newNRG.users.controllers;

import get.a.big.head.newNRG.users.UserClient;
import get.a.big.head.newNRG.users.dtos.User;
import get.a.big.head.newNRG.users.dtos.UserDto;
import get.a.big.head.newNRG.users.UserMapper;
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
public class UserRegistrationFrameController {

    private final UserClient userClient;
    private UserRegistrationFrame frame;
    private final List<JFrame> windows = new ArrayList<>();

    public void UserRegistration() {
        if (windows.size() == 0) {
            frame = new UserRegistrationFrame();
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
            log.info("Get userLogin {}, userPassword {}", userLogin, userPassword);
            UserDto userDto = UserMapper.toUserDto(userLogin, userPassword);
            ResponseEntity<Object> registrationAnswer = userClient.userRegistration(userDto);
            if (registrationAnswer.getStatusCode().is2xxSuccessful()) {
                User user = UserMapper.toUser(registrationAnswer.getBody(), userLogin);
                frame.getFrame().dispose();
                windows.clear();
                JOptionPane.showMessageDialog(frame.getFrame(), "Пользователь " + user.getEmail() + " успешно зарегистрирован");
            } else {
                JOptionPane.showMessageDialog(frame.getFrame(), registrationAnswer.getBody(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.getButtonCancel().addActionListener(e -> {
            frame.getFrame().dispose();
            windows.clear();
        });
    }
}
