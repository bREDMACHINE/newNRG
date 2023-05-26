package get.newNRG.equipment.controllers;

import get.newNRG.equipment.EquipmentClient;
import get.newNRG.equipment.EquipmentMapper;
import get.newNRG.equipment.frames.AddEquipmentFrame;
import get.newNRG.general.AddCardFrameController;
import get.newNRG.general.ControllerUtil;
import get.newNRG.type.AddTypeFrameController;
import get.newNRG.type.TypeClient;
import get.newNRG.type.TypeShortDto;
import get.newNRG.users.controllers.UserAuthorizationFrameController;
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
public class AddEquipmentFrameController implements AddCardFrameController {

    private final EquipmentClient equipmentClient;
    private final TypeClient typeClient;
    private final AddTypeFrameController addTypeFrameController;
    private final UserAuthorizationFrameController authorizationFrameController;
    private AddEquipmentFrame frame;

    public void initAddCardFrameController() {

        List<TypeShortDto> types = typeClient.findAllTypes(
                frame,
                authorizationFrameController.getUser().getUserId()
        );;

        frame = new AddEquipmentFrame(types.stream().map(TypeShortDto::getTypeName).collect(Collectors.toList()));

        frame.getFrame().addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                frame = null;
            }
        });

        frame.getButtonAddType().addActionListener(e -> ControllerUtil.start(addTypeFrameController));

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
