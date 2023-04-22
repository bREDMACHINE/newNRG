package get.newNRG.projectdocumentation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Slf4j
public class ProjectDocumentationController {

    private final ProjectDocumentationService projectDocumentationService;

    @PostMapping("/moderator/equipment/project")
    public ProjectDocumentationDto addProject(@RequestBody ProjectDocumentationDto projectDocumentationDto) {
        log.info("Получен Post запрос к эндпоинту /moderator/equipment/project, project={}", projectDocumentationDto);
        ProjectDocumentationDto project = projectDocumentationService.addProject(projectDocumentationDto);
        log.info("Результат запроса {}", project);
        return project;
    }

    @DeleteMapping("/moderator/equipment/project/{id}")
    public void deleteProject(@PathVariable Long id) {
        log.info("Получен Delete запрос к эндпоинту /moderator/equipment/project/{}", id);
        projectDocumentationService.deleteProject(id);
    }

    @GetMapping("/user/equipment/project/{id}")
    public ProjectDocumentationDto getProject(@PathVariable Long id) {
        log.info("Получен Get запрос к эндпоинту /user/equipment/project/{}", id);
        ProjectDocumentationDto project = projectDocumentationService.getProject(id);
        log.info("Результат запроса {}", project);
        return project;
    }

    @GetMapping("/user/equipment/{id}/projects")
    public List<ProjectDocumentationDto> findAllProjects(@PathVariable Long id,
                                                         @RequestParam int from,
                                                         @RequestParam int size) {
        log.info("Получен Get запрос к эндпоинту /user/equipment/{}/projects с листа {}", id, from);
        List<ProjectDocumentationDto> list = projectDocumentationService.findAllProjects(id, from, size);
        log.info("Результат запроса {}", list);
        return list;
    }
}
