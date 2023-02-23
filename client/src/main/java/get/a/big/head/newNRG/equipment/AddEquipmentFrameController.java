package get.a.big.head.newNRG.equipment;

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

@Controller
@Slf4j
@Getter
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AddEquipmentFrameController {

    private boolean close = true;
    private final EquipmentClient equipmentClient;
    private final UserAuthorizationFrameController authorizationFrameController;
    private AddEquipmentFrame frame;

    public void initAddEquipmentFrameController() {
        if (close) {
            frame = new AddEquipmentFrame();
            close = false;
        } else {
            frame.getFrame().toFront();
            frame.getFrame().requestFocus();
        }

        frame.getFrame().addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                close = true;
            }
        });

        frame.getButtonOk().addActionListener(e -> {
            String operationalName = frame.getTextOperationalName().getText();
            String ratedCurrent = frame.getTextRatedCurrent().getText();
            String ratedVoltage = frame.getTextRatedVoltage().getText();
            log.info("Add equipment  with operationalName {}, ratedCurrent {}, ratedVoltage {}",
                    operationalName, ratedCurrent, ratedVoltage);
            EquipmentDto equipmentDto = EquipmentMapper.toEquipmentDto(operationalName, ratedCurrent, ratedVoltage);
            ResponseEntity<Object> addEquipmentAnswer = equipmentClient.addEquipment(
                    equipmentDto,
                    authorizationFrameController.getUser().getUserId()
            );

            if (addEquipmentAnswer.getStatusCode().is2xxSuccessful()) {
                Equipment equipment = EquipmentMapper.toEquipment(addEquipmentAnswer.getBody());
                frame.getFrame().dispose();
                JOptionPane.showMessageDialog(frame.getFrame(),
                        "Оборудование " + equipment.getOperationalName() + " успешно добавлено");
            } else {
                JOptionPane.showMessageDialog(frame.getFrame(), addEquipmentAnswer.getStatusCode().toString(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.getButtonCancel().addActionListener(e -> frame.getFrame().dispose());
    }
}
