package get.newNRG.type;

import get.newNRG.factories.FactoryClient;
import get.newNRG.factories.FactoryDto;
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
import java.util.List;
import java.util.stream.Collectors;

@Component
@Getter
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AddTypeFrameController {

    private final TypeClient typeClient;
    private final SpecificationFrameController specificationFrameController;
    private final FactoryClient factoryClient;
    private final SpecificationValueClient specificationValueClient;
    private final UserAuthorizationFrameController authorizationFrameController;
    private AddTypeFrame frame;

    public void initAddTypeFrameController() {

        List<SpecificationDto> specifications = specificationFrameController.findAllSpecifications();
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

            String specificationString1 = frame.getSpecificationMenu1().getSelectedItem().toString();
            Long specificationId1 = null;
            for (SpecificationDto specification : specifications) {
                if (specification.getSpecificationName().equalsIgnoreCase(specificationString1)) {
                    specificationId1 = specification.getSpecificationId();
                }
            }
            String specificationValueId1 = frame.getTextSpecificationValue1().getText();
            Long value1 = specificationValueClient.addSpecificationValue(
                    frame,
                    SpecificationValueMapper.toSpecificationValueDto(Long.parseLong(specificationValueId1), specificationId1),
                    authorizationFrameController.getUser().getUserId()
            ).getSpecificationValueId();

            typeClient.addType(
                    frame,
                    TypeMapper.toTypeShortDto(typeName, factoryId, List.of(value1)),
                    authorizationFrameController.getUser().getUserId()
            );
        });

        frame.getButtonCancel().addActionListener(e -> frame.getFrame().dispose());
    }
}
