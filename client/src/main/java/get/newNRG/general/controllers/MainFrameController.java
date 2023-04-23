package get.newNRG.general.controllers;

import get.newNRG.equipment.EquipmentClient;
import get.newNRG.equipment.EquipmentDto;
import get.newNRG.equipment.controllers.AddEquipmentFrameController;
import get.newNRG.equipment.controllers.EquipmentFrameController;
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
                if (userManagerFrameController.getFrame() == null) {
                    userManagerFrameController.initUserManagerFrameController();
                } else {
                    userManagerFrameController.getFrame().getFrame().toFront();
                    userManagerFrameController.getFrame().getFrame().requestFocus();
                }
            } else {
                if (accountFrameController.getFrame() == null) {
                    accountFrameController.initUserAccountFrameController(authorizationFrameController.getUser());
                } else {
                    accountFrameController.getFrame().getFrame().toFront();
                    accountFrameController.getFrame().getFrame().requestFocus();
                }
            }
        });

        frame.getButtonFind().addActionListener(e -> {
            if (equipmentFrameController.getFrame() == null) {
                EquipmentDto equipment = equipmentClient.getEquipment(
                        frame,
                        frame.getTextField().getText(),
                        authorizationFrameController.getUser().getUserId()
                );
                equipmentFrameController.initEquipmentFrameController(equipment);
            } else {
                equipmentFrameController.getFrame().getFrame().toFront();
                equipmentFrameController.getFrame().getFrame().requestFocus();
            }
        });

        frame.getButtonAddEquipment().addActionListener(e -> {
            if (addEquipmentFrameController.getFrame() == null) {
                addEquipmentFrameController.initAddEquipmentFrameController();
            } else {
                addEquipmentFrameController.getFrame().getFrame().toFront();
                addEquipmentFrameController.getFrame().getFrame().requestFocus();
            }
        });

        frame.getFrame().addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                if (accountFrameController.getFrame() !=null) {
                    accountFrameController.getFrame().getFrame().dispose();
                }
                if (equipmentFrameController.getFrame() !=null) {
                    equipmentFrameController.getFrame().getFrame().dispose();
                }
                if (addEquipmentFrameController.getFrame() !=null) {
                    addEquipmentFrameController.getFrame().getFrame().dispose();
                }
                if (userManagerFrameController.getFrame() != null) {
                    userManagerFrameController.getFrame().getFrame().dispose();
                }
            }
        });
    }
}
