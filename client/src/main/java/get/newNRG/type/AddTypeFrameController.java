package get.newNRG.type;

import get.newNRG.factories.AddFactoryFrameController;
import get.newNRG.factories.FactoryClient;
import get.newNRG.factories.FactoryDto;
import get.newNRG.general.AddCardFrameController;
import get.newNRG.general.ControllerUtil;
import get.newNRG.specification.AddSpecificationFrameController;
import get.newNRG.specification.SpecificationDto;
import get.newNRG.specification.SpecificationFrameController;
import get.newNRG.specificationvalue.SpecificationValueClient;
import get.newNRG.specificationvalue.SpecificationValueMapper;
import get.newNRG.users.controllers.UserAuthorizationFrameController;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Getter
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AddTypeFrameController implements AddCardFrameController {

    private final TypeClient typeClient;
    private final SpecificationFrameController specificationFrameController;
    private final AddFactoryFrameController addFactoryFrameController;
    private final AddSpecificationFrameController addSpecificationFrameController;
    private final FactoryClient factoryClient;
    private final SpecificationValueClient specificationValueClient;
    private final UserAuthorizationFrameController authorizationFrameController;
    private AddTypeFrame frame;
    private List<SpecificationDto> specifications;

    public void initAddCardFrameController() {

        specifications = specificationFrameController.findAllSpecifications();
        List<FactoryDto> factories = factoryClient.findAllFactories(
                frame,
                authorizationFrameController.getUser().getUserId());

        frame = new AddTypeFrame(
                specifications.stream().map(SpecificationDto::getSpecificationName).collect(Collectors.toList()),
                factories.stream().map(FactoryDto::getFactoryName).collect(Collectors.toList())
        );

        frame.getFrame().addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                frame = null;
            }
        });

        frame.getButtonOk().addActionListener(e -> {
            String typeName = frame.getTextTypeName().getText();
            String factoryString = frame.getFactoryMenu().getSelectedItem().toString();
            Long factoryId = null;
            for (FactoryDto factory : factories) {
                if (factory.getFactoryName().equalsIgnoreCase(factoryString)) {
                    factoryId = factory.getFactoryId();
                }
            }
            List<Long> ids = new ArrayList<>();
            ids.add(getId(frame.getSpecificationMenu1().getSelectedItem().toString(), frame.getTextSpecificationValue1().getText()));
            ids.add(getId(frame.getSpecificationMenu2().getSelectedItem().toString(), frame.getTextSpecificationValue2().getText()));
            ids.add(getId(frame.getSpecificationMenu3().getSelectedItem().toString(), frame.getTextSpecificationValue3().getText()));
            ids.add(getId(frame.getSpecificationMenu4().getSelectedItem().toString(), frame.getTextSpecificationValue4().getText()));
            ids.add(getId(frame.getSpecificationMenu5().getSelectedItem().toString(), frame.getTextSpecificationValue5().getText()));
            typeClient.addType(
                    frame,
                    TypeMapper.toTypeShortDto(typeName, factoryId, ids),
                    authorizationFrameController.getUser().getUserId()
            );
        });

        frame.getButtonCancel().addActionListener(e -> frame.getFrame().dispose());

        frame.getButtonAddFactory().addActionListener(e -> ControllerUtil.start(addFactoryFrameController));

        frame.getButtonAddSpecification().addActionListener(e -> ControllerUtil.start(addSpecificationFrameController));
    }

    private Long getId(String specificationString, String valueString) {
        Long specificationId = null;
        for (SpecificationDto specification : specifications) {
            if (specification.getSpecificationName().equalsIgnoreCase(specificationString)) {
                specificationId = specification.getSpecificationId();
            }
        }
        if (specificationId != null) {
            return specificationValueClient.addSpecificationValue(
                    frame,
                    SpecificationValueMapper.toSpecificationValueDto(Long.parseLong(valueString), specificationId),
                    authorizationFrameController.getUser().getUserId()
            ).getSpecificationValueId();
        }
        return null;
    }
}
