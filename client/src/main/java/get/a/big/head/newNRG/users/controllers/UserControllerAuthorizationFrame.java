package get.a.big.head.newNRG.users.controllers;

import get.a.big.head.newNRG.users.UserClient;
import get.a.big.head.newNRG.users.UserDto;
import get.a.big.head.newNRG.users.UserMapper;
import get.a.big.head.newNRG.users.frames.UserAuthorizationFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@Lazy
@Controller
@Slf4j
public class UserControllerAuthorizationFrame {

    private final UserClient userClient;
    private UserAuthorizationFrame frame;
    private boolean closeFrame = true;

    @Autowired
    public UserControllerAuthorizationFrame(UserClient userClient) {
        this.userClient = userClient;
    }

    public void UserAuthorization() {
        if (closeFrame) {
            frame = new UserAuthorizationFrame();
            closeFrame = false;
        }
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                e.getWindow().dispose();
                closeFrame = true;
                System.out.println("A is closing");
            }
        });
        frame.getButton().addActionListener(e -> {
            String userLogin = frame.getTextFieldLogin().getText();
            String userPassword = String.valueOf(frame.getPasswordField().getPassword());
            log.info("Get userLogin {}, userPassword {}", userLogin, userPassword);
            UserDto userDto = UserMapper.toUserDto(userLogin, userPassword);
            ResponseEntity<Object> login = userClient.userAuthorization(userDto);
            if (login != null) {
                System.out.println(login.getBody());
            }
        });
    }
}
