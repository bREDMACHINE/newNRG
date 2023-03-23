package get.a.big.head.newNRG.equipment.controllers;

import get.a.big.head.newNRG.equipment.EquipmentClient;
import get.a.big.head.newNRG.equipment.EquipmentDto;
import get.a.big.head.newNRG.equipment.EquipmentMapper;
import get.a.big.head.newNRG.equipment.frames.EquipmentFrame;
import get.a.big.head.newNRG.events.controllers.AddEventFrameController;
import get.a.big.head.newNRG.events.controllers.EventListFrameController;
import get.a.big.head.newNRG.projectdocumentations.AddProjectDocumentationFrameController;
import get.a.big.head.newNRG.projectdocumentations.ProjectDocumentationListFrameController;
import get.a.big.head.newNRG.type.TypeFrameController;
import get.a.big.head.newNRG.users.controllers.UserAuthorizationFrameController;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@Lazy
@Controller
@Slf4j
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
                addProjectDocumentationFrameController.initAddProjectDocumentationFrameController();
            } else {
                addProjectDocumentationFrameController.getFrame().getFrame().toFront();
                addProjectDocumentationFrameController.getFrame().getFrame().requestFocus();
            }
        });

        frame.getButtonOk().addActionListener(e -> {
            ResponseEntity<Object> updateEquipmentResponse = equipmentClient.updateEquipment(
                    equipment,
                    authorizationFrameController.getUser().getUserId()
            );
            if (updateEquipmentResponse.getStatusCode().is2xxSuccessful() && updateEquipmentResponse.getBody() != null) {
                frame.getFrame().dispose();
            } else {
                JOptionPane.showMessageDialog(
                        frame.getFrame(),
                        updateEquipmentResponse.getStatusCode().toString(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        frame.getButtonProjects().addActionListener(e -> {
            if (projectDocumentationListFrameController.getFrame() == null) {
                projectDocumentationListFrameController.initProjectDocumentationListFrameController(equipment);
            } else {
                projectDocumentationListFrameController.getFrame().getFrame().toFront();
                projectDocumentationListFrameController.getFrame().getFrame().requestFocus();
            }
        });

        frame.getButtonEvents().addActionListener(e -> {
            if (eventListFrameController.getFrame() == null) {
                eventListFrameController.initEventListFrameController(equipment);
            } else {
                eventListFrameController.getFrame().getFrame().toFront();
                eventListFrameController.getFrame().getFrame().requestFocus();
            }
        });
    }

    public EquipmentDto getEquipment(String text, String userId) {
        ResponseEntity<Object> equipmentResponse = equipmentClient.getEquipment(text, userId);
        if (equipmentResponse.getStatusCode().is2xxSuccessful() && equipmentResponse.getBody() != null) {
            return EquipmentMapper.toEquipmentDto(equipmentResponse.getBody());
        } else {
            JOptionPane.showMessageDialog(
                    frame.getFrame(),
                    equipmentResponse.getStatusCode().toString(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        return null;
    }
}
