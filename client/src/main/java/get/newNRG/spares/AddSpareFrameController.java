package get.newNRG.spares;

import get.newNRG.general.AddCardFrameController;
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
public class AddSpareFrameController implements AddCardFrameController {

    private final UserAuthorizationFrameController authorizationFrameController;
    private final SpareClient spareClient;
    private AddSpareFrame frame;

    @Override
    public void initAddCardFrameController() {

        frame = new AddSpareFrame();

        frame.getFrame().addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                frame = null;
            }
        });

        frame.getButtonOk().addActionListener(e -> {
            String spareName = frame.getTextSpareName().getText();
            String spareDescription = frame.getTextSpareDescription().getText();
            String spareCode = frame.getTextSpareCode().getText();
            spareClient.addSpare(
                    frame,
                    SpareMapper.toSpareDto(spareName, spareDescription, spareCode),
                    authorizationFrameController.getUser().getUserId()
            );
        });

        frame.getButtonCancel().addActionListener(e -> frame.getFrame().dispose());
    }
}
