package get.a.big.head.newNRG.projectdocumentations;

import lombok.Getter;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Lazy
@Controller
@Getter
public class ProjectDocumentationListFrameController {

    private ProjectDocumentationListFrame frame;

    public void initProjectDocumentationListFrameController(Long equipmentId, List<Long> projects) {
        frame = new ProjectDocumentationListFrame(new ArrayList<>());
    }
}
