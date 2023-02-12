package get.a.big.head.newNRG.general;

import get.a.big.head.newNRG.equipment.AddEquipmentFrameController;
import get.a.big.head.newNRG.equipment.EquipmentFrameController;
import get.a.big.head.newNRG.users.controllers.UserAccountFrameController;
import get.a.big.head.newNRG.users.controllers.UserAuthorizationFrameController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class ModeratorMainFrameController extends UserMainFrameController{
    private ModeratorMainFrame frame;
    private final AddEquipmentFrameController addEquipmentFrameController;
    private final UserAuthorizationFrameController authorizationFrameController;

    @Autowired
    public ModeratorMainFrameController(UserAccountFrameController accountFrameController,
                                        EquipmentFrameController equipmentFrameController,
                                        UserAuthorizationFrameController authorizationFrameController,
                                        AddEquipmentFrameController addEquipmentFrameController) {
        super(accountFrameController, equipmentFrameController, authorizationFrameController);
        this.addEquipmentFrameController = addEquipmentFrameController;
        this.authorizationFrameController = authorizationFrameController;
    }

    void initModeratorControllerFrame() {
        frame = new ModeratorMainFrame();

        frame.getButtonAddEquipment().addActionListener(e -> addEquipmentFrameController.addEquipmentController(authorizationFrameController.getUser().getRole()));
    }
}
