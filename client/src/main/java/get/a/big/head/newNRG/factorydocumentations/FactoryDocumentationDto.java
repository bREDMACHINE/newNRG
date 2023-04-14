package get.a.big.head.newNRG.factorydocumentations;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class FactoryDocumentationDto {

    private Long documentId;
    private String nameFactoryDocumentation;
    private String codeFactoryDocumentation;
    private List<Long> types;
    private Long fileId;
}
