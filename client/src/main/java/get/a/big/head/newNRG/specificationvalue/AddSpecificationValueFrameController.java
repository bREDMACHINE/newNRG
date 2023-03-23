package get.a.big.head.newNRG.specificationvalue;

import get.a.big.head.newNRG.specification.*;
import get.a.big.head.newNRG.users.controllers.UserAuthorizationFrameController;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
@Getter
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AddSpecificationValueFrameController {

    private final UserAuthorizationFrameController authorizationFrameController;
    private final SpecificationValueClient specificationValueClient;
    private final SpecificationFrameController specificationFrameController;
    private AddSpecificationValueFrame frame;

    public void initAddSpecificationValueFrameController() {
        List<SpecificationDto> specifications = specificationFrameController.findAllSpecifications();

        frame = new AddSpecificationValueFrame(specifications.stream()
                .map(SpecificationDto::getSpecificationName)
                .collect(Collectors.toList()));

        frame.getFrame().addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                frame = null;
            }
        });

        frame.getButtonOk().addActionListener(e -> {
            Long specificationValue = Long.parseLong(frame.getTextSpecificationValue().getText());
            String specificationString = frame.getSpecificationMenu().getSelectedItem().toString();
            log.info("Add specification  with specificationValue {}, specificationId {}",
                    specificationValue, specificationString);
            Long specificationId = null;
            for (SpecificationDto specification : specifications) {
                if (specification.getSpecificationName().equalsIgnoreCase(specificationString)) {
                    specificationId = specification.getSpecificationId();
                }
            }

            ResponseEntity<Object> addSpecificationValueResponse = specificationValueClient.addSpecificationValue(
                    SpecificationValueMapper.toSpecificationValueDto(specificationValue, specificationId),
                    authorizationFrameController.getUser().getUserId()
            );
            if (addSpecificationValueResponse.getStatusCode().is2xxSuccessful() && addSpecificationValueResponse.getBody() != null) {
                frame.getFrame().dispose();
                JOptionPane.showMessageDialog(frame.getFrame(),
                        "Значение добавлено");
            } else {
                JOptionPane.showMessageDialog(
                        frame.getFrame(),
                        addSpecificationValueResponse.getStatusCode().toString(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        frame.getButtonCancel().addActionListener(e -> frame.getFrame().dispose());
    }
}
