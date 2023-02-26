package get.a.big.head.newNRG.general.controllers;

import get.a.big.head.newNRG.users.controllers.UserAuthorizationFrameController;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@Controller
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class FrameController {
    private final UserAuthorizationFrameController authorizationFrameController;
    private final MainFrameController mainFrameController;

    public void initControllerFrame() {
        if (authorizationFrameController.getUser() == null) {
            authorizationFrameController.initUserAuthorizationFrameController();
        }

        authorizationFrameController.getFrame().getFrame().addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                if (authorizationFrameController.getUser() != null) {
                    mainFrameController.initMainFrameController();
                    mainFrameController.getFrame().getFrame().addWindowListener(new WindowAdapter() {
                        public void windowClosed(WindowEvent e) {
                            initControllerFrame();
                        }
                    });
                }
            }
        });
    }
}