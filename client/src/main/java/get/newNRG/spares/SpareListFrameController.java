package get.newNRG.spares;

import get.newNRG.type.TypeDto;
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
public class SpareListFrameController {

    private SpareListFrame frame;
    private final SpareClient spareClient;
    private final UserAuthorizationFrameController authorizationFrameController;
    private final int size = 15;
    private int maxSize;
    private int from;
    private int pages = maxSize / size + 1;
    private int maxShow = pages * size - 15;
    private Long typeId;
    private int page;

    public void initSpareListFrameController(TypeDto type) {
        typeId = type.getTypeId();
        maxSize = type.getSpares().size();
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

        for (JButton button : frame.getDeleteButtons()) {
//            button.addActionListener(e -> spareClient.deleteSpare(
//                        list.get(Integer.parseInt(button.getActionCommand())).getSpareId(),
//                        authorizationFrameController.getUser().getUserId()
//            ));
        }

        frame.getFrame().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                frame = null;
            }
        });
    }

    private List<SpareDto> findAllSpares(Long typeId, int from) {
        ResponseEntity<Object> spareListResponse = spareClient.findAllSpares(
                typeId,
                from,
                size,
                authorizationFrameController.getUser().getUserId()
        );
        if (spareListResponse.getStatusCode().is2xxSuccessful() && spareListResponse.getBody() != null) {
            return SpareMapper.toSpareDtos(spareListResponse.getBody());
        } else {
            JOptionPane.showMessageDialog(
                    frame.getFrame(),
                    spareListResponse.getStatusCode().toString(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        return null;
    }

    private void openPage() {
        List<SpareDto> list = findAllSpares(typeId, from);
        if (frame != null) {
            frame.getFrame().dispose();
        }
        if (list != null) {
            if (maxSize <= size) {
                frame = new SpareListFrame(list, page, pages);
            } else if (from < size) {
                frame = new SpareListFrame(list, page, pages);
                frame.getPanelButtons().add(frame.getButtonNext());
            } else if (from > size && from < maxShow) {
                frame = new SpareListFrame(list, page, pages);
                frame.getPanelButtons().add(frame.getButtonPrevious());
                frame.getPanelButtons().add(frame.getButtonNext());
            } else if (from > size && from == maxShow) {
                frame = new SpareListFrame(list, page, pages);
                frame.getPanelButtons().add(frame.getButtonPrevious());
            }
        }
    }
}
