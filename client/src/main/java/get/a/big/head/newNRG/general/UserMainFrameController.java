package get.a.big.head.newNRG.general;

import get.a.big.head.newNRG.users.controllers.UserAccountFrameController;
import get.a.big.head.newNRG.users.controllers.UserAuthorizationFrameController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

@Lazy
@Controller
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserMainFrameController {

    private UserMainFrame userMainFrame;
    private final UserAuthorizationFrameController authorizationFrameController;
    private final UserAccountFrameController accountFrameController;


    public void initControllerFrame() {
        userMainFrame = new UserMainFrame();
        userMainFrame.getMenuItemLogin().addActionListener(e -> authorizationFrameController.UserAuthorization());
        userMainFrame.getMenuItemLogout().addActionListener(e -> authorizationFrameController.logout());
        userMainFrame.getMenuItemAccount().addActionListener(e -> accountFrameController.UserAccount());
    }
}
