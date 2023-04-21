package get.a.big.head.newNRG.equipment.controllers;

import get.a.big.head.newNRG.equipment.EquipmentClient;
import get.a.big.head.newNRG.equipment.EquipmentMapper;
import get.a.big.head.newNRG.equipment.frames.AddEquipmentFrame;
import get.a.big.head.newNRG.type.AddTypeFrameController;
import get.a.big.head.newNRG.type.TypeFrameController;
import get.a.big.head.newNRG.type.TypeShortDto;
import get.a.big.head.newNRG.users.controllers.UserAuthorizationFrameController;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Getter
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AddEquipmentFrameController {

    private final EquipmentClient equipmentClient;
    private final TypeFrameController typeFrameController;
    private final AddTypeFrameController addTypeFrameController;
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

        frame.getButtonAddType().addActionListener(e -> {
            if (addTypeFrameController.getFrame() == null) {
                addTypeFrameController.initAddTypeFrameController();
            } else {
                addTypeFrameController.getFrame().getFrame().toFront();
                addTypeFrameController.getFrame().getFrame().requestFocus();
            }
        });

        frame.getButtonOk().addActionListener(e -> {
            String operationalName = frame.getTextOperationalName().getText();
            Short installationYear = Short.parseShort(frame.getTextInstallationYear().getText());
            String typeString = frame.getTypeMenu().getSelectedItem().toString();
            String userId = authorizationFrameController.getUser().getUserId();
            Long typeId = null;
            for (TypeShortDto type : types) {
                if (type.getTypeName().equalsIgnoreCase(typeString)) {
                    typeId = type.getTypeId();
                }
            }

            equipmentClient.addEquipment(
                    frame,
                    EquipmentMapper.toEquipmentShortDto(operationalName, installationYear, typeId),
                    userId
            );
        });

        frame.getButtonCancel().addActionListener(e -> frame.getFrame().dispose());
    }
}
