package get.a.big.head.newNRG.users.controllers;

import get.a.big.head.newNRG.users.UserClient;
import get.a.big.head.newNRG.users.UserMapper;
import get.a.big.head.newNRG.users.dtos.User;
import get.a.big.head.newNRG.users.frames.UserListFrame;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

@Lazy
@Controller
@Slf4j
@Getter
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserListFrameController {

    private final UserAccountFrameController accountFrameController;
    private final UserAuthorizationFrameController authorizationFrameController;
    private final UserClient userClient;
    private UserListFrame frame;
    private boolean close = true;

    public void initUserListFrameController(List<User> users) {
        if (close) {
            frame = new UserListFrame(users);
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

        frame.getButton().addActionListener(e -> accountFrameController.initUserAccountFrameController(
                UserMapper.toUser(userClient.getUser(
                frame.getButton().getActionCommand(),
                authorizationFrameController.getUser().getUserId()
                ))
        ));

        frame.getButtonClose().addActionListener(e -> frame.getFrame().dispose());
    }
}
