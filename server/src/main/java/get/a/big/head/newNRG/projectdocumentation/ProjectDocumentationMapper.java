package get.a.big.head.newNRG.projectdocumentation;

import get.a.big.head.newNRG.equipment.Equipment;
import get.a.big.head.newNRG.files.DataFile;

import java.util.stream.Collectors;

public class ProjectDocumentationMapper {

    public static ProjectDocumentationDto toProjectDocumentationDto(ProjectDocumentation projectDocumentation) {
        return ProjectDocumentationDto.builder()
                .projectId(projectDocumentation.getProjectId())
                .nameProjectDocumentation(projectDocumentation.getNameProjectDocumentation())
                .codeProjectDocumentation(projectDocumentation.getCodeProjectDocumentation())
                .equipment(projectDocumentation.getEquipment().stream().map(Equipment::getEquipmentId).collect(Collectors.toList()))
                .fileId(projectDocumentation.getFile().getFileId())
                .build();
    }

    public static ProjectDocumentation toProjectDocumentation(ProjectDocumentationDto projectDocumentationDto,
                                                              DataFile dataFile) {
        ProjectDocumentation projectDocumentation = new ProjectDocumentation();
        projectDocumentation.setNameProjectDocumentation(projectDocumentationDto.getNameProjectDocumentation());
        projectDocumentation.setCodeProjectDocumentation(projectDocumentationDto.getCodeProjectDocumentation());
        projectDocumentation.setFile(dataFile);
        return projectDocumentation;
    }
}
