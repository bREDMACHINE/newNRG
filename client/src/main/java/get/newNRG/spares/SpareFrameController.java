package get.newNRG.spares;

import get.newNRG.users.controllers.UserAuthorizationFrameController;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@Controller
@Getter
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SpareFrameController {

    private SpareFrame frame;
    private final SpareClient spareClient;
    private final UserAuthorizationFrameController authorizationFrameController;

    public void initSpareFrameController(Long spareId) {
        frame = new SpareFrame(getSpare(spareId));

        frame.getFrame().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                frame = null;
            }
        });

        frame.getButtonCancel().addActionListener(e -> frame.getFrame().dispose());
    }

    public SpareDto getSpare(Long spareId) {
        ResponseEntity<Object> getSpareResponse = spareClient.getSpare(
                spareId,
                authorizationFrameController.getUser().getUserId()
        );
        if (getSpareResponse.getStatusCode().is2xxSuccessful() && getSpareResponse.getBody() != null) {
            return SpareMapper.toSpareDto(getSpareResponse.getBody());
        } else {
            JOptionPane.showMessageDialog(
                    frame.getFrame(),
                    getSpareResponse.getStatusCode().toString(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        return null;
    }

    public void deleteSpare(Long spareId) {
        ResponseEntity<Object> getSpareResponse = spareClient.deleteSpare(
                spareId,
                authorizationFrameController.getUser().getUserId()
        );
        if (getSpareResponse.getStatusCode().is2xxSuccessful() && getSpareResponse.getBody() != null) {
            frame.getFrame().dispose();
            JOptionPane.showMessageDialog(frame.getFrame(),"Деталь удалена");
        } else {
            JOptionPane.showMessageDialog(
                    frame.getFrame(),
                    getSpareResponse.getStatusCode().toString(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
