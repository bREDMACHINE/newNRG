package get.newNRG.users.controllers;

import get.newNRG.users.UserClient;
import get.newNRG.users.models.User;
import get.newNRG.users.dtos.UserDto;
import get.newNRG.users.frames.UserAuthorizationFrame;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@Component
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
            user = userClient.userAuthorization(frame, new UserDto(userLogin, userPassword));
            if (user != null) {
                frame.getFrame().dispose();
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
}
