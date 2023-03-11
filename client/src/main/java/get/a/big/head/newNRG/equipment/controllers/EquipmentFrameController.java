package get.a.big.head.newNRG.equipment.controllers;

import get.a.big.head.newNRG.equipment.Equipment;
import get.a.big.head.newNRG.equipment.EquipmentClient;
import get.a.big.head.newNRG.equipment.EquipmentMapper;
import get.a.big.head.newNRG.equipment.frames.EquipmentFrame;
import get.a.big.head.newNRG.events.controllers.AddEventFrameController;
import get.a.big.head.newNRG.events.controllers.EventListFrameController;
import get.a.big.head.newNRG.projectdocumentations.ProjectDocumentationFrameController;
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
    private Equipment equipment;
    private final EquipmentClient equipmentClient;
    private final EventListFrameController eventListFrameController;
    private final AddEventFrameController addEventFrameController;
    private final UserAuthorizationFrameController authorizationFrameController;
    private final ProjectDocumentationFrameController projectDocumentationFrameController;

    public void initEquipmentFrameController(Equipment equipment) {
        this.equipment = equipment;
        frame = new EquipmentFrame(equipment);

        frame.getFrame().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                frame = null;
            }
        });

        frame.getButtonCancel().addActionListener(e -> frame.getFrame().dispose());

        frame.getButtonAddEvent().addActionListener(e -> {
            if (addEventFrameController.getFrame() == null) {
                addEventFrameController.initAddEventFrameController();
                if (addEventFrameController.getEvent() != null) {
                    equipment.getEvents().add(addEventFrameController.getEvent());
                }
            } else {
                addEventFrameController.getFrame().getFrame().toFront();
                addEventFrameController.getFrame().getFrame().requestFocus();
            }
        });

        frame.getButtonAddDocumentation().addActionListener(e -> {
            equipment.getProjectDocuments().add(projectDocumentationFrameController.addDocumentation());
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

        frame.getButtonShowDocumentation().addActionListener(e -> {
            if (projectDocumentationFrameController.getFrame() == null) {
                projectDocumentationFrameController.initProjectDocumentationFrameController(equipment.getProjectDocuments());
            } else {
                projectDocumentationFrameController.getFrame().getFrame().toFront();
                projectDocumentationFrameController.getFrame().getFrame().requestFocus();
            }
        });

        frame.getButtonShowEvents().addActionListener(e -> {
            if (eventListFrameController.getFrame() == null) {
                eventListFrameController.initEventListFrameController(equipment.getEvents());
            } else {
                eventListFrameController.getFrame().getFrame().toFront();
                eventListFrameController.getFrame().getFrame().requestFocus();
            }
        });
    }

    public Equipment getEquipment(String text, String userId) {
        ResponseEntity<Object> equipmentResponse = equipmentClient.getEquipment(text, userId);
        if (equipmentResponse.getStatusCode().is2xxSuccessful() && equipmentResponse.getBody() != null) {
            return EquipmentMapper.toEquipment(equipmentResponse.getBody());
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
