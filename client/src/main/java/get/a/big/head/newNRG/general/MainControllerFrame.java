package get.a.big.head.newNRG.general;

import get.a.big.head.newNRG.users.UserControllerAuthorizationFrame;
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

    @Autowired
    public MainControllerFrame(UserControllerAuthorizationFrame userControllerAuthorizationFrame) {
        this.userControllerAuthorizationFrame = userControllerAuthorizationFrame;
        this.mainFrame = new MainFrame();
    }

    public void initControllerFrame() {
        mainFrame.getMenuItemAuthorization().addActionListener(e -> {
            userControllerAuthorizationFrame.UserAuthorization();
        });
    }
}
