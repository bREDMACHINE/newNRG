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
public class SpareFrameController extends CardFrameController<SpareDto> {

    private SpareFrame frame;
    private final SpareClient spareClient;
    private final UserAuthorizationFrameController authorizationFrameController;

    @Override
    public void initCardFrameController(SpareDto spareDto) {
        frame = new SpareFrame(spareDto);

        frame.getFrame().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                frame = null;
            }
        });

        frame.getButtonDelete().addActionListener(e -> spareClient.deleteSpare(
                frame,
                spareDto.getId(),
                authorizationFrameController.getUser().getUserId()
        ));

        frame.getButtonCancel().addActionListener(e -> frame.getFrame().dispose());
    }
}
