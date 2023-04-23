package get.newNRG.factorydocumentations;

import get.newNRG.general.WithFile;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=true)
public class FactoryDocumentationDto extends WithFile {

    private String nameFactoryDocumentation;
    private String codeFactoryDocumentation;
    private List<Long> types;
}
