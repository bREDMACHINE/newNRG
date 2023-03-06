package get.a.big.head.newNRG.general.controllers;

import get.a.big.head.newNRG.equipment.controllers.AddEquipmentFrameController;
import get.a.big.head.newNRG.equipment.Equipment;
import get.a.big.head.newNRG.equipment.controllers.EquipmentFrameController;
import get.a.big.head.newNRG.general.frames.AdminMainFrame;
import get.a.big.head.newNRG.general.frames.ModeratorMainFrame;
import get.a.big.head.newNRG.general.frames.UserMainFrame;
import get.a.big.head.newNRG.type.Type;
import get.a.big.head.newNRG.type.TypeClient;
import get.a.big.head.newNRG.type.TypeMapper;
import get.a.big.head.newNRG.users.controllers.UserAccountFrameController;
import get.a.big.head.newNRG.users.controllers.UserAuthorizationFrameController;
import get.a.big.head.newNRG.users.controllers.UserManagerFrameController;
import get.a.big.head.newNRG.users.models.Role;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    private final TypeClient typeClient;
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
            if (equipmentFrameController.getFrame() == null) {
                Equipment equipment = equipmentFrameController.getEquipment(
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
                List<Type> types = new ArrayList<>();
                ResponseEntity<Object> findAllTypesResponse = typeClient.findAllTypes(
                        authorizationFrameController.getUser().getUserId()
                );
                if (findAllTypesResponse.getStatusCode().is2xxSuccessful() && findAllTypesResponse.getBody() != null) {
                    types = TypeMapper.toTypes(findAllTypesResponse.getBody());
                } else {
                    JOptionPane.showMessageDialog(
                            frame.getFrame(),
                            findAllTypesResponse.getStatusCode().toString(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
                addEquipmentFrameController.initAddEquipmentFrameController(
                        types.stream().map(Type::getTypeName).collect(Collectors.toList())
                );
            } else {
                addEquipmentFrameController.getFrame().getFrame().toFront();
                addEquipmentFrameController.getFrame().getFrame().requestFocus();
            }
        });

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
