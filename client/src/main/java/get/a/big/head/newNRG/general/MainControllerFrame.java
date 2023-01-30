package get.a.big.head.newNRG.general;

import get.a.big.head.newNRG.users.UserControllerAuthorizationFrame;
import get.a.big.head.newNRG.users.UserControllerRegistrationFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

@Lazy
@Controller
@Slf4j
public class MainControllerFrame {

    private final MainFrame mainFrame;
    private final UserControllerAuthorizationFrame userControllerAuthorizationFrame;
    private final UserControllerRegistrationFrame userControllerRegistrationFrame;

    @Autowired
    public MainControllerFrame(
            UserControllerAuthorizationFrame userControllerAuthorizationFrame,
            UserControllerRegistrationFrame userControllerRegistrationFrame) {
        this.userControllerAuthorizationFrame = userControllerAuthorizationFrame;
        this.userControllerRegistrationFrame = userControllerRegistrationFrame;
        this.mainFrame = new MainFrame();
    }

    public void initControllerFrame() {
        mainFrame.getMenuItemAuthorization().addActionListener(e -> userControllerAuthorizationFrame.UserAuthorization());
        mainFrame.getMenuItemRegistration().addActionListener(e -> userControllerRegistrationFrame.UserRegistration());
    }
}
