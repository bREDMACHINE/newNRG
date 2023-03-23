package get.a.big.head.newNRG.projectdocumentation;

public class ProjectDocumentationMapper {

    public static ProjectDocumentationDto toProjectDocumentationDto(ProjectDocumentation projectDocumentation) {
        return ProjectDocumentationDto.builder()
                .projectId(projectDocumentation.getProjectId())
                .nameProjectDocumentation(projectDocumentation.getNameProjectDocumentation())
                .codeProjectDocumentation(projectDocumentation.getCodeProjectDocumentation())
                .projectDocumentation(projectDocumentation.getProjectDocumentation())
                .build();
    }

    public static ProjectDocumentation toProjectDocumentation(ProjectDocumentationDto projectDocumentationDto) {
        ProjectDocumentation projectDocumentation = new ProjectDocumentation();
        projectDocumentation.setNameProjectDocumentation(projectDocumentationDto.getNameProjectDocumentation());
        projectDocumentation.setCodeProjectDocumentation(projectDocumentationDto.getCodeProjectDocumentation());
        projectDocumentation.setProjectDocumentation(projectDocumentationDto.getProjectDocumentation());
        return projectDocumentation;
    }
}
