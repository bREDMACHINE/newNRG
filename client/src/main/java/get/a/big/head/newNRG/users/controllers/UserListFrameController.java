package get.a.big.head.newNRG.users.controllers;

import get.a.big.head.newNRG.users.models.User;
import get.a.big.head.newNRG.users.frames.UserListFrame;
import lombok.Getter;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

@Lazy
@Controller
@Getter
public class UserListFrameController {

    private UserListFrame frame;

    public void initUserListFrameController(List<User> users) {
        frame = new UserListFrame(users);

        frame.getFrame().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                frame = null;
            }
        });

        frame.getButtonClose().addActionListener(e -> frame.getFrame().dispose());
    }
}
