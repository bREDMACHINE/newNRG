package get.newNRG.general.controllers;

import get.newNRG.users.controllers.UserAuthorizationFrameController;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class FrameController {
    private final UserAuthorizationFrameController authorizationFrameController;
    private final MainFrameController mainFrameController;

    public void initControllerFrame() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

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
