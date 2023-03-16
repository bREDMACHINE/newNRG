package get.a.big.head.newNRG.type;


import get.a.big.head.newNRG.users.controllers.UserAuthorizationFrameController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import java.util.List;

@Lazy
@Controller
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class TypeFrameController {

    private TypeFrame frame;
    private final TypeClient typeClient;
    private final UserAuthorizationFrameController authorizationFrameController;

    public void initTypeFrameController() {
        TypeDto type = null;
        Long typeId = 1L;
        ResponseEntity<Object> getTypeResponse = typeClient.getType(typeId, authorizationFrameController.getUser().getUserId());
        if (getTypeResponse.getStatusCode().is2xxSuccessful() && getTypeResponse.getBody() != null) {
            type = TypeMapper.toTypeDto(getTypeResponse.getBody());
        } else {
            JOptionPane.showMessageDialog(frame.getFrame(), getTypeResponse.getStatusCode().toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }
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
