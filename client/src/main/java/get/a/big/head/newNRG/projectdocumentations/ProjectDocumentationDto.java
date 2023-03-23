package get.a.big.head.newNRG.projectdocumentations;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProjectDocumentationDto {

    private Long projectId;
    private String nameProjectDocumentation;
    private String codeProjectDocumentation;
    private String projectDocumentation;
}
