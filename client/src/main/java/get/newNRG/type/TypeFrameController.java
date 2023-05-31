package get.newNRG.type;

import get.newNRG.factorydocumentations.controllers.AddFactoryDocumentationFrameController;
import get.newNRG.factorydocumentations.controllers.FactoryDocumentationListFrameController;
import get.newNRG.general.CardFrameController;
import get.newNRG.general.ControllerUtil;
import get.newNRG.spares.AddSpareFrameController;
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
public class TypeFrameController implements CardFrameController {

    private TypeFrame frame;
    private final TypeClient typeClient;
    private final UserAuthorizationFrameController authorizationFrameController;
    private final SpareListFrameController spareListFrameController;
    private final FactoryDocumentationListFrameController factoryDocumentationListFrameController;
    private final AddFactoryDocumentationFrameController addFactoryDocumentationFrameController;
    private final AddSpareFrameController addSpareFrameController;

    @Override
    public void initCardFrameController(Long typeId) {
        TypeDto type = typeClient.getType(
                frame,
                typeId,
                authorizationFrameController.getUser().getUserId()
        );

        frame = new TypeFrame(type);

        frame.getButtonDocuments().addActionListener(e -> ControllerUtil.start(
                factoryDocumentationListFrameController,
                type.getFactoryDocuments().size(),
                type.getTypeId()
        ));

        frame.getButtonAddDocuments().addActionListener(e -> ControllerUtil.start(
                addFactoryDocumentationFrameController
        ));

        frame.getButtonSpares().addActionListener(e -> ControllerUtil.start(
                spareListFrameController,type.getSpares().size(),
                type.getTypeId()
        ));

        frame.getButtonAddSpares().addActionListener(e -> ControllerUtil.start(addSpareFrameController));

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
                ControllerUtil.stop(factoryDocumentationListFrameController);
                ControllerUtil.stop(addFactoryDocumentationFrameController);
                ControllerUtil.stop(spareListFrameController);
                ControllerUtil.stop(addSpareFrameController);
            }
        });
    }
}
