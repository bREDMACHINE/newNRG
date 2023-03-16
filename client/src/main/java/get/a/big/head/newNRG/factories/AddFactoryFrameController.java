package get.a.big.head.newNRG.factories;

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

@Controller
@Slf4j
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
            log.info("Add factory  with factoryName {}", factoryName);

            ResponseEntity<Object> addFactoryResponse = factoryClient.addFactory(
                    FactoryDto.builder().factoryName(factoryName).build(),
                    authorizationFrameController.getUser().getUserId()
            );
            if (addFactoryResponse.getStatusCode().is2xxSuccessful() && addFactoryResponse.getBody() != null) {
                FactoryDto factory = FactoryMapper.toFactoryDto(addFactoryResponse.getBody());
                frame.getFrame().dispose();
                JOptionPane.showMessageDialog(frame.getFrame(),
                        "Наименование завода " + factory.getFactoryName() + " успешно добавлено");
            } else {
                JOptionPane.showMessageDialog(frame.getFrame(), addFactoryResponse.getStatusCode().toString(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.getButtonCancel().addActionListener(e -> frame.getFrame().dispose());
    }

    public List<FactoryDto> findAllFactories() {
        ResponseEntity<Object> findAllFactoriesResponse = factoryClient.findAllFactories(
                authorizationFrameController.getUser().getUserId()
        );
        if (findAllFactoriesResponse.getStatusCode().is2xxSuccessful() && findAllFactoriesResponse.getBody() != null) {
            return FactoryMapper.toFactoryDtos(findAllFactoriesResponse.getBody());
        } else {
            JOptionPane.showMessageDialog(
                    frame.getFrame(),
                    findAllFactoriesResponse.getStatusCode().toString(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        return null;
    }
}
