package get.newNRG.specification;

import get.newNRG.general.AddCardFrameController;
import get.newNRG.users.controllers.UserAuthorizationFrameController;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@Component
@Getter
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AddSpecificationFrameController implements AddCardFrameController {

    private final UserAuthorizationFrameController authorizationFrameController;
    private final SpecificationClient specificationClient;
    private AddSpecificationFrame frame;

    @Override
    public void initAddCardFrameController() {

        frame = new AddSpecificationFrame();

        frame.getFrame().addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                frame = null;
            }
        });

        frame.getButtonOk().addActionListener(e -> {
            String specificationName = frame.getTextSpecificationName().getText();
            String specificationDescription = frame.getTextSpecificationDescription().getText();

            ResponseEntity<Object> addSpecificationResponse = specificationClient.addSpecification(
                    SpecificationMapper.toSpecificationDto(specificationName, specificationDescription),
                    authorizationFrameController.getUser().getUserId()
            );
            if (addSpecificationResponse.getStatusCode().is2xxSuccessful() && addSpecificationResponse.getBody() != null) {
                SpecificationDto specificationDto = SpecificationMapper.toSpecificationDto(addSpecificationResponse);
                frame.getFrame().dispose();
                JOptionPane.showMessageDialog(frame.getFrame(),
                        "Характеристика " + specificationDto.getSpecificationName() + " успешно добавлена");
            } else {
                JOptionPane.showMessageDialog(
                        frame.getFrame(),
                        addSpecificationResponse.getStatusCode().toString(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        frame.getButtonCancel().addActionListener(e -> frame.getFrame().dispose());
    }
}
