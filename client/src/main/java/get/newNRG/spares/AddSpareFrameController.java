package get.newNRG.spares;

import get.newNRG.users.controllers.UserAuthorizationFrameController;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@Controller
@Slf4j
@Getter
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AddSpareFrameController {

    private final UserAuthorizationFrameController authorizationFrameController;
    private final SpareClient spareClient;
    private AddSpareFrame frame;

    public void initAddSpareFrameController() {

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
            log.info("Add spare  with spareName {}, spareDescription {}, spareCode {}",
                    spareName, spareDescription, spareCode);

            ResponseEntity<Object> addSpareResponse = spareClient.addSpare(
                    SpareMapper.toSpareDto(spareName, spareDescription, spareCode),
                    authorizationFrameController.getUser().getUserId()
            );
            if (addSpareResponse.getStatusCode().is2xxSuccessful() && addSpareResponse.getBody() != null) {
                SpareDto spareDto = SpareMapper.toSpareDto(addSpareResponse.getBody());
                frame.getFrame().dispose();
                JOptionPane.showMessageDialog(frame.getFrame(),
                        "Деталь " + spareDto.getSpareName() + " успешно добавлена");
            } else {
                JOptionPane.showMessageDialog(
                        frame.getFrame(),
                        addSpareResponse.getStatusCode().toString(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        frame.getButtonCancel().addActionListener(e -> frame.getFrame().dispose());
    }
}
