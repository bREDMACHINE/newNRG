package get.a.big.head.newNRG.projectdocumentation;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ProjectDocumentationDto {

    private Long projectId;
    private String nameProjectDocumentation;
    private String codeProjectDocumentation;
    private List<Long> equipment;
    private Long fileId;
}
