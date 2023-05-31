package get.newNRG.general.controllers;

import get.newNRG.equipment.EquipmentClient;
import get.newNRG.equipment.EquipmentDto;
import get.newNRG.equipment.controllers.AddEquipmentFrameController;
import get.newNRG.equipment.controllers.EquipmentFrameController;
import get.newNRG.general.ControllerUtil;
import get.newNRG.general.frames.UserMainFrame;
import get.newNRG.users.controllers.UserAccountFrameController;
import get.newNRG.users.controllers.UserAuthorizationFrameController;
import get.newNRG.users.controllers.UserManagerFrameController;
import get.newNRG.users.models.Role;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@Component
@Getter
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MainFrameController {

    private final UserAccountFrameController accountFrameController;
    private final EquipmentFrameController equipmentFrameController;
    private final EquipmentClient equipmentClient;
    private final UserAuthorizationFrameController authorizationFrameController;
    private final AddEquipmentFrameController addEquipmentFrameController;
    private final UserManagerFrameController userManagerFrameController;
    private UserMainFrame frame;

    public void initMainFrameController() {
        frame = new UserMainFrame(authorizationFrameController.getUser().getEmail());
        if (authorizationFrameController.getUser().getRole().equals(Role.MODERATOR.name())
                || authorizationFrameController.getUser().getRole().equals(Role.ADMIN.name())) {
            frame.getPanelButtons().add(frame.getButtonAddEquipment());
        }

        frame.getButtonProfile().addActionListener(e -> {
            if (authorizationFrameController.getUser().getRole().equals(Role.ADMIN.name())) {
                ControllerUtil.start(userManagerFrameController);
            } else {
                ControllerUtil.start(accountFrameController, authorizationFrameController.getUser().getEmail());
            }
        });

        frame.getButtonFind().addActionListener(e -> {
            EquipmentDto equipment = equipmentClient.getEquipment(
                    frame,
                    frame.getTextField().getText(),
                    authorizationFrameController.getUser().getUserId()
            );
            ControllerUtil.start(equipmentFrameController, equipment.getEquipmentId());
        });

        frame.getButtonAddEquipment().addActionListener(e -> ControllerUtil.start(addEquipmentFrameController));

        frame.getFrame().addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                ControllerUtil.stop(accountFrameController);
                ControllerUtil.stop(equipmentFrameController);
                ControllerUtil.stop(addEquipmentFrameController);
                ControllerUtil.stop(userManagerFrameController);
            }
        });
    }
}
