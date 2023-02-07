package get.a.big.head.newNRG.general;

import get.a.big.head.newNRG.users.UserClient;
import get.a.big.head.newNRG.users.controllers.UserAuthorizationFrameController;
import get.a.big.head.newNRG.users.controllers.UserRegistrationFrameController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

@Lazy
@Controller
@Slf4j
public class MainControllerFrame {

    private final MainFrame mainFrame;
    private final UserClient userClient;

    @Autowired
    public MainControllerFrame(UserClient userClient) {
        this.userClient = userClient;
        this.mainFrame = new MainFrame();
    }

    public void initControllerFrame() {
        mainFrame.getMenuItemAuthorization().addActionListener(e -> new UserAuthorizationFrameController(userClient).UserAuthorization());
        mainFrame.getMenuItemRegistration().addActionListener(e -> new UserRegistrationFrameController(userClient).UserRegistration());
    }
}
