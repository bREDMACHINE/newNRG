package get.newNRG.specificationvalue;

import get.newNRG.type.TypeDto;
import get.newNRG.users.controllers.UserAuthorizationFrameController;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

@Component
@Getter
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SpecificationValueListFrameController {

    private SpecificationValueListFrame frame;
    private final SpecificationValueClient specificationValueClient;
    private final UserAuthorizationFrameController authorizationFrameController;
    private final int size = 15;
    private int maxSize;
    private int from;
    private int pages = maxSize / size + 1;
    private int maxShow = pages * size - 15;
    private Long typeId;
    private int page;



    public void initSpecificationValueListFrameController(TypeDto type) {
        typeId = type.getTypeId();
        maxSize = type.getSpecificationValues().size();
        from = 0;
        page = 1;
        openPage();

        frame.getButtonNext().addActionListener(e -> {
            from = from + 15;
            page = page + 1;
            openPage();
        });

        frame.getButtonPrevious().addActionListener(e -> {
            from = from - 15;
            page = page - 1;
            openPage();
        });

        frame.getFrame().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                frame = null;
            }
        });
    }

    private List<SpecificationValueDto> findAllSpecificationValues(Long typeId, int from) {
        ResponseEntity<Object> specificationValuesListResponse = specificationValueClient.findAllSpecificationValues(
                typeId,
                from,
                size,
                authorizationFrameController.getUser().getUserId()
        );
        if (specificationValuesListResponse.getStatusCode().is2xxSuccessful() && specificationValuesListResponse.getBody() != null) {
            return SpecificationValueMapper.toSpecificationValueDtos(specificationValuesListResponse.getBody());
        } else {
            JOptionPane.showMessageDialog(
                    frame.getFrame(),
                    specificationValuesListResponse.getStatusCode().toString(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        return null;
    }

    private void openPage() {
        List<SpecificationValueDto> list = findAllSpecificationValues(typeId, from);
        if (frame != null) {
            frame.getFrame().dispose();
        }
        if (list != null) {
            if (maxSize <= size) {
                frame = new SpecificationValueListFrame(list, page, pages);
            } else if (from < size) {
                frame = new SpecificationValueListFrame(list, page, pages);
                frame.getPanelButtons().add(frame.getButtonNext());
            } else if (from > size && from < maxShow) {
                frame = new SpecificationValueListFrame(list, page, pages);
                frame.getPanelButtons().add(frame.getButtonPrevious());
                frame.getPanelButtons().add(frame.getButtonNext());
            } else if (from > size && from == maxShow) {
                frame = new SpecificationValueListFrame(list, page, pages);
                frame.getPanelButtons().add(frame.getButtonPrevious());
            }
        }
    }
}
