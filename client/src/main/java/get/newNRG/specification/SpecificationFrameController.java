package get.newNRG.specification;

import get.newNRG.users.controllers.UserAuthorizationFrameController;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

@Controller
@Getter
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SpecificationFrameController {

    private SpecificationFrame frame;
    private final SpecificationClient specificationClient;
    private final UserAuthorizationFrameController authorizationFrameController;

    public void initSpecificationFrameController(Long specificationId) {
        frame = new SpecificationFrame(getSpecification(specificationId));

        frame.getFrame().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                frame = null;
            }
        });

        frame.getButtonCancel().addActionListener(e -> frame.getFrame().dispose());
    }

    public void deleteSpecification(Long specificationId) {
        ResponseEntity<Object> deleteSpecificationResponse = specificationClient.deleteSpecification(
                specificationId,
                authorizationFrameController.getUser().getUserId()
        );
        if (deleteSpecificationResponse.getStatusCode().is2xxSuccessful() && deleteSpecificationResponse.getBody() != null) {
            frame.getFrame().dispose();
            JOptionPane.showMessageDialog(frame.getFrame(),"Характеристика удалена");
        } else {
            JOptionPane.showMessageDialog(
                    frame.getFrame(),
                    deleteSpecificationResponse.getStatusCode().toString(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public SpecificationDto getSpecification(Long specificationId) {
        ResponseEntity<Object> getSpecificationResponse = specificationClient.getSpecification(
                specificationId,
                authorizationFrameController.getUser().getUserId()
        );
        if (getSpecificationResponse.getStatusCode().is2xxSuccessful() && getSpecificationResponse.getBody() != null) {
            return SpecificationMapper.toSpecificationDto(getSpecificationResponse.getBody());
        } else {
            JOptionPane.showMessageDialog(
                    frame.getFrame(),
                    getSpecificationResponse.getStatusCode().toString(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        return null;
    }

    public List<SpecificationDto> findAllSpecifications() {
        ResponseEntity<Object> findAllSpecificationResponse = specificationClient.findAllSpecifications(
                authorizationFrameController.getUser().getUserId()
        );
        if (findAllSpecificationResponse.getStatusCode().is2xxSuccessful() && findAllSpecificationResponse.getBody() != null) {
            return SpecificationMapper.toSpecificationDtos(findAllSpecificationResponse.getBody());
        } else {
            JOptionPane.showMessageDialog(
                    frame.getFrame(),
                    findAllSpecificationResponse.getStatusCode().toString(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        return null;
    }
}
