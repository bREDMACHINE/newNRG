package get.a.big.head.newNRG.factorydocumentation;

import get.a.big.head.newNRG.files.DataFile;
import get.a.big.head.newNRG.types.Type;

import java.util.List;
import java.util.stream.Collectors;

public class FactoryDocumentationMapper {

    public static FactoryDocumentation toFactoryDocumentation(FactoryDocumentationDto factoryDocumentationDto,
                                                              List<Type> types,
                                                              DataFile dataFile) {
        FactoryDocumentation factoryDocumentation = new FactoryDocumentation();
        factoryDocumentation.setNameFactoryDocumentation(factoryDocumentationDto.getNameFactoryDocumentation());
        factoryDocumentation.setCodeFactoryDocumentation(factoryDocumentation.getCodeFactoryDocumentation());
        factoryDocumentation.setTypes(types);
        factoryDocumentation.setFile(dataFile);
        return factoryDocumentation;
    }

    public static FactoryDocumentationDto toFactoryDocumentationDto(FactoryDocumentation factoryDocumentation) {
        return FactoryDocumentationDto.builder()
                .documentId(factoryDocumentation.getDocumentId())
                .nameFactoryDocumentation(factoryDocumentation.getNameFactoryDocumentation())
                .codeFactoryDocumentation(factoryDocumentation.getCodeFactoryDocumentation())
                .types(factoryDocumentation.getTypes().stream().map(Type::getTypeId).collect(Collectors.toList()))
                .fileId(factoryDocumentation.getFile().getFileId())
                .build();
    }
}
