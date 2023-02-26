package get.a.big.head.newNRG.general.controllers;

import get.a.big.head.newNRG.equipment.AddEquipmentFrameController;
import get.a.big.head.newNRG.equipment.Equipment;
import get.a.big.head.newNRG.equipment.EquipmentFrameController;
import get.a.big.head.newNRG.general.frames.AdminMainFrame;
import get.a.big.head.newNRG.general.frames.ModeratorMainFrame;
import get.a.big.head.newNRG.general.frames.UserMainFrame;
import get.a.big.head.newNRG.users.controllers.UserAccountFrameController;
import get.a.big.head.newNRG.users.controllers.UserAuthorizationFrameController;
import get.a.big.head.newNRG.users.controllers.UserManagerFrameController;
import get.a.big.head.newNRG.users.dtos.Role;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@Lazy
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

        if (authorizationFrameController.getUser().getRole().equals(Role.USER.name())) {
            frame = new UserMainFrame();
        } else if (authorizationFrameController.getUser().getRole().equals(Role.MODERATOR.name())) {
            frame = new ModeratorMainFrame();
        } else if (authorizationFrameController.getUser().getRole().equals(Role.ADMIN.name())) {
            frame = new AdminMainFrame();
        }

        frame.getMenuItemLogout().addActionListener(event -> {
            authorizationFrameController.logout();
            frame.getFrame().dispose();
        });

        frame.getMenuItemAccount().addActionListener(e -> {
            if (accountFrameController.getFrame() == null) {
                accountFrameController.initUserAccountFrameController(authorizationFrameController.getUser());
            } else {
                accountFrameController.getFrame().getFrame().toFront();
                accountFrameController.getFrame().getFrame().requestFocus();
            }
        });

        frame.getButtonFind().addActionListener(e -> {
            Equipment equipment = equipmentFrameController.getEquipment(
                    frame.getTextField().getText(),
                    authorizationFrameController.getUser().getUserId()
            );
            equipmentFrameController.initEquipmentFrameController(equipment);
        });

        frame.getButtonAddEquipment().addActionListener(
                e -> addEquipmentFrameController.initAddEquipmentFrameController()
        );

        frame.getUserManager().addActionListener(e -> {
            if (userManagerFrameController.getFrame() == null) {
                userManagerFrameController.initUserManagerFrameController();
            } else {
                userManagerFrameController.getFrame().getFrame().toFront();
                userManagerFrameController.getFrame().getFrame().requestFocus();
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
