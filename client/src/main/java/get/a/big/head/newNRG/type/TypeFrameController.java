package get.a.big.head.newNRG.type;

import get.a.big.head.newNRG.spares.SpareListFrameController;
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
import java.util.List;

@Lazy
@Controller
@Slf4j
@Getter
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class TypeFrameController {

    private TypeFrame frame;
    private final TypeClient typeClient;
    private final UserAuthorizationFrameController authorizationFrameController;
    private final SpareListFrameController spareListFrameController;

    public void initTypeFrameController(Long typeId) {
        TypeDto type = getType(typeId);

        frame = new TypeFrame(type);

        frame.getButtonSpecifications().addActionListener(e -> {
            //открытие листа характеристик
        });

        frame.getButtonDocuments().addActionListener(e -> {
            //открытие листа документов
        });

        frame.getButtonDocuments().addActionListener(e -> {
            if (spareListFrameController.getFrame() == null) {
                spareListFrameController.initSpareListFrameController(type);
            } else {
                spareListFrameController.getFrame().getFrame().toFront();
                spareListFrameController.getFrame().getFrame().requestFocus();
            }
        });

        frame.getButtonOk().addActionListener(e -> {
            ResponseEntity<Object> updateTypeResponse = typeClient.updateType(
                    type,
                    authorizationFrameController.getUser().getUserId()
            );
            if (updateTypeResponse.getStatusCode().is2xxSuccessful() && updateTypeResponse.getBody() != null) {
                frame.getFrame().dispose();
            } else {
                JOptionPane.showMessageDialog(
                        frame.getFrame(),
                        updateTypeResponse.getStatusCode().toString(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        frame.getButtonCancel().addActionListener(e -> frame.getFrame().dispose());

        frame.getFrame().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                frame = null;
                if (spareListFrameController.getFrame() !=null) {
                    spareListFrameController.getFrame().getFrame().dispose();
                }
            }
        });
    }

    public TypeDto getType(Long typeId) {
        ResponseEntity<Object> getTypeResponse = typeClient.getType(
                typeId,
                authorizationFrameController.getUser().getUserId()
        );
        if (getTypeResponse.getStatusCode().is2xxSuccessful() && getTypeResponse.getBody() != null) {
            return TypeMapper.toTypeDto(getTypeResponse.getBody());
        } else {
            JOptionPane.showMessageDialog(
                    frame.getFrame(),
                    getTypeResponse.getStatusCode().toString(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        return null;
    }

    public List<TypeShortDto> findAllTypes() {
        ResponseEntity<Object> findAllTypesResponse = typeClient.findAllTypes(
                authorizationFrameController.getUser().getUserId()
        );
        if (findAllTypesResponse.getStatusCode().is2xxSuccessful() && findAllTypesResponse.getBody() != null) {
            return TypeMapper.toTypeShortDtos(findAllTypesResponse.getBody());
        } else {
            JOptionPane.showMessageDialog(
                    frame.getFrame(),
                    findAllTypesResponse.getStatusCode().toString(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        return null;
    }
}
