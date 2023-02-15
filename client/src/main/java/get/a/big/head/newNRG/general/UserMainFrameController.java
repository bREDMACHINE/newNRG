package get.a.big.head.newNRG.general;

import get.a.big.head.newNRG.equipment.Equipment;
import get.a.big.head.newNRG.equipment.EquipmentFrameController;
import get.a.big.head.newNRG.users.controllers.UserAccountFrameController;
import get.a.big.head.newNRG.users.controllers.UserAuthorizationFrameController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

@Lazy
@Controller
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserMainFrameController {

    private final UserAccountFrameController accountFrameController;
    private final EquipmentFrameController equipmentFrameController;
    private final UserAuthorizationFrameController authorizationFrameController;
    private UserMainFrame frame;

    public void initUserControllerFrame() {
        frame = new UserMainFrame();

        frame.getMenuItemAccount().addActionListener(e -> accountFrameController.userAccount());

        frame.getButtonFind().addActionListener(e -> {
            Equipment equipment = equipmentFrameController.getEquipment(
                    frame.getTextField().getText(),
                    authorizationFrameController.getUser().getUserId()
            );
            equipmentFrameController.initEquipmentController(equipment);
        });

        frame.getMenuItemLogout().addActionListener(e -> {
            authorizationFrameController.logout();
            frame.getFrame().dispose();
        });
    }

    public UserMainFrame getFrame() {
        return frame;
    }
}
