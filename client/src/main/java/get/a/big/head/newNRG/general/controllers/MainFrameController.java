package get.a.big.head.newNRG.general.controllers;

import get.a.big.head.newNRG.equipment.EquipmentDto;
import get.a.big.head.newNRG.equipment.controllers.AddEquipmentFrameController;
import get.a.big.head.newNRG.equipment.controllers.EquipmentFrameController;
import get.a.big.head.newNRG.general.frames.UserMainFrame;
import get.a.big.head.newNRG.users.controllers.UserAccountFrameController;
import get.a.big.head.newNRG.users.controllers.UserAuthorizationFrameController;
import get.a.big.head.newNRG.users.controllers.UserManagerFrameController;
import get.a.big.head.newNRG.users.models.Role;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@Controller
@Getter
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MainFrameController {

    private final UserAccountFrameController accountFrameController;
    private final EquipmentFrameController equipmentFrameController;
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
                EquipmentDto equipment = equipmentFrameController.getEquipment(
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
