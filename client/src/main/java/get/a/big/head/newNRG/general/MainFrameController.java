package get.a.big.head.newNRG.general;

import get.a.big.head.newNRG.users.controllers.UserAccountFrameController;
import get.a.big.head.newNRG.users.controllers.UserAuthorizationFrameController;
import get.a.big.head.newNRG.users.dtos.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@Lazy
@Controller
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MainFrameController {

    private UserMainFrame frame;
    private final UserAuthorizationFrameController authorizationFrameController;
    private final UserAccountFrameController accountFrameController;

    public void initControllerFrame() {
        if (authorizationFrameController.getUser() == null) {
            authorizationFrameController.userAuthorization();
        }

        authorizationFrameController.getFrame().getFrame().addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                if (authorizationFrameController.getUser() != null) {
                    if (authorizationFrameController.getUser().getRole().equals(Role.USER.name())) {
                        frame = new UserMainFrame();
                        chooseMainFrame();
                    } else if (authorizationFrameController.getUser().getRole().equals(Role.MODERATOR.name())) {
                        frame = new ModeratorMainFrame();
                    } else if (authorizationFrameController.getUser().getRole().equals(Role.ADMIN.name())) {
                        frame = new AdminMainFrame();
                    }
                }
            }
        });
    }

    private void chooseMainFrame() {
        frame.getMenuItemLogout().addActionListener(e -> {
            authorizationFrameController.logout();
            frame.getFrame().dispose();
            initControllerFrame();
        });
        frame.getMenuItemAccount().addActionListener(e -> accountFrameController.userAccount());
    }
}
