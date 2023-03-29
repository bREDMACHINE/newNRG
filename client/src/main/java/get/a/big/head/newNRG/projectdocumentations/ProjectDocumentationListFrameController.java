package get.a.big.head.newNRG.projectdocumentations;

import get.a.big.head.newNRG.equipment.EquipmentDto;
import get.a.big.head.newNRG.users.controllers.UserAuthorizationFrameController;
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
public class ProjectDocumentationListFrameController {

    private ProjectDocumentationListFrame frame;
    private final ProjectDocumentationClient projectDocumentationClient;
    private final UserAuthorizationFrameController authorizationFrameController;
    private final int size = 15;
    private int maxSize;
    private int from;
    private int pages = maxSize / size + 1;
    private int maxShow = pages * size - 15;
    private Long equipmentId;
    private int page;



    public void initProjectDocumentationListFrameController(EquipmentDto equipment) {
        equipmentId = equipment.getEquipmentId();
        maxSize = equipment.getProjectDocuments().size();
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

    private List<ProjectDocumentationDto> findAllProjects(Long equipmentId, int from) {
        ResponseEntity<Object> projectListResponse = projectDocumentationClient.findAllProjects(
                equipmentId,
                from,
                size,
                authorizationFrameController.getUser().getUserId()
        );
        if (projectListResponse.getStatusCode().is2xxSuccessful() && projectListResponse.getBody() != null) {
            return ProjectDocumentationMapper.toProjectDtos(projectListResponse.getBody());
        } else {
            JOptionPane.showMessageDialog(
                    frame.getFrame(),
                    projectListResponse.getStatusCode().toString(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        return null;
    }

    private void openPage() {
        List<ProjectDocumentationDto> list = findAllProjects(equipmentId, from);
        if (frame != null) {
            frame.getFrame().dispose();
        }
        if (list != null) {
            if (maxSize <= size) {
                frame = new ProjectDocumentationListFrame(list, page, pages);
            } else if (from < size) {
                frame = new ProjectDocumentationListFrame(list, page, pages);
                frame.getPanelButtons().add(frame.getButtonNext());
            } else if (from > size && from < maxShow) {
                frame = new ProjectDocumentationListFrame(list, page, pages);
                frame.getPanelButtons().add(frame.getButtonPrevious());
                frame.getPanelButtons().add(frame.getButtonNext());
            } else if (from > size && from == maxShow) {
                frame = new ProjectDocumentationListFrame(list, page, pages);
                frame.getPanelButtons().add(frame.getButtonPrevious());
            }
        }
    }
}
