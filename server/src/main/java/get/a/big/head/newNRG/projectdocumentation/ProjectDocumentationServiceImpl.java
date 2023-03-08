package get.a.big.head.newNRG.projectdocumentation;

import get.a.big.head.newNRG.exception.BadRequestException;
import get.a.big.head.newNRG.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Transactional
public class ProjectDocumentationServiceImpl implements ProjectDocumentationService {

    private final ProjectDocumentationRepository projectDocumentationRepository;

    @Override
    public ProjectDocumentationDto addProject(ProjectDocumentationDto projectDocumentationDto) {
        if (projectDocumentationRepository.findByNameProjectDocumentation(
                projectDocumentationDto.getNameProjectDocumentation()
        ).isEmpty()) {
            return ProjectDocumentationMapper.toProjectDocumentationDto(projectDocumentationRepository.save(
                    ProjectDocumentationMapper.toProjectDocumentation(projectDocumentationDto)
            ));
        }
        throw  new BadRequestException("Указанный проект уже используется");
    }

    @Override
    public void deleteProject(Long id) {
        ProjectDocumentation projectDocumentation = projectDocumentationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Указанный projectId не существует"));
        projectDocumentationRepository.delete(projectDocumentation);
    }

    @Override
    public ProjectDocumentationDto getProject(Long id) {
        return ProjectDocumentationMapper.toProjectDocumentationDto(projectDocumentationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Указанный projectId не существует")));
    }

    @Override
    public List<ProjectDocumentationDto> findAllProjects() {
        return projectDocumentationRepository.findAll().stream()
                .map(ProjectDocumentationMapper::toProjectDocumentationDto)
                .collect(Collectors.toList());
    }
}
