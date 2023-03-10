package get.a.big.head.newNRG.factorydocumentation;

public class FactoryDocumentationMapper {

    public static FactoryDocumentation toFactoryDocumentation(FactoryDocumentationDto factoryDocumentationDto) {
        FactoryDocumentation factoryDocumentation = new FactoryDocumentation();
        factoryDocumentation.setNameFactoryDocumentation(factoryDocumentationDto.getNameFactoryDocumentation());
        factoryDocumentation.setCodeFactoryDocumentation(factoryDocumentation.getCodeFactoryDocumentation());
        factoryDocumentation.setFactoryDocumentation(factoryDocumentation.getFactoryDocumentation());
        return factoryDocumentation;
    }

    public static FactoryDocumentationDto toFactoryDocumentationDto(FactoryDocumentation factoryDocumentation) {
        return FactoryDocumentationDto.builder()
                .documentId(factoryDocumentation.getDocumentId())
                .nameFactoryDocumentation(factoryDocumentation.getNameFactoryDocumentation())
                .codeFactoryDocumentation(factoryDocumentation.getCodeFactoryDocumentation())
                .factoryDocumentation(factoryDocumentation.getFactoryDocumentation())
                .build();
    }
}
