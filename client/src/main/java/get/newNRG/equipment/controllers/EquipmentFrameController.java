package get.newNRG.equipment.controllers;

import get.newNRG.equipment.EquipmentClient;
import get.newNRG.equipment.EquipmentDto;
import get.newNRG.equipment.frames.EquipmentFrame;
import get.newNRG.events.controllers.AddEventFrameController;
import get.newNRG.events.controllers.EventListFrameController;
import get.newNRG.general.FrameStarterController;
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
public class EquipmentFrameController extends FrameStarterController {

    private EquipmentFrame frame;
    private final EquipmentClient equipmentClient;
    private final TypeFrameController typeFrameController;
    private final EventListFrameController eventListFrameController;
    private final AddEventFrameController addEventFrameController;
    private final UserAuthorizationFrameController authorizationFrameController;
    private final ProjectDocumentationListFrameController projectDocumentationListFrameController;
    private final AddProjectDocumentationFrameController addProjectDocumentationFrameController;

    public void initEquipmentFrameController(EquipmentDto equipment) {
        frame = new EquipmentFrame(equipment);

        frame.getFrame().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                frame = null;
            }
        });

        frame.getButtonCancel().addActionListener(e -> frame.getFrame().dispose());

        frame.getButtonType().addActionListener(e -> startFrame(
                typeFrameController.getFrame().getFrame(),
                typeFrameController,
                equipment.getType().getTypeId()
        ));

        frame.getButtonAddEvent().addActionListener(e -> {
            if (addEventFrameController.getFrame() == null) {
                addEventFrameController.initAddEventFrameController(equipment.getEquipmentId());
            } else {
                addEventFrameController.getFrame().getFrame().toFront();
                addEventFrameController.getFrame().getFrame().requestFocus();
            }
        });

        frame.getButtonAddProject().addActionListener(e -> {
            if (addProjectDocumentationFrameController.getFrame() == null) {
                addProjectDocumentationFrameController.initAddProjectDocumentationFrameController(equipment.getEquipmentId());
            } else {
                addProjectDocumentationFrameController.getFrame().getFrame().toFront();
                addProjectDocumentationFrameController.getFrame().getFrame().requestFocus();
            }
        });

        frame.getButtonOk().addActionListener(e -> {
            equipmentClient.updateEquipment(
                    frame,
                    equipment,
                    authorizationFrameController.getUser().getUserId()
            );
        });

        frame.getButtonProjects().addActionListener(e -> {
            if (projectDocumentationListFrameController.getFrame() == null) {
                projectDocumentationListFrameController.initProjectDocumentationListFrameController(
                        equipment.getEvents().size(),
                        equipment.getEquipmentId()
                );
            } else {
                projectDocumentationListFrameController.getFrame().toFront();
                projectDocumentationListFrameController.getFrame().requestFocus();
            }
        });

        frame.getButtonEvents().addActionListener(e -> {
            if (eventListFrameController.getFrame() == null) {
                eventListFrameController.initEventListFrameController(
                        equipment.getEvents().size(),
                        equipment.getEquipmentId()
                );
            } else {
                eventListFrameController.getFrame().toFront();
                eventListFrameController.getFrame().requestFocus();
            }
        });
    }
}
