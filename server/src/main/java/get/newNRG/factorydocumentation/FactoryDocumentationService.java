package get.newNRG.factorydocumentation;

import java.util.List;

public interface FactoryDocumentationService {

    FactoryDocumentationDto addDocument(FactoryDocumentationDto factoryDocumentationDto);
    void deleteDocument(Long id);
    FactoryDocumentationDto getDocument(Long id);
    List<FactoryDocumentationDto> findAllDocuments(Long id, int rom, int size);
}
