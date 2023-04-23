package get.newNRG.type;

import get.newNRG.factorydocumentations.FactoryDocumentationListFrameController;
import get.newNRG.spares.SpareListFrameController;
import get.newNRG.specificationvalue.SpecificationValueListFrameController;
import get.newNRG.users.controllers.UserAuthorizationFrameController;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

@Controller
@Getter
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class TypeFrameController {

    private TypeFrame frame;
    private final TypeClient typeClient;
    private final UserAuthorizationFrameController authorizationFrameController;
    private final SpareListFrameController spareListFrameController;
    private final SpecificationValueListFrameController specificationValueListFrameController;
    private final FactoryDocumentationListFrameController factoryDocumentationListFrameController;

    public void initTypeFrameController(Long typeId) {
        TypeDto type = getType(typeId);

        frame = new TypeFrame(type);

        frame.getButtonSpecifications().addActionListener(e -> {
            if (specificationValueListFrameController.getFrame() == null) {
                specificationValueListFrameController.initSpecificationValueListFrameController(type);
            } else {
                specificationValueListFrameController.getFrame().getFrame().toFront();
                specificationValueListFrameController.getFrame().getFrame().requestFocus();
            }
        });

        frame.getButtonDocuments().addActionListener(e -> {
            if (factoryDocumentationListFrameController.getFrame() == null) {
                factoryDocumentationListFrameController.initFactoryDocumentationListFrameController(
                        type.getFactoryDocuments().size(),
                        type.getTypeId()
                );
            } else {
                factoryDocumentationListFrameController.getFrame().toFront();
                factoryDocumentationListFrameController.getFrame().requestFocus();
            }
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
            updateType(type);
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

    public void updateType(TypeDto type) {
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
    }

    public void deleteType(Long typeId) {
        ResponseEntity<Object> deleteTypeResponse = typeClient.deleteType(
                typeId,
                authorizationFrameController.getUser().getUserId()
        );
        if (deleteTypeResponse.getStatusCode().is2xxSuccessful() && deleteTypeResponse.getBody() != null) {
            frame.getFrame().dispose();
            JOptionPane.showMessageDialog(frame.getFrame(),"Тип удален");
        } else {
            JOptionPane.showMessageDialog(
                    frame.getFrame(),
                    deleteTypeResponse.getStatusCode().toString(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
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
