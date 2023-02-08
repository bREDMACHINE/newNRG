package get.a.big.head.newNRG.general;

import get.a.big.head.newNRG.users.UserClient;
import get.a.big.head.newNRG.users.controllers.UserAccountFrameController;
import get.a.big.head.newNRG.users.controllers.UserAuthorizationFrameController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MainFrameController {

    private MainFrame mainFrame;
    private final UserAuthorizationFrameController authorizationFrameController;
    private final UserClient userClient;
    private final UserAccountFrameController accountFrameController;


    public void initControllerFrame() {
        mainFrame = new MainFrame();
        mainFrame.getMenuItemLogin().addActionListener(e -> authorizationFrameController.UserAuthorization());
        mainFrame.getMenuItemLogout().addActionListener(e -> userClient.logout());
        mainFrame.getMenuItemAccount().addActionListener(e -> accountFrameController.UserAccount());
    }
}
