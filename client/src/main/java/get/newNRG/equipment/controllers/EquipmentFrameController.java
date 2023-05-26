package get.newNRG.equipment.controllers;

import get.newNRG.equipment.EquipmentClient;
import get.newNRG.equipment.EquipmentDto;
import get.newNRG.equipment.frames.EquipmentFrame;
import get.newNRG.events.controllers.AddEventFrameController;
import get.newNRG.events.controllers.EventListFrameController;
import get.newNRG.general.CardFrameController;
import get.newNRG.general.ControllerUtil;
import get.newNRG.projectdocumentations.AddProjectDocumentationFrameController;
import get.newNRG.projectdocumentations.ProjectDocumentationListFrameController;
import get.newNRG.type.TypeFrameController;
import get.newNRG.users.controllers.UserAuthorizationFrameController;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@Component
@Getter
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class EquipmentFrameController implements CardFrameController {

    private EquipmentFrame frame;
    private final EquipmentClient equipmentClient;
    private final TypeFrameController typeFrameController;
    private final EventListFrameController eventListFrameController;
    private final AddEventFrameController addEventFrameController;
    private final UserAuthorizationFrameController authorizationFrameController;
    private final ProjectDocumentationListFrameController projectDocumentationListFrameController;
    private final AddProjectDocumentationFrameController addProjectDocumentationFrameController;

    public void initCardFrameController(Long equipmentId) {
        EquipmentDto equipment = equipmentClient.getEquipment(
                frame,
                equipmentId,
                authorizationFrameController.getUser().getUserId()
        );

        frame = new EquipmentFrame(equipment);

        frame.getFrame().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                frame = null;
            }
        });

        frame.getButtonCancel().addActionListener(e -> frame.getFrame().dispose());

        frame.getButtonType().addActionListener(e -> ControllerUtil.start(
                typeFrameController,
                equipment.getType().getTypeId()
        ));

        frame.getButtonAddEvent().addActionListener(e -> ControllerUtil.start(
                addEventFrameController,
                equipment.getEquipmentId()
        ));

        frame.getButtonAddProject().addActionListener(e -> ControllerUtil.start(
                addProjectDocumentationFrameController,
                equipment.getEquipmentId()
        ));

        frame.getButtonOk().addActionListener(e ->
            equipmentClient.updateEquipment(
                    frame,
                    equipment,
                    authorizationFrameController.getUser().getUserId()
            )
        );

        frame.getButtonProjects().addActionListener(e -> ControllerUtil.start(
                projectDocumentationListFrameController,
                equipment.getEvents().size(),
                equipment.getEquipmentId()
        ));

        frame.getButtonEvents().addActionListener(e -> ControllerUtil.start(
                eventListFrameController,
                equipment.getEvents().size(),
                equipment.getEquipmentId()
        ));
    }
}
