package get.a.big.head.newNRG.general;

import get.a.big.head.newNRG.equipment.AddEquipmentFrameController;
import get.a.big.head.newNRG.equipment.Equipment;
import get.a.big.head.newNRG.equipment.EquipmentFrameController;
import get.a.big.head.newNRG.users.controllers.UserAccountFrameController;
import get.a.big.head.newNRG.users.controllers.UserAuthorizationFrameController;
import get.a.big.head.newNRG.users.controllers.UserManagerFrameController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@Controller
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AdminMainFrameController {

    private final AddEquipmentFrameController addEquipmentFrameController;
    private final UserAuthorizationFrameController authorizationFrameController;
    private final UserAccountFrameController accountFrameController;
    private final EquipmentFrameController equipmentFrameController;
    private final UserManagerFrameController userManagerFrameController;
    private AdminMainFrame frame;

    public void initAdminControllerFrame() {
        frame = new AdminMainFrame();

        frame.getMenuItemAccount().addActionListener(e -> accountFrameController.userAccount());

        frame.getButtonFind().addActionListener(e -> {
            Equipment equipment = equipmentFrameController.getEquipment(
                    frame.getTextField().getText(),
                    authorizationFrameController.getUser().getUserId()
            );
            equipmentFrameController.initEquipmentController(equipment);
        });

        frame.getButtonAddEquipment().addActionListener(
                e -> addEquipmentFrameController.initAddEquipmentFrame(authorizationFrameController.getUser().getUserId())
        );

        frame.getUserManager().addActionListener(
                e -> userManagerFrameController.initUserManagerFrame(authorizationFrameController.getUser().getUserId())
        );

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
            }
        });
    }

    public AdminMainFrame getFrame() {
        return frame;
    }
}
