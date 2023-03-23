package get.a.big.head.newNRG.factorydocumentations;

import get.a.big.head.newNRG.type.TypeDto;
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

@Controller
@Slf4j
@Getter
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class FactoryDocumentationListFrameController {

    private FactoryDocumentationListFrame frame;
    private final FactoryDocumentationClient factoryDocumentationClient;
    private final UserAuthorizationFrameController authorizationFrameController;
    private final int size = 15;
    private int maxSize;
    private int from;
    private int pages = maxSize / size;
    private int maxShow = pages * size;
    private Long typeId;
    private int page;



    public void initFactoryDocumentationListFrameController(TypeDto type) {
        typeId = type.getTypeId();
        maxSize = type.getFactoryDocuments().size();
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

    private List<FactoryDocumentationDto> findAllDocuments(Long typeId, int from) {
        ResponseEntity<Object> documentListResponse = factoryDocumentationClient.findAllDocuments(
                typeId,
                from,
                size,
                authorizationFrameController.getUser().getUserId()
        );
        if (documentListResponse.getStatusCode().is2xxSuccessful() && documentListResponse.getBody() != null) {
            return FactoryDocumentationMapper.toDocumentDtos(documentListResponse.getBody());
        } else {
            JOptionPane.showMessageDialog(
                    frame.getFrame(),
                    documentListResponse.getStatusCode().toString(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        return null;
    }

    private void openPage() {
        List<FactoryDocumentationDto> list = findAllDocuments(typeId, from);
        if (frame != null) {
            frame.getFrame().dispose();
        }
        if (list != null) {
            if (maxSize <= size) {
                frame = new FactoryDocumentationListFrame(list, page, pages);
            } else if (from < size) {
                frame = new FactoryDocumentationListFrame(list, page, pages);
                frame.getPanelButtons().add(frame.getButtonNext());
            } else if (from > size && from < maxShow) {
                frame = new FactoryDocumentationListFrame(list, page, pages);
                frame.getPanelButtons().add(frame.getButtonPrevious());
                frame.getPanelButtons().add(frame.getButtonNext());
            } else if (from > size && from == maxShow) {
                frame = new FactoryDocumentationListFrame(list, page, pages);
                frame.getPanelButtons().add(frame.getButtonPrevious());
            }
        }
    }
}
