package get.newNRG.type;

import get.newNRG.factorydocumentations.FactoryDocumentationListFrameController;
import get.newNRG.spares.SpareListFrameController;
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
public class TypeFrameController {

    private TypeFrame frame;
    private final TypeClient typeClient;
    private final UserAuthorizationFrameController authorizationFrameController;
    private final SpareListFrameController spareListFrameController;
    private final FactoryDocumentationListFrameController factoryDocumentationListFrameController;

    public void initTypeFrameController(Long typeId) {
        TypeDto type = typeClient.getType(
                frame,
                typeId,
                authorizationFrameController.getUser().getUserId()
        );

        frame = new TypeFrame(type);

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

        frame.getButtonSpares().addActionListener(e -> {
            if (spareListFrameController.getFrame() == null) {
                spareListFrameController.initSpareListFrameController(
                        type.getSpares().size(),
                        type.getTypeId());
            } else {
                spareListFrameController.getFrame().toFront();
                spareListFrameController.getFrame().requestFocus();
            }
        });

        frame.getButtonOk().addActionListener(e ->
            typeClient.updateType(
                    frame,
                    type,
                    authorizationFrameController.getUser().getUserId()
            )
        );

        frame.getButtonDelete().addActionListener(e -> typeClient.deleteType(
                frame,
                typeId,
                authorizationFrameController.getUser().getUserId()
        ));

        frame.getButtonCancel().addActionListener(e -> frame.getFrame().dispose());

        frame.getFrame().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                frame = null;
                if (spareListFrameController.getFrame() !=null) {
                    spareListFrameController.getFrame().dispose();
                }
            }
        });
    }
}
