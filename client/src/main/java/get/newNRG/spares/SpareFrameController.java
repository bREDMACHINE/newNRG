package get.newNRG.spares;

import get.newNRG.general.CardFrameController;
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
public class SpareFrameController implements CardFrameController {

    private SpareFrame frame;
    private final SpareClient spareClient;
    private final UserAuthorizationFrameController authorizationFrameController;

    @Override
    public void initCardFrameController(Long spareId) {
        frame = new SpareFrame(spareClient.get(frame, spareId, authorizationFrameController.getUser().getUserId()));

        frame.getFrame().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                frame = null;
            }
        });

        frame.getButtonDelete().addActionListener(e -> spareClient.deleteSpare(
                frame,
                spareId,
                authorizationFrameController.getUser().getUserId()
        ));

        frame.getButtonCancel().addActionListener(e -> frame.getFrame().dispose());
    }
}
