package get.newNRG.projectdocumentation;

import get.newNRG.equipment.Equipment;
import get.newNRG.equipment.EquipmentRepository;
import get.newNRG.exception.BadRequestException;
import get.newNRG.exception.NotFoundException;
import get.newNRG.files.DataFile;
import get.newNRG.files.DataFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Transactional
public class ProjectDocumentationServiceImpl implements ProjectDocumentationService {

    private final ProjectDocumentationRepository projectDocumentationRepository;
    private final EquipmentRepository equipmentRepository;
    private final DataFileRepository dataFileRepository;

    @Override
    public ProjectDocumentationDto addProject(ProjectDocumentationDto projectDocumentationDto) {
        List<Equipment> equipment = equipmentRepository.findAllById(projectDocumentationDto.getEquipment());
        DataFile dataFile = dataFileRepository.findById(projectDocumentationDto.getFileId())
                .orElseThrow(() -> new NotFoundException("Указанный fileId не существует"));
        if (projectDocumentationRepository.findByNameProjectDocumentation(
                projectDocumentationDto.getNameProjectDocumentation()
        ).isEmpty()) {
            return ProjectDocumentationMapper.toProjectDocumentationDto(projectDocumentationRepository.save(
                    ProjectDocumentationMapper.toProjectDocumentation(projectDocumentationDto, equipment, dataFile)
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
    public List<ProjectDocumentationDto> findAllProjects(Long equipmentId, int from, int size) {
        return projectDocumentationRepository.findByEquipmentEquipmentId(equipmentId, PageRequest.of(from / size, size)).stream()
                .map(ProjectDocumentationMapper::toProjectDocumentationDto)
                .collect(Collectors.toList());
    }
}
