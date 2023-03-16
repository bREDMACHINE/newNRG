package get.a.big.head.newNRG.equipment.controllers;

import get.a.big.head.newNRG.equipment.EquipmentClient;
import get.a.big.head.newNRG.equipment.EquipmentMapper;
import get.a.big.head.newNRG.equipment.EquipmentShortDto;
import get.a.big.head.newNRG.equipment.frames.AddEquipmentFrame;
import get.a.big.head.newNRG.type.TypeFrameController;
import get.a.big.head.newNRG.type.TypeShortDto;
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
public class AddEquipmentFrameController {

    private final EquipmentClient equipmentClient;
    private final TypeFrameController typeFrameController;
    private final UserAuthorizationFrameController authorizationFrameController;
    private AddEquipmentFrame frame;

    public void initAddEquipmentFrameController() {

        List<TypeShortDto> types = typeFrameController.findAllTypes();

        frame = new AddEquipmentFrame(types.stream().map(TypeShortDto::getTypeName).collect(Collectors.toList()));

        frame.getFrame().addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                frame = null;
            }
        });

        frame.getButtonOk().addActionListener(e -> {
            String operationalName = frame.getTextOperationalName().getText();
            Short installationYear = Short.parseShort(frame.getTextInstallationYear().getText());
            String typeString = frame.getTypeMenu().getSelectedItem().toString();
            String userId = authorizationFrameController.getUser().getUserId();
            log.info("Add equipment  with operationalName {}, installationYear {}, type {}",
                    operationalName, installationYear, typeString);
            Long typeId = null;
            for (TypeShortDto type : types) {
                if (type.getTypeName().equalsIgnoreCase(typeString)) {
                    typeId = type.getTypeId();
                }
            }

            ResponseEntity<Object> addEquipmentResponse = equipmentClient.addEquipment(
                    EquipmentMapper.toEquipmentShortDto(operationalName, installationYear, typeId),
                    userId
            );
            if (addEquipmentResponse.getStatusCode().is2xxSuccessful() && addEquipmentResponse.getBody() != null) {
                EquipmentShortDto equipment = EquipmentMapper.toEquipmentShortDto(addEquipmentResponse.getBody());
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
