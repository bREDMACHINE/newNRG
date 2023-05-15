package get.newNRG.factories;

import get.newNRG.users.controllers.UserAuthorizationFrameController;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@Component
@Getter
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AddFactoryFrameController {

    private final UserAuthorizationFrameController authorizationFrameController;
    private final FactoryClient factoryClient;
    private AddFactoryFrame frame;

    public void initAddFactoryFrameController() {

        frame = new AddFactoryFrame();

        frame.getFrame().addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                frame = null;
            }
        });

        frame.getButtonOk().addActionListener(e -> {
            String factoryName = frame.getTextFactoryName().getText();
            factoryClient.addFactory(
                    frame,
                    FactoryDto.builder().factoryName(factoryName).build(),
                    authorizationFrameController.getUser().getUserId()
            );
        });

        frame.getButtonCancel().addActionListener(e -> frame.getFrame().dispose());
    }
}
