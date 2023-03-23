package get.a.big.head.newNRG.type;

import get.a.big.head.newNRG.factories.AddFactoryFrameController;
import get.a.big.head.newNRG.factories.FactoryDto;
import get.a.big.head.newNRG.specification.SpecificationDto;
import get.a.big.head.newNRG.specification.SpecificationFrameController;
import get.a.big.head.newNRG.users.controllers.UserAuthorizationFrameController;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
@Getter
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AddTypeFrameController {

    private final TypeClient typeClient;
    private final SpecificationFrameController specificationFrameController;
    private final AddFactoryFrameController addFactoryFrameController;
    private final UserAuthorizationFrameController authorizationFrameController;
    private AddTypeFrame frame;

    public void initAddTypeFrameController() {

        List<SpecificationDto> specifications = specificationFrameController.findAllSpecifications();
        List<FactoryDto> factories = addFactoryFrameController.findAllFactories();

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
            String specificationString = frame.getSpecificationMenu().getSelectedItem().toString();
            log.info("Add type  with typeName {}, factoryString {}, specificationString {}",
                    typeName, factoryString, specificationString);
            Long factoryId = null;
            for (FactoryDto factory : factories) {
                if (factory.getFactoryName().equalsIgnoreCase(factoryString)) {
                    factoryId = factory.getFactoryId();
                }
            }
            Long specificationId = null;
            for (SpecificationDto specification : specifications) {
                if (specification.getSpecificationName().equalsIgnoreCase(specificationString)) {
                    specificationId = specification.getSpecificationId();
                }
            }

            ResponseEntity<Object> addTypeResponse = typeClient.addType(
                    TypeMapper.toTypeShortDto(typeName, factoryId, List.of(specificationId)),
                    authorizationFrameController.getUser().getUserId()
            );
            if (addTypeResponse.getStatusCode().is2xxSuccessful() && addTypeResponse.getBody() != null) {
                TypeShortDto type = TypeMapper.toTypeShortDto(addTypeResponse.getBody());
                frame.getFrame().dispose();
                JOptionPane.showMessageDialog(frame.getFrame(),
                        "Тип " + type.getTypeName() + " успешно добавлен");
            } else {
                JOptionPane.showMessageDialog(
                        frame.getFrame(),
                        addTypeResponse.getStatusCode().toString(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        frame.getButtonCancel().addActionListener(e -> frame.getFrame().dispose());
    }
}
