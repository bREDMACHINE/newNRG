package get.a.big.head.newNRG.general;

import get.a.big.head.newNRG.users.controllers.UserAuthorizationFrameController;
import get.a.big.head.newNRG.users.dtos.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@Controller
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class FrameController {
    private final UserAuthorizationFrameController authorizationFrameController;
    private final UserMainFrameController userMainFrameController;
    private final ModeratorMainFrameController moderatorMainFrameController;
    private final AdminMainFrameController adminMainFrameController;

    public void initControllerFrame() {
        if (authorizationFrameController.getUser() == null) {
            authorizationFrameController.userAuthorization();
        }

        authorizationFrameController.getFrame().getFrame().addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                if (authorizationFrameController.getUser() != null) {
                    if (authorizationFrameController.getUser().getRole().equals(Role.USER.name())) {
                        userMainFrameController.initUserControllerFrame();
                        userMainFrameController.getFrame().getMenuItemLogout().addActionListener(event -> {
                            authorizationFrameController.logout();
                            userMainFrameController.getFrame().getFrame().dispose();
                            initControllerFrame();
                        });
                    } else if (authorizationFrameController.getUser().getRole().equals(Role.MODERATOR.name())) {
                        moderatorMainFrameController.initModeratorControllerFrame();
                        moderatorMainFrameController.getFrame().getMenuItemLogout().addActionListener(event -> {
                            authorizationFrameController.logout();
                            moderatorMainFrameController.getFrame().getFrame().dispose();
                            initControllerFrame();
                        });
                    } else if (authorizationFrameController.getUser().getRole().equals(Role.ADMIN.name())) {
                        adminMainFrameController.initAdminControllerFrame();
                        adminMainFrameController.getFrame().getMenuItemLogout().addActionListener(event -> {
                            authorizationFrameController.logout();
                            adminMainFrameController.getFrame().getFrame().dispose();
                            initControllerFrame();
                        });
                    }
                }
            }
        });
    }
}
