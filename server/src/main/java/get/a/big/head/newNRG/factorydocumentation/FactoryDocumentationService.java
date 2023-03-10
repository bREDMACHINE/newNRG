package get.a.big.head.newNRG.factorydocumentation;

import java.util.List;

public interface FactoryDocumentationService {

    FactoryDocumentationDto addDocument(FactoryDocumentationDto factoryDocumentationDto);
    void deleteDocument(Long id);
    FactoryDocumentationDto getDocument(Long id);
    List<FactoryDocumentationDto> findAllDocuments(Long id);
}
