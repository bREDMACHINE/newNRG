package get.a.big.head.newNRG.factorydocumentations;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FactoryDocumentationDto {

    private Long documentId;
    private String nameFactoryDocumentation;
    private String codeFactoryDocumentation;
    private String factoryDocumentation;
}