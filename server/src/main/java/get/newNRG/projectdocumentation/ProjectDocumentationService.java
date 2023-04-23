package get.newNRG.projectdocumentation;

import java.util.List;

public interface ProjectDocumentationService {

    ProjectDocumentationDto addProject(ProjectDocumentationDto projectDocumentationDto);
    void deleteProject(Long id);
    ProjectDocumentationDto getProject(Long id);
    List<ProjectDocumentationDto> findAllProjects(Long equipmentId, int from, int size);
}
