package get.a.big.head.newNRG.factorydocumentation;

import get.a.big.head.newNRG.projectdocumentation.ProjectDocumentationDto;
import get.a.big.head.newNRG.projectdocumentation.ProjectDocumentationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Slf4j
public class FactoryDocumentationController {

    private final FactoryDocumentationService factoryDocumentationService;

    @PostMapping("/equipment/moderator/type/document")
    public ProjectDocumentationDto addProject(@RequestBody ProjectDocumentationDto projectDocumentationDto) {
        log.info("Получен Post запрос к эндпоинту /equipment/moderator/project, project={}", projectDocumentationDto);
        return projectDocumentationService.addProject(projectDocumentationDto);
    }

    @DeleteMapping("/equipment/moderator/project/{id}")
    public void deleteProject(@PathVariable Long id) {
        log.info("Получен Delete запрос к эндпоинту /equipment/moderator/project/{}", id);
        projectDocumentationService.deleteProject(id);
    }

    @GetMapping("/equipment/project/{id}")
    public ProjectDocumentationDto getProject(@PathVariable Long id) {
        log.info("Получен Get запрос к эндпоинту /equipment/project/{}", id);
        return projectDocumentationService.getProject(id);
    }

    @GetMapping("/equipment/projects")
    public List<ProjectDocumentationDto> findAllProjects() {
        log.info("Получен Get запрос к эндпоинту /equipment/projects");
        return projectDocumentationService.findAllProjects();
    }
}
