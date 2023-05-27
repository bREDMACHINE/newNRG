package get.newNRG.users.controllers;

import get.newNRG.general.AddCardFrameController;
import get.newNRG.users.UserClient;
import get.newNRG.users.dtos.UserDto;
import get.newNRG.users.frames.UserRegistrationFrame;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@Component
@Getter
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserRegistrationFrameController implements AddCardFrameController {

    private final UserClient userClient;
    private UserRegistrationFrame frame;

    @Override
    public void initAddCardFrameController() {
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
            String role = userClient.userRegistration(frame, new UserDto(userLogin, userPassword));
            if (role != null) {
                frame.getFrame().dispose();
            }
        });

        frame.getButtonCancel().addActionListener(e -> frame.getFrame().dispose());
    }
}
