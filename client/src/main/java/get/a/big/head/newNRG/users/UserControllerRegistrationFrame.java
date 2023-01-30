package get.a.big.head.newNRG.users;

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
public class UserControllerRegistrationFrame {

    private final UserClient userClient;
    private UserRegistrationFrame frame;
    private boolean closeFrame = true;

    @Autowired
    public UserControllerRegistrationFrame(UserClient userClient) {
        this.userClient = userClient;
    }

    public void UserRegistration() {
        if (closeFrame) {
            frame = new UserRegistrationFrame();
            closeFrame = false;
        }
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                e.getWindow().dispose();
                closeFrame = true;
            }
        });
        frame.getButton().addActionListener(e -> {
            String userLogin = frame.getTextFieldLogin().getText();
            String userPassword = String.valueOf(frame.getPasswordField().getPassword());
            log.info("Get userLogin {}, userPassword {}", userLogin, userPassword);
            UserDto userDto = UserMapper.toUserDto(userLogin, userPassword);
            ResponseEntity<Object> login = userClient.userRegistration(userDto);
            if (login != null) {
                System.out.println(login.getBody());
            }
        });
    }
}
