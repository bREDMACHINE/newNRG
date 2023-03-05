package get.a.big.head.newNRG.equipment.controllers;

import get.a.big.head.newNRG.equipment.Equipment;
import get.a.big.head.newNRG.equipment.EquipmentClient;
import get.a.big.head.newNRG.equipment.EquipmentMapper;
import get.a.big.head.newNRG.equipment.frames.AddEquipmentFrame;
import get.a.big.head.newNRG.type.Type;
import get.a.big.head.newNRG.type.TypeClient;
import get.a.big.head.newNRG.type.TypeMapper;
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
public class AddEquipmentFrameController {

    private final EquipmentClient equipmentClient;
    private final TypeClient typeClient;
    private final UserAuthorizationFrameController authorizationFrameController;
    private AddEquipmentFrame frame;

    public void initAddEquipmentFrameController(List<String> types) {
        frame = new AddEquipmentFrame(types);

        frame.getFrame().addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                frame = null;
            }
        });

        frame.getButtonOk().addActionListener(e -> {
            String operationalName = frame.getTextOperationalName().getText();
            String installationYear = frame.getTextInstallationYear().getText();
            String typeString = frame.getTypeMenu().getSelectedItem().toString();
            String userId = authorizationFrameController.getUser().getUserId();
            log.info("Add equipment  with operationalName {}, installationYear {}, type {}",
                    operationalName, installationYear, typeString);
            Type type = null;
            ResponseEntity<Object> getTypeResponse = typeClient.getType(typeString, authorizationFrameController.getUser().getUserId());
            if (getTypeResponse.getStatusCode().is2xxSuccessful() && getTypeResponse.getBody() != null) {
                type = TypeMapper.toType(getTypeResponse.getBody());
            } else {
                JOptionPane.showMessageDialog(frame.getFrame(), getTypeResponse.getStatusCode().toString(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            ResponseEntity<Object> addEquipmentResponse = equipmentClient.addEquipment(
                    EquipmentMapper.toEquipment(operationalName, installationYear, type),
                    userId
            );

            if (addEquipmentResponse.getStatusCode().is2xxSuccessful() && addEquipmentResponse.getBody() != null) {
                Equipment equipment = EquipmentMapper.toEquipment(addEquipmentResponse.getBody());
                frame.getFrame().dispose();
                JOptionPane.showMessageDialog(frame.getFrame(),
                        "Оборудование " + equipment.getOperationalName() + " успешно добавлено");
            } else {
                JOptionPane.showMessageDialog(frame.getFrame(), addEquipmentResponse.getStatusCode().toString(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.getButtonCancel().addActionListener(e -> frame.getFrame().dispose());
    }
}
