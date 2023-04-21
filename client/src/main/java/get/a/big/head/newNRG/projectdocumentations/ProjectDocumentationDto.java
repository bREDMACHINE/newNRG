package get.a.big.head.newNRG.projectdocumentations;

import get.a.big.head.newNRG.general.WithFile;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=true)
public class ProjectDocumentationDto extends WithFile {

    private String nameProjectDocumentation;
    private String codeProjectDocumentation;
    private List<Long> equipment;
}
