package get.a.big.head.newNRG.projectdocumentations;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import java.util.List;

@Lazy
@Controller
@Slf4j
@Getter
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ProjectDocumentationFrameController {

    private ProjectDocumentationFrame frame;

    public void initProjectDocumentationFrameController(List<ProjectDocumentation> projectDocuments) {

        frame = new ProjectDocumentationFrame(projectDocuments);
    }

    public ProjectDocumentation addDocumentation() {
        return ProjectDocumentation.builder().build();
    }
}
