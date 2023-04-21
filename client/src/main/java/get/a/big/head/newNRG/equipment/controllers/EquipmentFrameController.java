package get.a.big.head.newNRG.equipment.controllers;

import get.a.big.head.newNRG.equipment.EquipmentClient;
import get.a.big.head.newNRG.equipment.EquipmentDto;
import get.a.big.head.newNRG.equipment.frames.EquipmentFrame;
import get.a.big.head.newNRG.events.controllers.AddEventFrameController;
import get.a.big.head.newNRG.events.controllers.EventListFrameController;
import get.a.big.head.newNRG.projectdocumentations.AddProjectDocumentationFrameController;
import get.a.big.head.newNRG.projectdocumentations.ProjectDocumentationListFrameController;
import get.a.big.head.newNRG.type.TypeFrameController;
import get.a.big.head.newNRG.users.controllers.UserAuthorizationFrameController;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@Component
@Getter
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class EquipmentFrameController {

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

        frame.getButtonType().addActionListener(e -> {
            if (typeFrameController.getFrame() == null) {
                typeFrameController.initTypeFrameController(equipment.getType().getTypeId());
            } else {
                typeFrameController.getFrame().getFrame().toFront();
                typeFrameController.getFrame().getFrame().requestFocus();
            }
        });

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
